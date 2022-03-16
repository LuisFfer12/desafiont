package com.luis.desafiont.service;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.entity.Sessao;
import com.luis.desafiont.exception.SessaoNotFoundException;
import com.luis.desafiont.kafka.KafkaProducer;
import com.luis.desafiont.repository.SessaoRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SessaoServiceTest {


    private Pauta pauta;
    private Sessao sessao;
    private SessaoDTO sessaoDTO;
    private long nonExistingId;
    private long existingId;
    private long dependetId;

    @InjectMocks
    SessaoService sessaoService;

    @Mock
    PautaService pautaService;

    @Mock
    SessaoRepository sessaoRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    KafkaProducer producer;

    @Mock
    TaskScheduler taskScheduler;

    @BeforeEach
    void Setup(){

        existingId = 1L;
        nonExistingId = 1000L;
        dependetId = 4L;

        Date dtIni = Calendar.getInstance().getTime();
        Date dtFin = DateUtils.addMinutes(Calendar.getInstance().getTime(),60);

        pauta = new Pauta(1L,"tituloTeste","descriçaoTeste");
        sessao = new Sessao(1L,60,dtIni,dtFin,pauta);

        sessaoDTO = new SessaoDTO(1L,1L,60,dtIni,null);

        ResultadoVotacaoDTO result = new ResultadoVotacaoDTO() {
            @Override
            public Long getVotosSim() {
                return 3L;
            }

            @Override
            public Long getVotosNao() {
                return 2L;
            }

            @Override
            public Long getTotalVotos() {
                return 5L;
            }
        };

        when(sessaoRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        when(sessaoRepository.findById(existingId)).thenReturn(Optional.ofNullable(sessao));
        when(sessaoRepository.getResultadoVotacao(1L)).thenReturn(result);
        when(pautaService.findById(1L)).thenReturn(pauta);
        when(modelMapper.map(sessaoDTO,Sessao.class)).thenReturn(sessao);
        when(modelMapper.map(sessao,SessaoDTO.class)).thenReturn(sessaoDTO);
        when(sessaoRepository.save(sessao)).thenReturn(sessao);
    }

    @Test
    void shouldReturnSessaoNotFoundException(){
        assertThrows(SessaoNotFoundException.class, () -> {
            sessaoService.findById(nonExistingId);
        });
        verify(sessaoRepository, times(1)).findById(nonExistingId);
    }

    @Test
    void shouldReturnResultadoVotacao(){
        assertDoesNotThrow(() -> {
           sessaoService.getResultadoVotacao(1l);
        });
    }

    @Test
    void shouldCreateSessao(){
        assertDoesNotThrow(() -> {
            sessaoService.createSessao(sessaoDTO);
        });
    }

    @Test
    void shouldFindByIdOk(){
        Sessao result = sessaoService.findById(existingId);
        assertFalse(result == null);
        assertEquals(sessao.getId(),result.getId());
        verify(sessaoRepository, times(1)).findById(existingId);
    }
}
