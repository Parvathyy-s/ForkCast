package com.forkcast.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Height is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Height must be greater than 0")
    private Double heightCm;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    private Double weightKg;

    @NotBlank(message = "Activity level is required")
    private String activityLevel;

    @NotBlank(message = "Goal is required")
    private String goal;

    private String dietPreference;

    private String allergies;

    private String healthConditions;
}