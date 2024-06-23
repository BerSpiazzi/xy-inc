package br.com.xyinc.domain.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PointOfInterestResponse(

        @NotNull(message = "O atributo 'coordenadaX' não pode ser nulo.")
        @PositiveOrZero(message = "O atributo 'coordenadaX' deve ser um número inteiro positivo ou zero.")
        Integer coordenadaX,

        @NotNull(message = "O atributo 'coordenadaY' não pode ser nulo.")
        @PositiveOrZero(message = "O atributo 'coordenadaY' deve ser um número inteiro positivo ou zero.")
        Integer coordenadaY,

        @NotNull(message = "O atributo 'distancia' não pode ser nulo.")
        Integer distancia
) {

}
