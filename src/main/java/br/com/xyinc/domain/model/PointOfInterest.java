package br.com.xyinc.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString.Exclude;

@Entity(name = "point_of_interest")
@Getter
@Setter
public class PointOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Exclude
    private long id;

    @NotBlank(message = "O atributo 'nome' não pode ser vazio")
    @Size(max = 50, message = "O atributo 'nome' deve ter no máximo 50 caracteres")
    private String nome;

    @NotNull(message = "O atributo 'coordenadaX' não pode ser nulo")
    @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaX' deve ser um número inteiro")
    @PositiveOrZero(message = "O atributo 'coordenadaX' deve ser um número inteiro positivo ou zero")
    private Integer coordenadaX;

    @NotNull(message = "O atributo 'coordenadaY' não pode ser nulo")
    @Digits(integer = 10, fraction = 0, message = "O atributo 'coordenadaY' deve ser um número inteiro")
    @PositiveOrZero(message = "O atributo 'coordenadaY' deve ser um número inteiro positivo ou zero")
    private Integer coordenadaY;
}
