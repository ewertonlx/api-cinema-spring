package com.cinemaapi.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cinemaapi.demo.entity.Feedback;
import com.cinemaapi.demo.repository.FeedBackRepository;
import com.cinemaapi.demo.repository.MovieRepository;

@Service
public class FeedBackService {
    private final FeedBackRepository feedbackRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    public FeedBackService(FeedBackRepository feedbackRepository, MovieRepository movieRepository, MovieService movieService) {
        this.movieService = movieService;
        this.feedbackRepository = feedbackRepository;
        this.movieRepository = movieRepository;
    }

    public Feedback addFeedbackToMovie(int movieId, Feedback feedback) {
        var movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            throw new RuntimeException("Filme não encontrado!");
        }
        if(feedback.getUsername() == null || feedback.getUsername().isEmpty()
        || feedback.getComment() == null || feedback.getComment().isEmpty()
        ){
            throw new RuntimeException("Preencha todos os campos!");
        }
        if(feedback.getRating() < 0 || feedback.getRating() > 5){
            throw new RuntimeException("Avaliação deve ser entre 0 e 5!");
        }

        feedback.setMovie(movie);
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(int id, Feedback feedback) {
        var existingFeedback = feedbackRepository.findById(id).orElse(null);
        if (existingFeedback == null) {
            throw new RuntimeException("Feedback não encontrado!");
        }
        if(feedback.getComment() == null || feedback.getComment().isEmpty()){
            throw new RuntimeException("Preencha todos os campos!");
        }
        if(feedback.getRating() < 0 || feedback.getRating() > 5){
            throw new RuntimeException("Avaliação deve ser entre 0 e 5!");
        }

        existingFeedback.setComment(feedback.getComment());
        existingFeedback.setRating(feedback.getRating());
        return feedbackRepository.save(existingFeedback);
    }

    public List<Feedback> getMovieFeedbacks(int id) {
        var movie = movieService.getMovieById(id);
        if( movie == null){
            throw new RuntimeException("Filme não encontrado!");
        }
        return movie.getFeedbacks();
    }
}