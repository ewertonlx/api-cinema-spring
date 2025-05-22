package com.cinemaapi.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemaapi.demo.dto.FeedBackDTO;
import com.cinemaapi.demo.entity.Feedback;
import com.cinemaapi.demo.exception.NotFoundException;
import com.cinemaapi.demo.repository.FeedBackRepository;
import com.cinemaapi.demo.repository.MovieRepository;

@Service
public class FeedBackService {
    private final FeedBackRepository feedbackRepository;
    private final MovieRepository movieRepository;

    // Injeção de dependência do FeedbackRepository e MovieRepository.
    public FeedBackService(FeedBackRepository feedbackRepository, MovieRepository movieRepository) {
        this.feedbackRepository = feedbackRepository;
        this.movieRepository = movieRepository;
    }

    // Serviço que busca um filme pelo ID e adiciona um novo feedback a ele.
    public FeedBackDTO addFeedbackToMovie(int movieId, FeedBackDTO feedback) {
        var movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Filme não encontrado!"));
        Feedback feedbackEntity = new Feedback(feedback.username(), feedback.comment(), feedback.rating(), movie);
        Feedback savedFeedback = feedbackRepository.save(feedbackEntity);
        return new FeedBackDTO(savedFeedback.getUsername(), savedFeedback.getComment(), savedFeedback.getRating());
    }

    // Serviço que busca um feedback pelo ID e faz as devidas atualizações.
    public FeedBackDTO updateFeedback(int movieId, FeedBackDTO feedback) {
        var existingFeedback = feedbackRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Feedback não encontrado!"));

        existingFeedback.setComment(feedback.comment());
        existingFeedback.setRating(feedback.rating());
        Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
        return new FeedBackDTO(updatedFeedback.getUsername(), updatedFeedback.getComment(), updatedFeedback.getRating());
    }

    // Serviço que busca um filme pelo ID e retorna os feedbacks desse filme.
    public List<FeedBackDTO> getMovieFeedbacks(int movieId) {
        var movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new NotFoundException("Filme não encontrado!"));

        return movie.getFeedbacks().stream()
            .map(f -> new FeedBackDTO(f.getUsername(), f.getComment(), f.getRating()))
            .toList();
    }

    // Serviço que busca um filme pelo ID e faz o delete dele na database.
    public void deleteFeedback(int id) {
        var feedback = feedbackRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Feedback não encontrado!"));
        feedbackRepository.delete(feedback);
    }
}