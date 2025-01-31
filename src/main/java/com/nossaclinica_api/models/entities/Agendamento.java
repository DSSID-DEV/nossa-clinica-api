package com.nossaclinica_api.models.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @Column(name = "id_agendamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agendamento_id")
    @SequenceGenerator(name = "seq_agendamento_id", sequenceName = "seq_agendamento_id", allocationSize = 1)
    private Long idAgendamento;

    @OneToOne
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
    private Especialista especialista;

    @OneToOne
    @JoinColumn(name = "id_servico", referencedColumnName = "id_servico")
    private Servico servico;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private Especialidade especialidade;

    @Column(name = "agendado_para")
    private LocalDate agendadoPara;

    @Column(name = "marcado_para")
    private String marcadoPara;

    private String status;

    @Column(name = "ultima_mestruacao")
    private LocalDate ultimaMestruacao;

    @Column(name = "ultimo_atendimento")
    private LocalDate ultimoAtendimento;

    private double desconto;

    private boolean pago;
}
