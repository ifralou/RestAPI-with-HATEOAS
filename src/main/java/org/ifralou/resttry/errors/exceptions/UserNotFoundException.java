package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends RestException {
    public UserNotFoundException(int id) {
        super("User with id: " + id + " not found.", HttpStatus.NOT_FOUND);
    }
}
