package com.cinemaapi.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemaapi.demo.dto.MovieDTO;
import com.cinemaapi.demo.entity.MovieCategory;
import com.cinemaapi.demo.services.MovieService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/filmes") // Definindo o endpoint /filmes
public class MovieController {
    
    public final MovieService service;

    // Injeção de dependência do MovieService.
    public MovieController(MovieService service) {
        this.service = service;
        service.createMovies(); // Cria alguns filmes iniciais.
    }
    
    // Rota GET para obter todos os filmes.
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        var movies = service.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    // Rota GET para obter um filme específico pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id){
        var movie = service.getMovieById(id);
        if(movie == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    // Rota GET para filtrar filmes pela categoria.
    @GetMapping("/categoria/{category}")
    public ResponseEntity<List<MovieDTO>> getMoviesByCategory(@PathVariable String category){
        var movieCategory = MovieCategory.valueOf(category.toUpperCase());
        var movies = service.getMoviesByCategory(movieCategory);
        if(movies.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }

    // Rota POST para criar um novo filme.
    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody @Valid MovieDTO movie){
        service.createMovie(movie);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    // Rota PUT para atualizar um filme existente.
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable int id, @RequestBody MovieDTO movie){
        service.updateMovie(id, movie);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    // Rota DELETE para remover um filme pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id){
        service.deleteMovie(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }
}