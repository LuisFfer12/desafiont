package com.luis.desafiont.service;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.exception.SessaoNotFoundException;
import com.luis.desafiont.kafka.KafkaProducer;
import com.luis.desafiont.kafka.KafkaRunnableTask;
import com.luis.desafiont.repository.SessaoRepository;
import com.luis.desafiont.specification.SessaoSpecification;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    PautaService pautaService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    KafkaProducer producer;

    @Autowired
    SessaoSpecification sessaoSpecification;


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

        taskScheduler.schedule(
                new KafkaRunnableTask("Sessao Finalizada",producer),
                sessaoSaved.getDtFim()
        );

        return sessaoResponse;
    }

    private Sessao saveSessao(SessaoDTO sessaoDTO, Pauta pauta, Sessao sessao) {
        sessao.setPauta(pauta);
        Date horaFimCalculada = DateUtils.addMinutes(sessaoDTO.getDtInicio(), sessaoDTO.getTempoSessao());
        sessao.setDtFim(horaFimCalculada);
        Sessao sessaoSaved = sessaoRepository.save(sessao);
        return sessaoSaved;
    }

    public List<SessaoDTO> getAll(SessaoDTO sessaoRequest) {
        return sessaoRepository.findAll(sessaoSpecification.getSessao(sessaoRequest)).stream()
                .map(item -> mapper.map(item,SessaoDTO.class))
                .collect(Collectors.toList());
    }
}
