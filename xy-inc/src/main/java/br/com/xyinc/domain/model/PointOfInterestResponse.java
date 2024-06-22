package br.com.xyinc.domain.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PointOfInterestResponse(

        @NotBlank(message = "O atributo 'nome' não pode ser vazio")
        @Size(max = 50, message = "O atributo 'nome' deve ter no máximo 50 caracteres")
        String nome,

        @NotNull(message = "O atributo 'coordenadaX' não pode ser nulo")
        @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaX' deve ser um número inteiro")
        int coordenadaX,

        @NotNull(message = "O atributo 'coordenadaY' não pode ser nulo")
        @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaY' deve ser um número inteiro")
        int coordenadaY,

        @NotNull(message = "O atributo 'distancia' não pode ser nulo")
        @Digits(integer = 10, fraction = 0, message = "O atributo 'distancia' deve ser um número inteiro")
        int distancia
) {

}
