package com.luis.desafiont.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DefaultControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity handle(DefaultException exception) {
        return buildResponse(exception);
    }

    private ResponseEntity<List<ErrorResponseDTO>> buildResponse(DefaultException exception) {
        ErrorResponse errorResponse = exception.getErrorResponse();
        List<ErrorResponseDTO> errorResponseList = new ArrayList<ErrorResponseDTO>();
        if(exception instanceof GeneralExceptionErrorList){
            for(ErrorResponseDTO error : ((GeneralExceptionErrorList) exception).getErrors()) {
                errorResponseList.add(error);
            }
        } else {
            errorResponseList.add(new ErrorResponseDTO(errorResponse.getCode(),errorResponse.getMessage()));
        }
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponseList);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception, Locale locale){
        final String errorCode = "ERRO01";
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(errorCode,exception.getMessage());
        return ResponseEntity.status(status).body(errorResponseDto);
    }

}
