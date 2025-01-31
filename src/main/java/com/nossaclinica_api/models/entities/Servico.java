package com.nossaclinica_api.models.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicos")
public class Servico {

    @Id
    @Column(name = "id_servico")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servico_id")
    @SequenceGenerator(name = "seq_servico_id", sequenceName = "seq_servico_id", allocationSize = 1)
    private Long idServico;

    private String nome;
    private String descricao;
    private String orientacao;
}
