package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;

public class CategoryAlreadyExists extends RestException {
    public CategoryAlreadyExists(String name) {
        super(name + " category is already here, please consider updating existing category.", HttpStatus.BAD_REQUEST);
    }
}
