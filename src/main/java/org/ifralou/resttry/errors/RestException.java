package org.ifralou.resttry.errors;

import org.springframework.http.HttpStatus;

public abstract class RestException extends RuntimeException {
    private HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
