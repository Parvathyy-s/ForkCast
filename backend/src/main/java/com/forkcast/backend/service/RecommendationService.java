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

        Recipe bestRecipe = matchingRecipes.get(0);

        List<Recipe> alternatives = matchingRecipes.stream()
                .skip(1)
                .limit(3)
                .toList();

        return RecommendationResponse.builder()
                .recommendedRecipe(bestRecipe)
                .alternativeRecipes(alternatives)
                .reason("AI explanation will be added here.")
                .aiTips("AI nutrition tips will be added here.")
                .build();
    }
}