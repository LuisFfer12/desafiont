package com.luis.desafiont.service;

import com.luis.desafiont.dto.AssociadoDTO;
import com.luis.desafiont.entity.Associado;
import com.luis.desafiont.exception.AssociadoNotFoundException;
import com.luis.desafiont.repository.AssociadoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    ModelMapper mapper;

    public Associado findById(Long id) {
        return associadoRepository.findById(id).orElseThrow(()->
                new AssociadoNotFoundException());
    }

    public AssociadoDTO createAssociado(AssociadoDTO associadoDTO) {
        Associado associado = mapper.map(associadoDTO,Associado.class);
        Associado associadoSaved = associadoRepository.save(associado);
        return mapper.map(associadoSaved,AssociadoDTO.class);
    }

    public List<AssociadoDTO> getAllAssociado() {
        List<Associado> associados = associadoRepository.findAll();
        List<AssociadoDTO> associadosResponse = mapper.map(associados,new TypeToken<List<AssociadoDTO>>() {}.getType());
        return associadosResponse;
    }

    public void deleteById(Long associadoId) {
        Associado associado = findById(associadoId);
        associadoRepository.delete(associado);
    }
}
