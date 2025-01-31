package com.nossaclinica_api.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "analise_colposcipio")
public class AnaliseColposcopio {

    @Id
    @Column(name = "id_analise_colposcopio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_analise_colposcopio_id")
    @SequenceGenerator(name = "seq_analise_colposcopio_id",
            sequenceName = "seq_analise_colposcopio_id", allocationSize = 1)
    private Long idAnaliseColposcopio;

    private String descricao;

    @Column(name = "id_servico")
    private Long idServico;

    @OneToMany(mappedBy = "analiseColposcopio")
    private List<AchadoColposcopio> achados = new ArrayList<>();
}
