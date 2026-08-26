package com.smartrecipe.recipe;

import org.springframework.web.multipart.MultipartFile;

public interface RecipeVisionService {

    Recipe generateRecipe(MultipartFile image, String preferences);
}
