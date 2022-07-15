package org.ifralou.resttry.errors.exceptions;

import org.ifralou.resttry.errors.RestException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NoSuchResource extends RestException {
    public NoSuchResource(List<Integer> ids) {
        super("No resources with id from the list: " + ids.toString() + " was found!", HttpStatus.NOT_FOUND);
    }
}
