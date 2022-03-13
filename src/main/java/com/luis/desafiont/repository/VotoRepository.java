package com.luis.desafiont.repository;

import com.luis.desafiont.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface VotoRepository extends JpaRepository<Voto,Long> {

    @Query(value = "select count(*)" +
            "from desafiont.voto" +
            "        join desafiont.sessao sessao on sessao.id = voto.id_sessao" +
            "        where id_associado = :associadoId and id_pauta = :pautaId",nativeQuery = true)
    Integer getVotoByPauta(@PathVariable Long associadoId, @PathVariable Long pautaId);
}
