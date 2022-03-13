package com.luis.desafiont.exception;

import com.luis.desafiont.enums.ErrorResponseEnum;
import com.luis.desafiont.utils.DefaultException;
import com.luis.desafiont.utils.ErrorResponse;

public class AssociadoUnableToVoteException extends DefaultException {

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponseEnum.ASSOCIADO_UNABLE_TO_VOTE;
    }
}
