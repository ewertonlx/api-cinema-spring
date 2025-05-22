package com.cinemaapi.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// DTO para representar a classe FeedBack.
public record FeedBackDTO(
        @NotBlank(message = "Nome de usuário é obrigatório")
        String username,

        @NotBlank(message = "Comentário é obrigatório")
        String comment,

        @Min(value = 0, message = "Nota deve estar entre 0 e 5")
        @Max(value = 5, message = "Nota deve estar entre 0 e 5")
        int rating
) {}