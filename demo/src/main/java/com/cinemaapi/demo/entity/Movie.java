package com.cinemaapi.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Column(name = "release_year")
    private int year;
    private String director;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "movie_categories", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "category")
    private List<MovieCategory> categories = new ArrayList<>();
    
    @OneToMany  (mappedBy = "movie", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Feedback> feedbacks = new ArrayList<>();

    public Movie() {
    }
    public Movie(String name, String description, int year, String director, List<MovieCategory> categories, List<Feedback> feedbacks) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.director = director;
        this.categories = categories;
        this.feedbacks = feedbacks;
    }

    public Movie(String name, String description, int year, String director, List<MovieCategory> categories) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.director = director;
        this.categories = categories;
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<MovieCategory> getCategories() {
        return categories;
    }

    public void setCategory(MovieCategory category) {
        this.categories.add(category);
    }
    
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
    }

}