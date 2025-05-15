package com.cinemaapi.demo.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemaapi.demo.entity.Feedback;
import com.cinemaapi.demo.entity.Movie;
import com.cinemaapi.demo.entity.MovieCategory;
import com.cinemaapi.demo.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repository;
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }
    

    public void createMovie(Movie movie){
        if(movie.getName() == null || movie.getName().isEmpty()
        || movie.getDescription() == null || movie.getDescription().isEmpty()
        || movie.getDirector() == null || movie.getDirector().isEmpty()
        || movie.getCategories() == null || movie.getCategories().isEmpty()
        ){
            throw new RuntimeException("Preencha todos os campos!");
        }
        if(movie.getYear() < 1800 || movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)){
            throw new RuntimeException("O ano deve ser entre 1800 e o ano atual!");
        }
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
    public List<Movie> getAllMovies(){
        return repository.findAll();
    }

    public Movie getMovieById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Movie> getMoviesByCategory(MovieCategory category){
        return repository.findAll().stream()
            .filter(movie -> movie.getCategories().contains(category))
            .toList();
    }

    public void updateMovie(int id, Movie movie){
        var movieEntity = getMovieById(id);
        if(movieEntity != null){
            if(movie.getName() != null){
                movieEntity.setName(movie.getName());
            }
            if(movie.getDescription() != null){
                movieEntity.setDescription(movie.getDescription());
            }
            if(movie.getYear() != 0){
                movieEntity.setYear(movie.getYear());
            }
            if(movie.getDirector() != null){
                movieEntity.setDirector(movie.getDirector());
            }
            if (movie.getCategories() != null && !movie.getCategories().isEmpty()) {
                movieEntity.getCategories().clear();
                for (MovieCategory category : movie.getCategories()) {
                    movieEntity.setCategory(category);
                }
            }
            repository.save(movieEntity);
        }
    }

    public void deleteMovie(int id){
        var movieEntity = getMovieById(id);
        if(movieEntity == null){
            throw new RuntimeException("Filme não encontrado!");
        }
        repository.delete(movieEntity);
    }

}