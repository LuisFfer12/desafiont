package com.luis.desafiont.enums;

import com.luis.desafiont.utils.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorResponseEnum implements ErrorResponse {

    GENERAL_ERROR_VALIDATION(HttpStatus.BAD_REQUEST, "ERR002", "An error occured while validating the information"),
    PAUTA_NOT_FOUND(HttpStatus.BAD_REQUEST,"ERR002" , "Pauta doesn't exists"),
    ASSOCIADO_NOT_FOUND(HttpStatus.BAD_REQUEST,"ERR003" , "Associado doesn't exists"),
    SESSAO_NOT_FOUND(HttpStatus.BAD_REQUEST,"ERR004" , "Sessao doesn't exists"),
    SESSAO_EXPIRED(HttpStatus.BAD_REQUEST,"ERR005" , "Sessao expired"),
    ASSOCIADO_UNABLE_TO_VOTE(HttpStatus.BAD_REQUEST,"ERR005" , "Associado unable to vote");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
