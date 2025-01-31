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
@Table(name = "especialidades")
public class Especialidade {

    @Id
    @Column(name = "id_especialidade")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especialidade_id")
    @SequenceGenerator(name = "seq_especialidade_id", sequenceName = "seq_especialidade_id", allocationSize = 1)
    private Long idEspecialidade;

    private String descricao;
}
