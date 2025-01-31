package com.nossaclinica_api.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instrucoes")
public class Instrucao {

    @Id
    @Column(name = "id_instrucao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_instrucao_id")
    @SequenceGenerator(name = "seq_instrucao_id", sequenceName = "seq_instrucao_id", allocationSize = 1)
    private Long idInstrucao;

    @Column(name = "tipo_instrucao")
    private String tipoInstrucao;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_exame", referencedColumnName = "id_exame")
    private Exame exame;
}
