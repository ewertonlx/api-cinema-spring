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

    public FeedBackService(FeedBackRepository feedbackRepository, MovieRepository movieRepository) {
        this.feedbackRepository = feedbackRepository;
        this.movieRepository = movieRepository;
    }

    public FeedBackDTO addFeedbackToMovie(int movieId, FeedBackDTO feedback) {
        var movie = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Filme não encontrado!"));
        Feedback feedbackEntity = new Feedback(feedback.username(), feedback.comment(), feedback.rating(), movie);
        Feedback savedFeedback = feedbackRepository.save(feedbackEntity);
        return new FeedBackDTO(savedFeedback.getUsername(), savedFeedback.getComment(), savedFeedback.getRating());
    }

    public FeedBackDTO updateFeedback(int id, FeedBackDTO feedback) {
        var existingFeedback = feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException("Feedback não encontrado!"));

        existingFeedback.setComment(feedback.getComment());
        existingFeedback.setRating(feedback.getRating());
        Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
        return new FeedBackDTO(updatedFeedback.getUsername(), updatedFeedback.getComment(), updatedFeedback.getRating());
    }

    public List<FeedBackDTO> getMovieFeedbacks(int id) {
        var movie = movieRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Filme não encontrado!"));

        return movie.getFeedbacks().stream()
            .map(f -> new FeedBackDTO(f.getUsername(), f.getComment(), f.getRating()))
            .toList();
    }
}