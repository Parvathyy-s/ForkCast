package com.forkcast.backend.service;

import com.forkcast.backend.dto.RecommendationRequest;
import com.forkcast.backend.entity.Recipe;
import com.forkcast.backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findMatchingRecipes(RecommendationRequest request) {

        return recipeRepository
                .findByDietTypeAndMealTypeAndCaloriesLessThanEqualAndPrepTimeLessThanEqual(
                        request.getDietType(),
                        request.getMealType(),
                        request.getMaxCalories(),
                        request.getMaxPrepTime()
                );
    }
}