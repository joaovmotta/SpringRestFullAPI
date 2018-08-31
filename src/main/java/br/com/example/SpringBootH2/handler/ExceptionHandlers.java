package br.com.example.SpringBootH2.handler;

import br.com.example.SpringBootH2.handler.exception.PlayerNotFoundException;
import com.google.common.collect.ImmutableMap;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import static br.com.example.SpringBootH2.representation.DefaultErrorMessages.RESOURCE_NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlers extends DefaultHandlerExceptionResolver {

    @ExceptionHandler({PlayerNotFoundException.class, EmptyResultDataAccessException.class})
    private ResponseEntity resourceNotFoundHandler(RuntimeException ex) {
        return ResponseEntity.status(404).body(ImmutableMap.of("message", RESOURCE_NOT_FOUND));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String causeMessage = exception.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(ImmutableMap.of("message", causeMessage));
    }
}
