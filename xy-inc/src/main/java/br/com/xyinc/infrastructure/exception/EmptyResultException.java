package br.com.xyinc.infrastructure.exception;

import lombok.Getter;

@Getter
public class EmptyResultException extends RuntimeException {

    /**
     * Constroi uma nova exceção.
     *
     * @param dsMessage Uma mensagem detalhada do problema
     */
    public EmptyResultException(String dsMessage) {

        super(dsMessage);
    }
}
