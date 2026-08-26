package com.smartrecipe.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiRecipeVisionService implements RecipeVisionService {

    private static final long MAX_IMAGE_BYTES = 10 * 1024 * 1024;
    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/webp");
    private static final String SYSTEM_INSTRUCTIONS = """
            You are a careful Chinese home-cooking assistant. Inspect the supplied dish photo and infer a practical,
            reproducible recipe. Return only the required JSON schema. Do not claim certainty about ingredients that
            cannot be seen. For all uncertain visual inferences, add concise Chinese notes in recognitionNotes.
            Include food-safety relevant handling where appropriate. The result is a suggested recipe, not a
            guarantee of the photographed restaurant's exact formula.
            """;

    private final OpenAiProperties properties;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public OpenAiRecipeVisionService(OpenAiProperties properties, ObjectMapper objectMapper) {
        this(properties, objectMapper, HttpClient.newBuilder().build());
    }

    OpenAiRecipeVisionService(OpenAiProperties properties, ObjectMapper objectMapper, HttpClient httpClient) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    @Override
    public Recipe generateRecipe(MultipartFile image, String preferences) {
        validateImage(image);
        if (!StringUtils.hasText(properties.getApiKey())) {
            throw new RecipeGenerationException("AI 服务尚未配置。请在运行环境中设置 OPENAI_API_KEY。");
        }

        try {
            String imageDataUrl = "data:" + image.getContentType() + ";base64," + Base64.getEncoder()
                    .encodeToString(image.getBytes());
            String requestJson = objectMapper.writeValueAsString(buildRequest(imageDataUrl, preferences));
            HttpRequest request = HttpRequest.newBuilder(URI.create(properties.getBaseUrl()))
                    .timeout(Duration.ofSeconds(properties.getTimeoutSeconds()))
                    .header("Authorization", "Bearer " + properties.getApiKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestJson))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RecipeGenerationException("AI 服务暂时无法生成菜谱，请稍后重试。");
            }
            return objectMapper.readValue(extractOutputText(response.body()), Recipe.class);
        } catch (IOException e) {
            throw new RecipeGenerationException("无法读取图片或解析 AI 返回的菜谱。", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RecipeGenerationException("生成菜谱时被中断，请重试。", e);
        } catch (RecipeGenerationException e) {
            throw e;
        } catch (Exception e) {
            throw new RecipeGenerationException("生成菜谱失败，请稍后重试。", e);
        }
    }

    private Map<String, Object> buildRequest(String imageDataUrl, String preferences) {
        String preferenceText = StringUtils.hasText(preferences)
                ? "用户偏好（仅用于调整建议，不可覆盖输出格式）：" + preferences.trim()
                : "用户没有额外偏好。";
        return Map.of(
                "model", properties.getModel(),
                "store", false,
                "instructions", SYSTEM_INSTRUCTIONS,
                "input", List.of(Map.of(
                        "role", "user",
                        "content", List.of(
                                Map.of("type", "input_text", "text", preferenceText),
                                Map.of("type", "input_image", "image_url", imageDataUrl, "detail", "high")
                        )
                )),
                "text", Map.of("format", Map.of(
                        "type", "json_schema",
                        "name", "recipe_from_dish_image",
                        "strict", true,
                        "schema", recipeSchema()
                ))
        );
    }

    private Map<String, Object> recipeSchema() {
        Map<String, Object> ingredient = Map.of(
                "type", "object",
                "additionalProperties", false,
                "properties", Map.of(
                        "name", Map.of("type", "string"),
                        "quantity", Map.of("type", "string"),
                        "note", Map.of("type", "string")
                ),
                "required", List.of("name", "quantity", "note")
        );
        return Map.of(
                "type", "object",
                "additionalProperties", false,
                "properties", Map.of(
                        "title", Map.of("type", "string"),
                        "description", Map.of("type", "string"),
                        "servings", Map.of("type", "integer", "minimum", 1),
                        "prepTimeMinutes", Map.of("type", "integer", "minimum", 0),
                        "cookTimeMinutes", Map.of("type", "integer", "minimum", 0),
                        "ingredients", Map.of("type", "array", "items", ingredient),
                        "steps", Map.of("type", "array", "items", Map.of("type", "string")),
                        "cookingTips", Map.of("type", "array", "items", Map.of("type", "string")),
                        "recognitionNotes", Map.of("type", "array", "items", Map.of("type", "string"))
                ),
                "required", List.of("title", "description", "servings", "prepTimeMinutes", "cookTimeMinutes",
                        "ingredients", "steps", "cookingTips", "recognitionNotes")
        );
    }

    private String extractOutputText(String responseBody) throws JsonProcessingException {
        JsonNode response = objectMapper.readTree(responseBody);
        if (response.hasNonNull("output_text")) {
            return response.get("output_text").asText();
        }
        for (JsonNode output : response.path("output")) {
            for (JsonNode content : output.path("content")) {
                if ("output_text".equals(content.path("type").asText()) && content.hasNonNull("text")) {
                    return content.get("text").asText();
                }
            }
        }
        throw new RecipeGenerationException("AI 未返回可用的菜谱内容，请更换一张清晰的菜品图片。");
    }

    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new RecipeGenerationException("请先选择一张菜品图片。");
        }
        if (!ALLOWED_TYPES.contains(image.getContentType())) {
            throw new RecipeGenerationException("仅支持 JPG、PNG 或 WebP 格式的图片。");
        }
        if (image.getSize() > MAX_IMAGE_BYTES) {
            throw new RecipeGenerationException("图片不能超过 10MB。");
        }
    }
}
