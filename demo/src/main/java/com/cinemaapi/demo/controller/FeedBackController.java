package com.cinemaapi.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemaapi.demo.dto.FeedBackDTO;
import com.cinemaapi.demo.services.FeedBackService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/feedbacks") // Definindo o endpoint /feedbacks
public class FeedBackController {
    private final FeedBackService feedbackService;

    // Injeção de dependência do FeedBackService.
    public FeedBackController(FeedBackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Rota GET para obter os feedbacks de determinado filme pelo ID.
    @GetMapping("/filme/{id}")
    public ResponseEntity<Page<FeedBackDTO>> getFeedbacks(@PathVariable int id, Pageable pageable) {
        return ResponseEntity.ok(feedbackService.getMovieFeedbacks(id, pageable));
    }
    
    // Rota POST para adicionar um novo feedback a um filme.
    @PostMapping("/filme/{id}")
    public ResponseEntity<Void> addFeedbackToMovie(@PathVariable int id, @RequestBody @Valid FeedBackDTO feedback) {
        feedbackService.addFeedbackToMovie(id, feedback);
        return ResponseEntity.status(201).build();
    }

    // Rota PUT para atualizar um feedback existente.
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFeedback(@PathVariable int id, @RequestBody @Valid FeedBackDTO feedback) {
        feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.status(204).build();
    }
}