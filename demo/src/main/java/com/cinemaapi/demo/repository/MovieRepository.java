package com.cinemaapi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemaapi.demo.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {}