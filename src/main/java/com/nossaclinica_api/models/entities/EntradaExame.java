package com.nossaclinica_api.models.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entradas_de_exame")
public class EntradaExame {

    @Id
    @Column(name = "id_entrada_exame")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_entrada_exame_id")
    @SequenceGenerator(name = "seq_entrada_exame_id",
            sequenceName = "seq_entrada_exame_id", allocationSize = 1)
    private Long idEntradaExame;
    @Column(name = "realizado_por")
    private String realizadoPor;
    @Column(name = "realizado_em")
    private LocalDateTime realizadoEm;
    @Column(name = "ids_exames")
    private String idExames;

    @Column(name = "has_entrada")
    private UUID hashEntrada;

    @OneToOne
    @JoinColumn(name = "id_movimentacao", referencedColumnName = "id_movimentacao")
    private Movimentacao movimentacao;
}
