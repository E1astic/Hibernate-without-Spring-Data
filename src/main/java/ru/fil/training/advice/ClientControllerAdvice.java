package ru.fil.training.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.training.exception.ClientNotFoundException;
import ru.fil.training.model.dto.ResponseBody;

@RestControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ResponseBody> handleException(ClientNotFoundException e) {
        return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
