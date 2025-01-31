package com.nossaclinica_api.models.entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "especialistas")
public class Especialista {

    @Id
    @Column(name = "id_especialista")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_especialista_id")
    @SequenceGenerator(name = "seq_especialista_id", sequenceName = "seq_especialista_id", allocationSize = 1)
    private  Long idEspecialista;

    private String nome;

    @Column(name = "nome_social")
    private String nomeSocial;

    private String conselho;

    private String registro;

    private String uf;

    private String avatar;

    private String telefone;

    @Column(name = "whatsapp")
    private boolean whatsapp;

    @Column(name = "nascido_em")
    private LocalDate nascidoEm;

    private String email;

    private String sexo;

    private boolean ativo;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
}

