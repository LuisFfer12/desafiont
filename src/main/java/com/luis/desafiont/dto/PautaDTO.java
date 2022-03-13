package com.luis.desafiont.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

    private Long id;
    private String titulo;
    private String descricao;

}
