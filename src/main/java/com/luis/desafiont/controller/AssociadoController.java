package com.luis.desafiont.controller;

import com.luis.desafiont.dto.AssociadoDTO;
import com.luis.desafiont.entity.Associado;
import com.luis.desafiont.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/associado")
public class AssociadoController {

    @Autowired
    AssociadoService associadoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Cria um novo  associado
     * @param associadoDTO
     * @return AssociadoDTO
     */
    @Operation(summary = "Criaçao de Associado")
    @PostMapping("/v1.0")
    public ResponseEntity<AssociadoDTO> createAssociado(@RequestBody AssociadoDTO associadoDTO){
        log.info("Criando um novo Associado:{}",associadoDTO.toString());
        return ResponseEntity.ok(associadoService.createAssociado(associadoDTO));
    }

    /**
     * Busca todos os associados
     * @return List<AssociadoDTO>
     */
    @Operation(summary = "Busca todos os Associados")
    @GetMapping("/v1.0")
    public ResponseEntity<List<AssociadoDTO>> getAllAssociado(){
        log.info("Buscando todos os Associados");
        return ResponseEntity.ok(associadoService.getAllAssociado());
    }

    /**
     * Busca um Associado por Id
     * @param associadoId
     * @return
     */
    @Operation(summary = "Busca um associado por Id")
    @GetMapping("/v1.0/{associadoId}")
    public ResponseEntity<AssociadoDTO> findAssociadoById(@PathVariable Long associadoId){
        log.info("Buscando um associado por Id:{}",associadoId);
        Associado associado = associadoService.findById(associadoId);
        return ResponseEntity.ok(mapper.map(associado,AssociadoDTO.class));
    }

    /**
     * Deleta um associado por Id
     * @param associadoId
     */
    @Operation(summary = "Deleta um associado por ID")
    @DeleteMapping("/v1.0/{associadoId}")
    public void deleteById(@PathVariable Long associadoId){
        log.info("Deletando um Associado Id:{}",associadoId);
        associadoService.deleteById(associadoId);
    }
}
