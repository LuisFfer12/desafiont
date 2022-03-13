package com.luis.desafiont.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int tempoSessao;

    @Column
    private Date dtInicio;

    @Column
    private Date dtFim;

    @ManyToOne
    @JoinColumn(name="id_pauta")
    private Pauta pauta;

}
