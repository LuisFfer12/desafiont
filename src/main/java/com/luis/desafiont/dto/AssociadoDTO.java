package com.luis.desafiont.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
}
