package com.cinemaapi.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemaapi.demo.dto.FeedBackDTO;
import com.cinemaapi.demo.services.FeedBackService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {
    private final FeedBackService feedbackService;

    public FeedBackController(FeedBackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/filme/{id}")
    public ResponseEntity<List<FeedBackDTO>> getFeedbacks(@PathVariable int id){
        var feedbacks = feedbackService.getMovieFeedbacks(id);
        return ResponseEntity.ok(feedbacks);
    }
    
    @PostMapping("/filme/{id}")
    public ResponseEntity<Void> addFeedbackToMovie(@PathVariable int id, @RequestBody FeedBackDTO feedback) {
        feedbackService.addFeedbackToMovie(id, feedback);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFeedback(@PathVariable int id, @RequestBody FeedBackDTO feedback) {
        feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.status(204).build();
    }
}