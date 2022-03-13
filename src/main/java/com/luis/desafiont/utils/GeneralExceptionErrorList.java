package com.luis.desafiont.utils;

import com.luis.desafiont.enums.ErrorResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class GeneralExceptionErrorList extends DefaultException {

    private List<ErrorResponseDTO> errors;

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponseEnum.GENERAL_ERROR_VALIDATION;
    }
}
