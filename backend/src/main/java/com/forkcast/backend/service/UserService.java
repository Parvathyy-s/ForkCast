package com.forkcast.backend.service;

import com.forkcast.backend.dto.RegisterRequest;
import com.forkcast.backend.entity.User;
import com.forkcast.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest request) {
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .heightCm(request.getHeightCm())
                .weightKg(request.getWeightKg())
                .activityLevel(request.getActivityLevel())
                .goal(request.getGoal())
                .dietPreference(request.getDietPreference())
                .allergies(request.getAllergies())
                .healthConditions(request.getHealthConditions())
                .build();

        return userRepository.save(user);
    }
}