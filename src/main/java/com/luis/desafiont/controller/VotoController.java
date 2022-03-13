package com.luis.desafiont.controller;

import com.luis.desafiont.dto.VotoDTO;
import com.luis.desafiont.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/voto")
public class VotoController {

    @Autowired
    VotoService votoService;

    /**
     * Cria um Voto
     * @param votoDTO
     * @return VotoDTO
     */
    @Operation(summary = "Cria um Voto")
    @PostMapping("/v1.0")
    public ResponseEntity<VotoDTO> createVoto(@RequestBody VotoDTO votoDTO) {
        log.info("Criando um voto {}",votoDTO.toString());
        return  ResponseEntity.ok(votoService.createVoto(votoDTO));
    }
}
