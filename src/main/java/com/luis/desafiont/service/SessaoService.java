package com.luis.desafiont.service;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.exception.SessaoNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface SessaoService {

    Sessao findById(Long sessaoId) throws SessaoNotFoundException;

    ResultadoVotacaoDTO getResultadoVotacao(Long sessaoId);

    SessaoDTO createSessao(SessaoDTO sessaoDTO);
}
