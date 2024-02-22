package com.jhonatanorz.learning.shared.domain.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("You are not authorized to perform this action");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
