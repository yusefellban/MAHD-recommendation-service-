package com.MAHD.Recommendation.Service.controller;

import com.MAHD.Recommendation.Service.models.Course;
import com.MAHD.Recommendation.Service.services.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendations/top5")
    public List<Course> getTop5Recommendations() {
        return recommendationService.getTop5Recommendations();
    }
}