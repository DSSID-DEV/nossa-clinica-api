package com.nossaclinica_api.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "numero_atendimento")
public class NumeroAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_numero_atendimento_id")
    @SequenceGenerator(name = "seq_numero_atendimento_id", sequenceName = "seq_numero_atendimento_id", allocationSize = 1)
    @Column(name = "id_numero_atendimento")
    private Long idNumeroAtendimento;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
    private Especialista especialista;
    @OneToOne
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private Especialidade especialidade;
    @Column(name = "tipo_atendimento")
    private String tipoDeAtendimento;
    @Column(name = "numero_atendimento")
    private Integer numeroAtendimento;
    @Column(name = "data_atendimento")
    private LocalDateTime dataDoAtendimento;
}
