package com.luis.desafiont.utils;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {
    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}
