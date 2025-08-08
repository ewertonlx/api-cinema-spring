package com.cinemaapi.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemaapi.demo.dto.MovieDTO;
import com.cinemaapi.demo.entity.MovieCategory;
import com.cinemaapi.demo.services.MovieService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/filmes")
public class MovieController {
    
    public final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
        service.createMovies();
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<MovieDTO>> getAllMovies(Pageable pageable) {
        return ResponseEntity.ok(service.getAllMovies(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id){
        var movie = service.getMovieById(id);
        if(movie == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<MovieDTO>> getMoviesByCategory(@PathVariable String category){
        var movieCategory = MovieCategory.valueOf(category.toUpperCase());
        var movies = service.getMoviesByCategory(movieCategory);
        if(movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody @Valid MovieDTO movie){
        service.createMovie(movie);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable int id, @RequestBody MovieDTO movie){
        service.updateMovie(id, movie);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id){
        service.deleteMovie(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
}