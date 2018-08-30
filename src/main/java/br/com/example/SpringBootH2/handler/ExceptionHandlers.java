package br.com.example.SpringBootH2.handler;

import br.com.example.SpringBootH2.handler.exception.PlayerNotFoundException;
import com.google.common.collect.ImmutableMap;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({PlayerNotFoundException.class, EmptyResultDataAccessException.class})
    private ResponseEntity resourceNotFoundHandler(RuntimeException ex) {
        return ResponseEntity.status(404).body(ImmutableMap.of("message:","Resource not found."));
    }
}
