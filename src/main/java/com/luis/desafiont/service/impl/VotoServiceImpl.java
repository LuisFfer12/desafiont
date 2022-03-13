package com.luis.desafiont.service.impl;

import com.luis.desafiont.dto.StatusCpfAssociado;
import com.luis.desafiont.dto.VotoDTO;
import com.luis.desafiont.entity.Associado;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.entity.Voto;
import com.luis.desafiont.enums.StatusCpfAssociadoEnum;
import com.luis.desafiont.exception.AssociadoUnableToVoteException;
import com.luis.desafiont.exception.SessaoExpiredException;
import com.luis.desafiont.repository.VotoRepository;
import com.luis.desafiont.service.AssociadoService;
import com.luis.desafiont.service.CpfAssociadoService;
import com.luis.desafiont.service.SessaoService;
import com.luis.desafiont.service.VotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    SessaoService sessaoService;

    @Autowired
    AssociadoService associadoService;

    @Autowired
    CpfAssociadoService cpfAssociadoService;

    @Autowired
    VotoRepository votoRepository;

    @Override
    public VotoDTO createVoto(VotoDTO votoDTO) {

        Sessao sessao = sessaoService.findById(votoDTO.getSessaoId());
        Associado associado = associadoService.findById(votoDTO.getAssociadoId());

        verifyPauta(votoDTO.getAssociadoId(),sessao.getPauta().getId());
        verifyCpf(associado.getCpf());
        verifyDate(sessao);

        Voto votoSaved = saveVoto(votoDTO, sessao, associado);

        VotoDTO votoResponse = mapper.map(votoSaved,VotoDTO.class);
        return votoResponse;
    }

    private void verifyPauta(Long associadoId, Long sessaoId) {
        Integer countVoto = votoRepository.getVotoByPauta(associadoId,sessaoId);
        if(countVoto >= 1){
            throw new AssociadoUnableToVoteException();
        }
    }

    private Voto saveVoto(VotoDTO votoDTO, Sessao sessao, Associado associado) {
        Voto voto = new Voto();
        voto.setAssociado(associado);
        voto.setSessao(sessao);
        voto.setVoto(votoDTO.getVoto());
        Voto votoSaved = votoRepository.save(voto);
        return votoSaved;
    }

    private void verifyDate(Sessao sessao) {
        Date date = new Date();
        if(sessao.getDtFim().before(date)){
            throw new SessaoExpiredException();
        }
    }

    private void verifyCpf(String cpf) {
        StatusCpfAssociado statusCpf = cpfAssociadoService.buscaCpfAssociado(cpf);
        if(statusCpf.getStatus().equals(StatusCpfAssociadoEnum.UNABLE_TO_VOTE.name())){
            throw new AssociadoUnableToVoteException();
        }
    }
}
