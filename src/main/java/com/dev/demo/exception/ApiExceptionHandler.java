package com.dev.demo.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.putIfAbsent(error.getField(), error.getDefaultMessage()));

        return Map.of("message", "Données invalides", "errors", errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleUnreadableBody(HttpMessageNotReadableException exception) {
        String field = findField(exception.getMessage());
        Map<String, Object> response = new LinkedHashMap<>();

        if (field != null) {
            response.put("message", "Le type du champ est incorrect");
            response.put("errors", Map.of(field, "Ce champ doit être une chaîne de caractères"));
        } else {
            response.put("message", "Le JSON est invalide ou mal formé");
        }

        return response;
    }

    private String findField(String message) {
        if (message == null) {
            return null;
        }

        Matcher matcher = Pattern.compile("\\[\\\"([^\\\"]+)\\\"\\]|champ '(\\w+)'|field '(\\w+)'").matcher(message);
        String field = null;
        while (matcher.find()) {
            field = matcher.group(1) != null ? matcher.group(1)
                    : matcher.group(2) != null ? matcher.group(2) : matcher.group(3);
        }
        return field;
    }
}
