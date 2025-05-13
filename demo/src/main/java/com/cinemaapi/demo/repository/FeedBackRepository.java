package com.cinemaapi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemaapi.demo.entity.Feedback;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {}