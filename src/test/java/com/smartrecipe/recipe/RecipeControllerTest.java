package com.smartrecipe.recipe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeVisionService recipeVisionService;

    @Test
    void returnsStructuredRecipeForUploadedImage() throws Exception {
        given(recipeVisionService.generateRecipe(any(), anyString())).willReturn(new Recipe(
                "番茄炒蛋", "家常快炒", 2, 5, 8,
                List.of(new Recipe.Ingredient("鸡蛋", "3 个", "打散")),
                List.of("热锅下油。"), List.of("番茄出汁后再下蛋。"), List.of("照片推测为番茄炒蛋。")
        ));
        MockMultipartFile image = new MockMultipartFile("image", "dish.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[]{1, 2});

        mockMvc.perform(multipart("/api/recipes/from-image").file(image).param("preferences", "少油"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("番茄炒蛋"))
                .andExpect(jsonPath("$.ingredients[0].name").value("鸡蛋"));
    }
}
