package com.cinemaapi.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemaapi.demo.entity.Feedback;
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
    public ResponseEntity<List<Feedback>> getFeedbacks(@PathVariable int id){
        try {
            var feedbacks = feedbackService.getMovieFeedbacks(id);
            return ResponseEntity.ok(feedbacks);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/filme/{id}")
    public ResponseEntity<String> addFeedbackToMovie(@PathVariable int id, @RequestBody Feedback feedback) {
        try {
            feedbackService.addFeedbackToMovie(id, feedback);
            return ResponseEntity.status(201).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFeedback(@PathVariable int id, @RequestBody Feedback feedback) {
        try {
            feedbackService.updateFeedback(id, feedback);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}