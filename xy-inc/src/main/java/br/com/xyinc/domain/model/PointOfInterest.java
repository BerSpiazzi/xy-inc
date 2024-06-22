package br.com.xyinc.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.ToString.Exclude;

@Entity(name = "point_of_interest")
public record PointOfInterest(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Exclude
        long id,

        @NotBlank(message = "O atributo 'nome' não pode ser vazio")
        @Size(max = 50, message = "O atributo 'nome' deve ter no máximo 50 caracteres")
        String nome,

        @NotNull(message = "O atributo 'coordenadaX' não pode ser nulo")
        @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaX' deve ser um número inteiro")
        int coordenadaX,

        @NotNull(message = "O atributo 'coordenadaY' não pode ser nulo")
        @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaY' deve ser um número inteiro")
        int coordenadaY) {

}
