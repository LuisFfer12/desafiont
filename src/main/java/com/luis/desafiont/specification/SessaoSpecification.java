package com.luis.desafiont.specification;

import com.luis.desafiont.dto.SessaoDTO;
import com.luis.desafiont.entity.Pauta;
import com.luis.desafiont.entity.Sessao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessaoSpecification {

    public Specification<Sessao> getSessao(SessaoDTO request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getTempoSessao() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tempoSessao"), request.getTempoSessao()));
            }

            if(request.getDtInicio() != null && request.getDtFim() != null){
                predicates.add(criteriaBuilder.between(root.get("dtInicio"),request.getDtInicio(),request.getDtFim()));
            }

            if (request.getPautaId() != null) {
                Join<Sessao, Pauta> pautaJoin = root.join("pauta");
                predicates.add(criteriaBuilder.equal(pautaJoin.get("id"), request.getPautaId()));
            }

            query.orderBy(criteriaBuilder.desc(root.get("tempoSessao")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
