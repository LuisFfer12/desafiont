package com.luis.desafiont.service;

import com.luis.desafiont.dto.StatusCpfAssociado;
import com.luis.desafiont.dto.VotoDTO;
import com.luis.desafiont.entity.Associado;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.enums.StatusCpfAssociadoEnum;
import com.luis.desafiont.exception.AssociadoUnableToVoteException;
import com.luis.desafiont.exception.SessaoExpiredException;
import com.luis.desafiont.repository.VotoRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VotoServiceTest {

    @InjectMocks
    VotoService votoService;

    @Mock
    AssociadoService associadoService;

    @Mock
    PautaService pautaService;

    @Mock
    SessaoService sessaoService;

    @Mock
    VotoRepository votoRepository;

    @Mock
    CpfAssociadoService cpfAssociadoService;

    @Mock
    ModelMapper modelMapper;

    private Associado associado;
    private Pauta pauta;
    private StatusCpfAssociado statusCpf;
    private Sessao sessao;
    private VotoDTO votoDTO;

    @BeforeEach
    void setup() throws ParseException {
        associado = new Associado(1L,"LuisMock","luis@gmail.com","43318698067");
        pauta = new Pauta(1L,"tituloTeste","descriçaoTeste");

        Date dtIni = Calendar.getInstance().getTime();
        Date dtFin = DateUtils.addMinutes(Calendar.getInstance().getTime(),60);

        statusCpf = new StatusCpfAssociado(StatusCpfAssociadoEnum.ABLE_TO_VOTE.toString());
        sessao = new Sessao(1L,60,dtIni,dtFin,pauta);

        when(associadoService.findById(1L)).thenReturn(associado);
        when(pautaService.findById(1L)).thenReturn(pauta);
        when(sessaoService.findById(1L)).thenReturn(sessao);
        when(cpfAssociadoService.buscaCpfAssociado(associado.getCpf())).thenReturn(statusCpf);

        votoDTO = new VotoDTO();
        votoDTO.setAssociadoId(1L);
        votoDTO.setSessaoId(1L);

    }

    @Test
     void shouldReturnAssociadoUnableToVoteException() {
        statusCpf.setStatus(StatusCpfAssociadoEnum.UNABLE_TO_VOTE.toString());
        assertThrows(AssociadoUnableToVoteException.class, () -> {
            votoService.createVoto(votoDTO);
        });
    }

    @Test
    void shouldReturnAssociadoUnableToVoteException2() {
        when(votoService.getVotoByPauta(associado.getId(),sessao.getId())).thenReturn(2);
        assertThrows(AssociadoUnableToVoteException.class, () -> {
            votoService.createVoto(votoDTO);
        });
    }

    @Test
    void shouldReturnSessaoExpiredException(){
        sessao.setDtFim(DateUtils.addMinutes(Calendar.getInstance().getTime(), -5));
        assertThrows(SessaoExpiredException.class, () -> {
            votoService.createVoto(votoDTO);
        });
    }

    @Test
    void shouldOk() {
        statusCpf.setStatus(StatusCpfAssociadoEnum.ABLE_TO_VOTE.name());
        votoService.createVoto(votoDTO);
    }
}
