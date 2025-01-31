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
@Table(name = "users_clientes")
public class UsuarioCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_cliente_id")
    @SequenceGenerator(name = "seq_user_cliente_id", sequenceName = "seq_user_cliente_id", allocationSize = 1)
    @Column(name = "id_user_cliente_id")
    private Long idUserCliente;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    private boolean responsavel;

    @Column(name = "grau_parentesco")
    private String grauParentesco;

    private boolean cobertado;

}
