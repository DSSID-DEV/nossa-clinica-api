package com.nossaclinica_api.models.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicos_medicos")
public class ServicosMedico {

    @Id
    @Column(name = "id_servico_medico")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servico_medico_id")
    @SequenceGenerator(name = "seq_servico_medico_id", sequenceName = "seq_servico_medico_id", allocationSize = 1)
    private Long idServicoMedico;

    @OneToOne
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
    private Especialista especialista;

    @OneToOne
    @JoinColumn(name = "id_servico", referencedColumnName = "id_servico")
    private Servico servico;

    private double percentagem;

    private BigDecimal receita;

    private String descricao;

    @Column(name = "preco")
    private BigDecimal preco;

}
