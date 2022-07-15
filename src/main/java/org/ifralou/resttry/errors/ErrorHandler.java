package org.ifralou.resttry.errors;

import org.ifralou.resttry.errors.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {
            IncompleteInfo.class,
            UserNotFoundException.class,
            UserAlreadyExists.class,
            NoSuchResource.class,
            SomeCategoriesAreLost.class,
            CategoryAlreadyExists.class
    })
    public ResponseEntity<ErrorMessage> handle(RestException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getStatus());
    }

}
