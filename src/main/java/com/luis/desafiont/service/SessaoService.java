package com.luis.desafiont.service;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.exception.SessaoNotFoundException;
import com.luis.desafiont.kafka.KafkaProducer;
import com.luis.desafiont.repository.SessaoRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    PautaService pautaService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    KafkaProducer producer;

    public Sessao findById(Long sessaoId) throws SessaoNotFoundException {
        return sessaoRepository.findById(sessaoId).orElseThrow(()->
                new SessaoNotFoundException());
    }

    public ResultadoVotacaoDTO getResultadoVotacao(Long sessaoId) {
        return sessaoRepository.getResultadoVotacao(sessaoId);
    }

    public SessaoDTO createSessao(SessaoDTO sessaoDTO) {

        Pauta pauta = pautaService.findById(sessaoDTO.getPautaId());

        Sessao sessao = mapper.map(sessaoDTO,Sessao.class);
        Sessao sessaoSaved = saveSessao(sessaoDTO, pauta, sessao);

        SessaoDTO sessaoResponse = mapper.map(sessaoSaved,SessaoDTO.class);
        sessaoResponse.setPautaId(sessaoSaved.getPauta().getId());
        producer.sendMessage("Sessao Criada");

        return sessaoResponse;
    }

    private Sessao saveSessao(SessaoDTO sessaoDTO, Pauta pauta, Sessao sessao) {
        sessao.setPauta(pauta);
        Date horaFimCalculada = DateUtils.addMinutes(sessaoDTO.getDtInicio(), sessaoDTO.getTempoSessao());
        sessao.setDtFim(horaFimCalculada);
        Sessao sessaoSaved = sessaoRepository.save(sessao);
        return sessaoSaved;
    }
}
