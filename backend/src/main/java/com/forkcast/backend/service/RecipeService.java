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

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findMatchingRecipes(RecommendationRequest request) {

        System.out.println("========================================");
        System.out.println("Incoming Request");
        System.out.println("Diet       : " + request.getDietType());
        System.out.println("Meal       : " + request.getMealType());
        System.out.println("Calories   : " + request.getMaxCalories());
        System.out.println("Prep Time  : " + request.getMaxPrepTime());
        System.out.println("Cuisine    : '" + request.getCuisine() + "'");
        System.out.println("HealthGoal : '" + request.getHealthGoal() + "'");
        System.out.println("Allergy    : '" + request.getAllergy() + "'");
        System.out.println("========================================");

       List<Recipe> recipes = recipeRepository.findAll()
        .stream()
        .filter(recipe ->
                recipe.getDietType() == request.getDietType())
        .toList();

        System.out.println("After Diet + Meal = " + recipes.size());

        recipes = recipes.stream()
                .filter(recipe -> recipe.getCalories() <= request.getMaxCalories())
                .collect(Collectors.toList());

        System.out.println("After Calories = " + recipes.size());

        recipes = recipes.stream()
                .filter(recipe -> recipe.getPrepTime() <= request.getMaxPrepTime())
                .collect(Collectors.toList());

        System.out.println("After Prep Time = " + recipes.size());

        if (request.getAllergy() != null && !request.getAllergy().isBlank()) {

            recipes = recipes.stream()
                    .filter(recipe ->
                            recipe.getAllergies() == null ||
                            !recipe.getAllergies()
                                    .toLowerCase()
                                    .contains(request.getAllergy().toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println("After Allergy = " + recipes.size());
        }

        recipes.sort(
                Comparator.comparing(Recipe::getProtein).reversed()
        );

        System.out.println("========================================");
        System.out.println("FINAL MATCHES = " + recipes.size());

        for (Recipe recipe : recipes) {
            System.out.println(recipe.getName());
        }

        System.out.println("========================================");

        return recipes;
    }
}