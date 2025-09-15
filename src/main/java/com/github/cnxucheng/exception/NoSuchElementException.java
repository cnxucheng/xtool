package com.github.cnxucheng.exception;

import java.io.Serial;

public class NoSuchElementException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public NoSuchElementException() {
    }
    public NoSuchElementException(String message) {
        super(message);
    }
}
