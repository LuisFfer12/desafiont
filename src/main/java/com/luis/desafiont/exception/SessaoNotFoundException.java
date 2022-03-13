package com.luis.desafiont.exception;

import com.luis.desafiont.enums.ErrorResponseEnum;
import com.luis.desafiont.utils.DefaultException;
import com.luis.desafiont.utils.ErrorResponse;

public class SessaoNotFoundException extends DefaultException {

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponseEnum.GENERAL_ERROR_VALIDATION;
    }
}
