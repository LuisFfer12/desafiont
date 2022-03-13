package com.luis.desafiont.service.impl;

import com.luis.desafiont.dto.PautaDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.exception.PautaNotFoundException;
import com.luis.desafiont.repository.PautaRepository;
import com.luis.desafiont.service.PautaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Pauta findById(Long pautaId){
        return pautaRepository.findById(pautaId).orElseThrow(PautaNotFoundException::new);
    }

    @Override
    public PautaDTO createPauta(PautaDTO pautaDTO) {
        Pauta pauta = mapper.map(pautaDTO,Pauta.class);
        Pauta pautaSaved = pautaRepository.save(pauta);
        PautaDTO pautaResponse = mapper.map(pautaSaved,PautaDTO.class);
        return pautaResponse;
    }

    @Override
    public List<PautaDTO> getAllPauta() {
        List<Pauta> pautas = pautaRepository.findAll();
        List<PautaDTO> pautasResponse = mapper.map(pautas,new TypeToken<List<PautaDTO>>() {}.getType());
        return pautasResponse;
    }

    @Override
    public void deleteById(Long pautaId) {
        Pauta pauta = findById(pautaId);
        pautaRepository.deleteById(pautaId);
    }
}




