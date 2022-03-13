package com.luis.desafiont.exception;

import com.luis.desafiont.enums.ErrorResponseEnum;
import com.luis.desafiont.utils.DefaultException;
import com.luis.desafiont.utils.ErrorResponse;

public class AssociadoNotFoundException extends DefaultException {

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponseEnum.ASSOCIADO_NOT_FOUND;
    }
}
