package com.nossaclinica_api.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @Column(name = "id_movimentacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimentacao_id")
    @SequenceGenerator(name = "seq_movimentacao_id",
            sequenceName = "seq_movimentacao_id", allocationSize = 1)
    private Long idMovimentacao;

    @OneToOne
    @JoinColumn(name = "id_agendamento", referencedColumnName = "id_agendamento", nullable = true)
    private Agendamento agendamento;

    private String servico;

    private String prestador;

    private boolean pago;

    @OneToMany(mappedBy = "movimentacao")
    private List<FormaDePagamento> pagamentosCom = new ArrayList<>();

}
