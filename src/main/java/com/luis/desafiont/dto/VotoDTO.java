package com.luis.desafiont.dto;

import com.luis.desafiont.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDTO {
    private Long associadoId;
    private Long sessaoId;
    private VotoEnum voto;
}
