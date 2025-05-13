package com.cinemaapi.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemaapi.demo.entity.Feedback;
import com.cinemaapi.demo.services.FeedBackService;

@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {
    private final FeedBackService feedbackService;

    public FeedBackController(FeedBackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/filme/{id}")
    public ResponseEntity<Void> addFeedbackToMovie(@PathVariable int id, @RequestBody Feedback feedback) {
        feedbackService.addFeedbackToMovie(id, feedback);
        return ResponseEntity.status(201).build();
    }
}