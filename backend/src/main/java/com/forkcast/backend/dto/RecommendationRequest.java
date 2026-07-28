package com.forkcast.backend.dto;

import com.forkcast.backend.enums.DietType;
import com.forkcast.backend.enums.MealType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationRequest {

    private DietType dietType;

    private MealType mealType;

    @Min(value = 1, message = "Maximum calories must be greater than 0")
    private Integer maxCalories;

    @Min(value = 1, message = "Preparation time must be greater than 0")
    private Integer maxPrepTime;

    private String healthGoal;

    private String allergy;

    private String cuisine;

    private String preferences;
}