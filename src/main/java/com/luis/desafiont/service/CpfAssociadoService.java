package com.luis.desafiont.service;

import com.luis.desafiont.dto.StatusCpfAssociado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="https://user-info.herokuapp.com/users/",name="cpfService")
public interface CpfAssociadoService {

    @GetMapping("{cpf}")
    StatusCpfAssociado buscaCpfAssociado(@PathVariable("cpf") String cpf);
}
