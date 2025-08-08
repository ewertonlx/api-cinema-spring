package com.cinemaapi.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public FeedBackDTO updateFeedback(int movieId, FeedBackDTO feedback) {
        var existingFeedback = feedbackRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Feedback não encontrado!"));

        existingFeedback.setComment(feedback.comment());
        existingFeedback.setRating(feedback.rating());
        Feedback updatedFeedback = feedbackRepository.save(existingFeedback);
        return new FeedBackDTO(updatedFeedback.getUsername(), updatedFeedback.getComment(), updatedFeedback.getRating());
    }

    public Page<FeedBackDTO> getMovieFeedbacks(int movieId, Pageable pageable) {
        movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException("Filme não encontrado!"));

        Page<Feedback> feedbacks = feedbackRepository.findByMovieId(movieId, pageable);
        return feedbacks.map(feedback -> new FeedBackDTO(feedback.getUsername(), feedback.getComment(), feedback.getRating()));
    }

    public void deleteFeedback(int id) {
        var feedback = feedbackRepository.findById(id).orElseThrow(() -> new NotFoundException("Feedback não encontrado!"));
        feedbackRepository.delete(feedback);
    }
}