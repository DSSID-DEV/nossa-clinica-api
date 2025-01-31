package com.nossaclinica_api.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
    @SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id", allocationSize = 1)
    @Column(name = "id_user")
    private Long idUser;

    private String nome;

    private String username;

    private String email;

    private String senha;

    @Column(name = "ultima_senha")
    private String ultimaSenha;

    @Column(name = "nascido_em")
    private LocalDate nascidoEm;

    private String telefone;

    private boolean whatsapp;

    private String permissao;

    private String avatar;

    private boolean ativo;
}

