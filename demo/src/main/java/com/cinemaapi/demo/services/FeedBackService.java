package com.cinemaapi.demo.services;

import org.springframework.stereotype.Service;
import com.cinemaapi.demo.entity.Feedback;
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

    public Feedback addFeedbackToMovie(int movieId, Feedback feedback) {
        var movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            throw new RuntimeException("Filme não encontrado!");
        }

        feedback.setMovie(movie);
        return feedbackRepository.save(feedback);
    }
}