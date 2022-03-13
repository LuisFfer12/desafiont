package com.luis.desafiont.entity;

import com.luis.desafiont.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_associado")
    private Associado associado;

    @ManyToOne
    @JoinColumn(name="id_sessao")
    private Sessao sessao;

    @Column
    private VotoEnum voto;
}
