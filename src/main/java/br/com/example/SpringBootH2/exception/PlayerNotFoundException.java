package br.com.example.SpringBootH2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Player not found.")
public class PlayerNotFoundException extends RuntimeException {
}
