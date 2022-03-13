package com.luis.desafiont.service;

import com.luis.desafiont.dto.VotoDTO;
import org.springframework.stereotype.Service;

@Service
public interface VotoService {

    VotoDTO createVoto(VotoDTO votoDTO);
}
