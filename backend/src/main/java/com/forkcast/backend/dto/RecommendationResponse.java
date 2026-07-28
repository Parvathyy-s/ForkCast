package com.forkcast.backend.dto;

import com.forkcast.backend.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponse {

    private Recipe recommendedRecipe;

    private List<Recipe> alternativeRecipes;

    private String reason;

    private String aiTips;
}