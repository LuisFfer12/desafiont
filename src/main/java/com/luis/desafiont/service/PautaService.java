package com.luis.desafiont.service;

import com.luis.desafiont.dto.PautaDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.exception.PautaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {

    Pauta findById(Long pautaId) throws PautaNotFoundException;

    PautaDTO createPauta(PautaDTO pautaDTO);

    List<PautaDTO> getAllPauta();

    void deleteById(Long pautaId);
}
