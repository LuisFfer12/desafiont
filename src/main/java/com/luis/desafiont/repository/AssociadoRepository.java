package com.luis.desafiont.repository;

import com.luis.desafiont.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado,Long> {
}
