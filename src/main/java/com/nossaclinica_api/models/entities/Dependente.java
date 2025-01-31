package com.nossaclinica_api.models.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "depedentes")
public class Dependente {

    @Id
    @Column(name = "id_dependente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dependente_id")
    @SequenceGenerator(name = "seq_dependente_id",
            sequenceName = "seq_dependente_id", allocationSize = 1)
    private Long idDependente;

    @OneToOne
    @JoinColumn(name = "id_titular", referencedColumnName = "id_cliente")
    private Cliente titular;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente dependente;

    @Column(name = "grau_parentesco")
    private String grauParetesco;

}
