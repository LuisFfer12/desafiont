package com.luis.desafiont.controller;

import com.luis.desafiont.dto.PautaDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @Autowired
    ModelMapper mapper;

    /**
     * Cria uma nova pauta
     * @param pautaDTO
     * @return PautaDTO
     */
    @Operation(summary = "Criaçao de Pauta")
    @PostMapping("/v1.0")
    public ResponseEntity<PautaDTO> createPauta(@RequestBody PautaDTO pautaDTO){
        log.info("Criando uma nova Pauta:{}",pautaDTO.toString());
        return ResponseEntity.ok(pautaService.createPauta(pautaDTO));
    }

    /**
     * Busca uma pauta por Id
     * @param pautaId
     * @return PautaDTO
     */
    @Operation(summary = "Buscar pauta por Id")
    @GetMapping("/v1.0/{pautaId}")
    public ResponseEntity<PautaDTO> findById(@PathVariable Long pautaId){
        log.info("Buscando uma Pauta por Id {}",pautaId);
        Pauta pauta = pautaService.findById(pautaId);
        return ResponseEntity.ok(mapper.map(pauta,PautaDTO.class));
    }

    /**
     * Deleta uma pauta por Id
     * @param pautaId
     */
    @Operation(summary = "Deleta Pauta por Id")
    @DeleteMapping("/v1.0/{pautaId}")
    public void deleteById(@PathVariable Long pautaId){
        log.info("Deletando uma Pauta por Id {}",pautaId);
        pautaService.deleteById(pautaId);
    }

    /**
     * Busca todas as Pautas
     * @return List<PautaDTO>
     */
    @Operation(summary = "Busca todas as pautas")
    @GetMapping("/v1.0")
    public ResponseEntity<List<PautaDTO>> getAllPauta(){
        log.info("Buscando todas as Pautas");
        return ResponseEntity.ok(pautaService.getAllPauta());
    }
}
