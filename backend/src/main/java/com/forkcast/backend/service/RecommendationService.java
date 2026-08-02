package com.forkcast.backend.service;

import com.forkcast.backend.dto.RecommendationRequest;
import com.forkcast.backend.dto.RecommendationResponse;
import com.forkcast.backend.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecipeService recipeService;
    private final GeminiService geminiService;

    public RecommendationResponse recommend(RecommendationRequest request) {

        List<Recipe> matchingRecipes =
                recipeService.findMatchingRecipes(request);

        if (matchingRecipes.isEmpty()) {
            return RecommendationResponse.builder()
                    .recommendedRecipe(null)
                    .alternativeRecipes(List.of())
                    .reason("No matching recipes found.")
                    .aiTips("Try changing your preferences.")
                    .build();
        }

        matchingRecipes.sort(
                Comparator.comparing(Recipe::getProtein).reversed()
        );

        List<Recipe> topRecipes = matchingRecipes.stream()
                .limit(5)
                .toList();

        StringBuilder candidateRecipes = new StringBuilder();

        int i = 1;

        for (Recipe recipe : topRecipes) {

            candidateRecipes.append("""
                    
Candidate %d

Name: %s
Meal Type: %s
Cuisine: %s
Calories: %d
Protein: %.1f g

Description:
%s

Ingredients:
%s

----------------------------------------

""".formatted(
                    i++,
                    recipe.getName(),
                    recipe.getMealType(),
                    recipe.getCuisine(),
                    recipe.getCalories(),
                    recipe.getProtein(),
                    recipe.getDescription(),
                    recipe.getIngredients()
            ));
        }

        String prompt = """
You are an expert nutritionist and meal recommendation assistant.

USER PREFERENCES

Diet Type: %s
Preferred Meal: %s
Preferred Cuisine: %s
Health Goal: %s
Allergy: %s
Additional Preferences: %s

==================================================

Candidate Recipes

%s

==================================================

Choose the SINGLE BEST recipe.

Rules:

1. Diet type and allergy are STRICT constraints.
2. Meal type and cuisine are preferences, not strict rules.
3. If an exact breakfast/lunch/dinner isn't available, choose the closest healthy alternative.
4. Never recommend ingredients that conflict with the user's allergy.
5. Never suggest allergen ingredients in your tips.
6. Respect the user's additional preferences.

Return EXACTLY this format:

RECIPE:
<recipe name>

REASON:
<2-3 sentences>

TIPS:
- Tip 1
- Tip 2
- Tip 3
""".formatted(
                request.getDietType(),
                request.getMealType() == null ? "Any" : request.getMealType(),
                request.getCuisine() == null || request.getCuisine().isBlank()
                        ? "Any"
                        : request.getCuisine(),
                request.getHealthGoal() == null || request.getHealthGoal().isBlank()
                        ? "None"
                        : request.getHealthGoal(),
                request.getAllergy() == null || request.getAllergy().isBlank()
                        ? "None"
                        : request.getAllergy(),
                request.getPreferences() == null || request.getPreferences().isBlank()
                        ? "None"
                        : request.getPreferences(),
                candidateRecipes.toString()
        );

        String aiResponse = geminiService.generateResponse(prompt);

        Recipe recommendedRecipe = topRecipes.get(0);

        for (Recipe recipe : topRecipes) {
            if (aiResponse.toLowerCase().contains(recipe.getName().toLowerCase())) {
                recommendedRecipe = recipe;
                break;
            }
        }

        String reason = aiResponse;
        String tips = "";

        if (aiResponse.contains("REASON:") && aiResponse.contains("TIPS:")) {

            String[] reasonSplit = aiResponse.split("REASON:", 2);
            String[] tipsSplit = reasonSplit[1].split("TIPS:", 2);

            reason = tipsSplit[0].trim();
            tips = tipsSplit[1].trim();
        }

        final Recipe selectedRecipe = recommendedRecipe;

        List<Recipe> alternatives = topRecipes.stream()
                .filter(recipe -> !recipe.equals(selectedRecipe))
                .limit(3)
                .toList();

        return RecommendationResponse.builder()
                .recommendedRecipe(selectedRecipe)
                .alternativeRecipes(alternatives)
                .reason(reason)
                .aiTips(tips)
                .build();
    }
}