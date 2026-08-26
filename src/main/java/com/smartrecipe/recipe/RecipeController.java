package com.smartrecipe.recipe;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeVisionService recipeVisionService;

    public RecipeController(RecipeVisionService recipeVisionService) {
        this.recipeVisionService = recipeVisionService;
    }

    @PostMapping(value = "/from-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Recipe> createFromImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam(value = "preferences", required = false) @Size(max = 500) String preferences) {
        return ResponseEntity.ok(recipeVisionService.generateRecipe(image, preferences));
    }
}
