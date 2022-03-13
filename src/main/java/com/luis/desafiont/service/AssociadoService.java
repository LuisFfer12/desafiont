package com.luis.desafiont.service;

import com.luis.desafiont.dto.AssociadoDTO;
import com.luis.desafiont.entity.Associado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssociadoService {

    Associado findById(Long id);

    AssociadoDTO createAssociado(AssociadoDTO associadoDTO);

    List<AssociadoDTO> getAllAssociado();

    void deleteById(Long associadoId);
}
