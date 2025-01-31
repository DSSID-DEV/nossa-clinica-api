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
@Table(name = "especialidades_medica")
public class EspecialidadeMedica {

    @Id
    @Column(name = "id_especialidade_medica")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especialidade_medica_id")
    @SequenceGenerator(name = "seq_especialidade_medica_id",
            sequenceName = "seq_especialidade_medica_id", allocationSize = 1)
    private Long idEspecialidadeMedica;

    @OneToOne
    @JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
    private Especialista especialista;
    @OneToOne
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private Especialidade especialidade;

    private String rqe;

    @Column(name = "area_de_atuacao")
    private String areaDeAtuacao;
}
