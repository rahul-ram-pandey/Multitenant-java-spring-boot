package com.commutec.shuttle.traccar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TenantNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTenantException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTenantException(String exMsg) {
        super(exMsg);
    }
}