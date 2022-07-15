package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;

public class IncompleteInfo extends RestException {
    public IncompleteInfo() {
        super("Lack of fields in request body", HttpStatus.BAD_REQUEST);
    }
}
