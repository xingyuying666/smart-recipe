package com.smartrecipe.recipe;

import java.util.List;

/** A recipe inferred from an uploaded dish photograph. */
public record Recipe(
        String title,
        String description,
        int servings,
        int prepTimeMinutes,
        int cookTimeMinutes,
        List<Ingredient> ingredients,
        List<String> steps,
        List<String> cookingTips,
        List<String> recognitionNotes
) {
    public record Ingredient(String name, String quantity, String note) {
    }
}
