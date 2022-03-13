package com.luis.desafiont.controller;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/sessao")
public class SessaoController {

    @Autowired
    SessaoService sessaoService;

    /**
     * Cria uma nova Sessao
     * @param sessaoDTO
     * @return SessaoDTO
     */
    @Operation(summary = "Cria uma nova Sessao")
    @PostMapping("/v1.0")
    public ResponseEntity<SessaoDTO> createSessao(@RequestBody SessaoDTO sessaoDTO) {
        log.info("Criando uma nova Sessao: {}",sessaoDTO.toString());
        return ResponseEntity.ok( sessaoService.createSessao(sessaoDTO));
    }

    /**
     * Busca Resultados Votacao
     * @param sessaoId
     * @return ResultadoVotacaoDTO
     */
    @Operation(summary = "Busca Resultados da Votaçao")
    @GetMapping("/v1.0/{sessaoId}")
    ResponseEntity<ResultadoVotacaoDTO> getResultadoVotacao(@PathVariable Long sessaoId){
        log.info("Buscando resultados da votaçao da sessao Id: {}",sessaoId);
        return ResponseEntity.ok(sessaoService.getResultadoVotacao(sessaoId));
    }

}
