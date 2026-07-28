package com.forkcast.backend.controller;

import com.forkcast.backend.dto.RecommendationRequest;
import com.forkcast.backend.dto.RecommendationResponse;
import com.forkcast.backend.service.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    public RecommendationResponse recommend(
            @Valid @RequestBody RecommendationRequest request
    ) {
        return recommendationService.recommend(request);
    }
}