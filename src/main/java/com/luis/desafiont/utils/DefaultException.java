package com.luis.desafiont.utils;

public abstract class DefaultException extends RuntimeException {
    public abstract ErrorResponse getErrorResponse();
}
