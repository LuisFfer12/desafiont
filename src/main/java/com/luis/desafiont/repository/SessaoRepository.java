package com.luis.desafiont.repository;

import com.luis.desafiont.dto.ResultadoVotacaoDTO;
import com.luis.desafiont.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessaoRepository extends JpaRepository<Sessao,Long>, JpaSpecificationExecutor<Sessao> {

    @Query(value="select count(*) FILTER (WHERE voto = 0) as votosNao," +
            "            count(*) FILTER (WHERE voto = 1) as votosSim, " +
            "            count(*) as totalVotos " +
            "from desafiont.voto where id_sessao = :sessaoId",nativeQuery = true)
    ResultadoVotacaoDTO getResultadoVotacao(@Param("sessaoId") Long sessaoId);
}
