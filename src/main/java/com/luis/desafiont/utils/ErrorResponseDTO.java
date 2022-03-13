package com.luis.desafiont.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String code;
    private String message;

}
