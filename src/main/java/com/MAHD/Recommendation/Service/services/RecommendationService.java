package com.MAHD.Recommendation.Service.services;

import com.MAHD.Recommendation.Service.models.Course;
import com.MAHD.Recommendation.Service.models.ExternalApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final WebClient webClient;

    public RecommendationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Course> getTop5Recommendations() {
        ExternalApiResponse response = webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(ExternalApiResponse.class)
                .block();

        if (response != null && response.isSuccess()) {
            return response.getData().stream()
                    .sorted(Comparator.comparingDouble(course -> -course.getRating().getAverage()))
                    .limit(5)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Failed to fetch data from external API");
        }
    }
}
