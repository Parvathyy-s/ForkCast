package com.forkcast.backend.entity;

import com.forkcast.backend.enums.DietType;
import com.forkcast.backend.enums.Difficulty;
import com.forkcast.backend.enums.EstimatedCost;
import com.forkcast.backend.enums.MealType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Recipe name is required")
    @Size(max = 150)
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Ingredients are required")
    @Column(name = "ingredients", nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "preparation_steps", columnDefinition = "TEXT")
    private String preparationSteps;

    @Column(name = "preparation_tips", columnDefinition = "TEXT")
    private String preparationTips;

    @Column(name = "nutritional_benefits", columnDefinition = "TEXT")
    private String nutritionalBenefits;

    @Column(name = "tags", columnDefinition = "TEXT")
    private String tags;

    @NotNull(message = "Calories are required")
    @Min(value = 0)
    @Column(name = "calories", nullable = false)
    private Integer calories;

    @NotNull(message = "Protein is required")
    @DecimalMin(value = "0.0")
    @Column(name = "protein", nullable = false)
    private Double protein;

    @NotNull(message = "Carbs are required")
    @DecimalMin(value = "0.0")
    @Column(name = "carbs", nullable = false)
    private Double carbs;

    @NotNull(message = "Fat is required")
    @DecimalMin(value = "0.0")
    @Column(name = "fat", nullable = false)
    private Double fat;

    @NotNull(message = "Preparation time is required")
    @Min(value = 1)
    @Column(name = "prep_time", nullable = false)
    private Integer prepTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type", nullable = false)
    private DietType dietType;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private Difficulty difficulty;

    @NotBlank(message = "Cuisine is required")
    @Column(name = "cuisine", nullable = false, length = 100)
    private String cuisine;

    @Column(name = "health_goals", columnDefinition = "TEXT")
    private String healthGoals;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Enumerated(EnumType.STRING)
    @Column(name = "estimated_cost", nullable = false)
    private EstimatedCost estimatedCost;

    @NotNull(message = "Servings are required")
    @Min(value = 1)
    @Column(name = "servings", nullable = false)
    private Integer servings;

    @Column(name = "image_url", length = 500)
    private String imageUrl;
}