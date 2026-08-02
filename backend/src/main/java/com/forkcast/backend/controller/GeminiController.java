package com.forkcast.backend.controller;

import com.forkcast.backend.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @GetMapping("/api/test-gemini")
    public String testGemini() {

        return geminiService.generateResponse(
                "Say hello to the ForkCast project in one sentence."
        );
    }
}