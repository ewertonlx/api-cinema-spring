package com.cinemaapi.demo.dto;

import java.util.List;
import com.cinemaapi.demo.entity.MovieCategory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MovieDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @Min(value = 1800, message = "Ano deve ser maior que 1800")
        @Max(value = 2025, message = "Ano deve ser menor que 2025")
        int year,

        @NotBlank(message = "Diretor é obrigatório")
        String director,

        @NotEmpty(message = "Categorias são obrigatórias")
        List<@NotNull(message = "Categoria inválida")MovieCategory> categories,

        @Valid
        List<@NotNull FeedBackDTO> feedbacks
) {

    public String getName() {
        return name;
    }
    public String setName(String name) {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        return description;
    }

    public int getYear() {
        return year;
    }

    public int setYear(int year) {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String setDirector(String director) {
        return director;
    }

    public List<MovieCategory> getCategories() {
        return categories;
    }

    public List<MovieCategory> setCategories(List<MovieCategory> categories) {
        return categories;
    }

    public List<FeedBackDTO> getFeedbacks() {
        return feedbacks;
    }
    public List<FeedBackDTO> setFeedbacks(List<FeedBackDTO> feedbacks) {
        return feedbacks;
    }
}