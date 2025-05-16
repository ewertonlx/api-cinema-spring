package com.cinemaapi.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemaapi.demo.dto.MovieDTO;
import com.cinemaapi.demo.dto.FeedBackDTO;
import com.cinemaapi.demo.entity.Feedback;
import com.cinemaapi.demo.entity.Movie;
import com.cinemaapi.demo.entity.MovieCategory;
import com.cinemaapi.demo.exception.NotFoundException;
import com.cinemaapi.demo.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repository;
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }
    
    public void createMovie(Movie movie){
        repository.save(movie);
    }

    public void createMovies(){
        var movie1 = new Movie("Filme legal 1", "Um filme muito legal", 2025, "Diretor legal", List.of(MovieCategory.ACAO, MovieCategory.COMEDIA));
        var movie2 = new Movie("Filme divertido", "Se divirta assistindo", 2000, "Diretor divertido", List.of(MovieCategory.DRAMA, MovieCategory.FANTASIA));
        var movie3 = new Movie("Sistemas de Informação", "Curso de sistemas", 2025, "UNIFACOL", List.of(MovieCategory.DOCUMENTARIO));
        var movie4 = new Movie("Rapaz, tá certo isso?", "Meme famoso na net", 2014, "Influencer", List.of(MovieCategory.TERROR, MovieCategory.ROMANCE));
        var movie5 = new Movie("Bora biu", "Bora fi do biu", 2020, "Biu", List.of(MovieCategory.COMEDIA));
        
        Feedback feedback1 = new Feedback("Tio do Zap", "Filme top!", 5, movie1);
        Feedback feedback2 = new Feedback("Avestruz que te seduz", "Filme daora galera!", 5, movie1);
        Feedback feedback3 = new Feedback("So Vejo Dublado", "Muito emocionante.", 4, movie2);
        movie1.addFeedback(feedback1);
        movie1.addFeedback(feedback2);
        feedback1.setMovie(movie1);
        feedback2.setMovie(movie1);
        movie2.addFeedback(feedback3);
        feedback3.setMovie(movie2);

        repository.save(movie1);
        repository.save(movie2);
        repository.save(movie3);
        repository.save(movie4);
        repository.save(movie5);
    }
    public List<MovieDTO> getAllMovies() {
    return repository.findAll().stream()
        .map(movie -> new MovieDTO(
            movie.getName(),
            movie.getDescription(),
            movie.getYear(),
            movie.getDirector(),
            movie.getCategories(), // List<MovieCategory>
            movie.getFeedbacks().stream()
                .map(f -> new FeedBackDTO(f.getUsername(), f.getComment(), f.getRating()))
                .toList()
        ))
        .toList();
}

    public MovieDTO getMovieById(int id){
        return repository.findById(id)
        .map(m -> new MovieDTO(m.getName(), m.getDescription(), m.getYear(), m.getDirector(), m.getCategories(), 
        m.getFeedbacks().stream().map(f -> new FeedBackDTO(f.getUsername(), f.getComment(), f.getRating())).toList()))
        .orElseThrow(() -> new NotFoundException("Filme não encontrado!"));
    }

   public List<MovieDTO> getMoviesByCategory(MovieCategory category) {
    if (category == null) {
        throw new RuntimeException("Categoria inválida!");
    }

    var movies = repository.findAll().stream()
        .filter(movie -> movie.getCategories().contains(category))
        .map(movie -> new MovieDTO(
            movie.getName(),
            movie.getDescription(),
            movie.getYear(),
            movie.getDirector(),
            movie.getCategories(),
            movie.getFeedbacks().stream()
                .map(f -> new FeedBackDTO(f.getUsername(), f.getComment(), f.getRating()))
                .toList()
        ))
        .toList();

    return movies;
}

    public void updateMovie(int id, MovieDTO movie){
        var movieEntity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Filme não encontrado!"));
        if(movie.name() != null){
            movieEntity.setName(movie.name());
        }
        if(movie.description() != null){
            movieEntity.setDescription(movie.description());
        }
        if(movie.year() != 0){
            movieEntity.setYear(movie.year());
        }
        if(movie.director() != null){
            movieEntity.setDirector(movie.director());
        }
        if(movie.categories() != null){
            movieEntity.getCategories().clear();
            movieEntity.getCategories().addAll(movie.categories());
        }

        repository.save(movieEntity);
    }

    public void deleteMovie(int id){
        Movie movieEntity = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Filme não encontrado!"));
        repository.delete(movieEntity);
    }

}