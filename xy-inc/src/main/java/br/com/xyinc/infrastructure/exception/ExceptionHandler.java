package br.com.xyinc.infrastructure.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.xyinc.domain.model.exception.ErrorMessage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    private final HttpServletRequest request;

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleValidationException(MethodArgumentNotValidException ex) {

        String requestPath = request.getRequestURI();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errorMessages = fieldErrors
                .stream()
                .map(error -> {
                    String message = error.getDefaultMessage();
                    Object rejectedValue = error.getRejectedValue();

                    return String.format("%s " + "Valor informado: '%s'", message, rejectedValue);
                })
                .collect(Collectors.toList());

        ErrorMessage errorResponse = new ErrorMessage();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(requestPath);
        errorResponse.setErrors(errorMessages);

        return errorResponse;
    }

}
