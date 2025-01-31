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
@Table(name = "exames")
public class Exame {

    @Id
    @Column(name = "id_exame")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exame_id")
    @SequenceGenerator(name = "seq_exame_id", sequenceName = "seq_exame_id", allocationSize = 1)
    private Long idExame;

    private String descricao;

    private String sigla;

    @Column(name = "tipo_exame")
    private String tipoExame;

    private BigDecimal custo;

    private BigDecimal preco;

    private String material;

    @Column(name = "meio_de_coleta")
    private String meioDeColeta;

    private String volume;

    private String prazo;

    private String interpretacao;

    @Column(name = "doencas_relacionadas")
    private String doencasRelacionadas;
}
