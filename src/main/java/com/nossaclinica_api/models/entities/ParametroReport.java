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
@Table(name = "parametros_reports")
public class ParametroReport {

    @Id
    @Column(name = "id_parametro_report")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_parametro_report_id")
    @SequenceGenerator(name = "seq_parametro_report_id",
            sequenceName = "seq_parametro_report_id", allocationSize = 1)
    private Long idParametroReport;
    private String papel;
    private String largura;
    private String altura;
    @Column(name = "largura_da_logo")
    private String larguraDalogo;
    @Column(name = "margem_top_pagina")
    private String margemTopPagina;
    @Column(name = "margem_direita_pagina")
    private String margemDireitaPagina;
    @Column(name = "margem_rodape_da_pagina")
    private String margemRodapeDaPagina;
    @Column(name = "margem_esquerda_da_pagina")
    private String margemEsquerdaDaPagina;
    @Column(name = "background_color_do_cabecalho")
    private String backGroundColorDoCabecalho;
    @Column(name = "background_color_do_rodape")
    private String backGroundColorDoRodape;
    @Column(name = "pixel_da_linha")
    private String pixelDaLinha;
    @Column(name = "cor_da_linha")
    private String corDaLinha;
}
