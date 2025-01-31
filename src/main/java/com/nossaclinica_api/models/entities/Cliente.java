package com.nossaclinica_api.models.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_id")
    @SequenceGenerator(name = "seq_cliente_id",
            sequenceName = "seq_cliente_id", allocationSize = 1)
    private Long idCliente;

    private String nome;

    private String cpf;

    private String documento;

    @Column(name = "orgao_emissor")
    private String orgaoEmissor;

    private String telefone;

    @Column(name = "whatsapp")
    private boolean whatsapp;

    @Column(name = "nascido_em")
    private LocalDate nascidoEm;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", nullable = false)
    private Endereco endereco;

    private boolean ativo;
}
