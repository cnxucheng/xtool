package com.github.cnxucheng.exception;

import java.io.Serial;

public class OutOfMaxSizeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public OutOfMaxSizeException() {

    }
    public OutOfMaxSizeException(String message) {
        super(message);
    }
}
