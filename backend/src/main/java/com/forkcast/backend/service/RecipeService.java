package com.forkcast.backend.service;

import com.forkcast.backend.dto.RecommendationRequest;
import com.forkcast.backend.entity.Recipe;
import com.forkcast.backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> findMatchingRecipes(RecommendationRequest request) {

        List<Recipe> recipes = recipeRepository
                .findByDietTypeAndMealType(
                        request.getDietType(),
                        request.getMealType()
                );

        recipes = recipes.stream()
                .filter(recipe -> recipe.getCalories() <= request.getMaxCalories())
                .collect(Collectors.toList());

        recipes = recipes.stream()
                .filter(recipe -> recipe.getPrepTime() <= request.getMaxPrepTime())
                .collect(Collectors.toList());

        if (request.getCuisine() != null && !request.getCuisine().isBlank()) {
            recipes = recipes.stream()
                    .filter(recipe -> recipe.getCuisine().equalsIgnoreCase(request.getCuisine()))
                    .collect(Collectors.toList());
        }

        if (request.getHealthGoal() != null && !request.getHealthGoal().isBlank()) {
            recipes = recipes.stream()
                    .filter(recipe ->
                            recipe.getHealthGoals() != null &&
                            recipe.getHealthGoals().toLowerCase()
                                    .contains(request.getHealthGoal().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (request.getAllergy() != null && !request.getAllergy().isBlank()) {
            recipes = recipes.stream()
                    .filter(recipe ->
                            recipe.getAllergies() == null ||
                            !recipe.getAllergies().toLowerCase()
                                    .contains(request.getAllergy().toLowerCase()))
                    .collect(Collectors.toList());
        }

        recipes.sort(
                Comparator.comparing(Recipe::getProtein).reversed()
        );

        return recipes;
    }
}