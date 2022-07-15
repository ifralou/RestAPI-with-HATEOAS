package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;

public class SomeCategoriesAreLost extends RestException {
    public SomeCategoriesAreLost() {
        super("Some categories ids where not found. Please consider revising the id list.", HttpStatus.BAD_REQUEST);
    }
}
