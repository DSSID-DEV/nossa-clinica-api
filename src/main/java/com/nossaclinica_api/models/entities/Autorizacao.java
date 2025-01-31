package com.nossaclinica_api.models.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autorizacoes")
public class Autorizacao {

    @Id
    @Column(name = "id_autorizacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autorizacao_id")
    @SequenceGenerator(name = "seq_autorizacao_id",
            sequenceName = "seq_autorizacao_id", allocationSize = 1)
    private Long idAutorizacao;

    @Column(name = "hash_autorizacao")
    private String hashAutorizacao;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "solcitado_em")
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:MM:ss")
    private LocalDateTime solicitadoEm;

    private String solicitante;

    private boolean autorizado;

    @Column(name = "autorizado_por")
    private String autorizadoPor;
}
