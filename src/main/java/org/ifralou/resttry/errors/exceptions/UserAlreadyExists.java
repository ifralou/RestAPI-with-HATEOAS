package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.DTOs.ProductDTO;
import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExists extends RestException {

    public UserAlreadyExists(ProductDTO model) {
        super("User already exists: " + model, HttpStatus.BAD_REQUEST);
    }
}
