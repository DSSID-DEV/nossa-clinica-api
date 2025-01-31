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
@Table(name = "achados_colposcipio")
public class AchadoColposcopio {

    @Id
    @Column(name = "id_achado_colposcopio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_achado_colposcopio_id")
    @SequenceGenerator(name = "seq_achado_colposcopio_id",
            sequenceName = "seq_achado_colposcopio_id", allocationSize = 1)
    private Long idAchadoColposcopio;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_analise_colposcopio", referencedColumnName = "id_analise_colposcopio")
    private AnaliseColposcopio analiseColposcopio;
}
