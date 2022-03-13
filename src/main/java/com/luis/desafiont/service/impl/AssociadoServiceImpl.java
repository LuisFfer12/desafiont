package com.luis.desafiont.service.impl;

import com.luis.desafiont.dto.AssociadoDTO;
import com.luis.desafiont.entity.Associado;
import com.luis.desafiont.exception.AssociadoNotFoundException;
import com.luis.desafiont.repository.AssociadoRepository;
import com.luis.desafiont.service.AssociadoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Associado findById(Long id) {
        return associadoRepository.findById(id).orElseThrow(()->
                new AssociadoNotFoundException());
    }

    @Override
    public AssociadoDTO createAssociado(AssociadoDTO associadoDTO) {
        Associado associado = mapper.map(associadoDTO,Associado.class);
        Associado associadoSaved = associadoRepository.save(associado);
        return mapper.map(associadoSaved,AssociadoDTO.class);
    }

    @Override
    public List<AssociadoDTO> getAllAssociado() {
        List<Associado> associados = associadoRepository.findAll();
        List<AssociadoDTO> associadosResponse = mapper.map(associados,new TypeToken<List<AssociadoDTO>>() {}.getType());
        return associadosResponse;
    }

    @Override
    public void deleteById(Long associadoId) {
        Associado associado = findById(associadoId);
        associadoRepository.delete(associado);
    }
}
