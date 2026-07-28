package com.forkcast.backend.repository;

import com.forkcast.backend.entity.Recipe;
import com.forkcast.backend.enums.DietType;
import com.forkcast.backend.enums.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByDietType(DietType dietType);

    List<Recipe> findByMealType(MealType mealType);

    List<Recipe> findByDietTypeAndMealType(
            DietType dietType,
            MealType mealType
    );

    List<Recipe> findByDietTypeAndMealTypeAndCaloriesLessThanEqualAndPrepTimeLessThanEqual(
            DietType dietType,
            MealType mealType,
            Integer calories,
            Integer prepTime
    );
}