package com.nossaclinica_api.models.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
public class Endereco {
    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco_id")
    @SequenceGenerator(name = "seq_endereco_id", sequenceName = "seq_endereco_id"
            , allocationSize = 1)
    private Long idEndereco;

    private String cidade;

    private String uf;

    private String numero;

    private String bairro;

    @Column(name = "tp_logradouro")
    private String tipoLogradouro;

    private String logradouro;

    private String cep;

    private String complemento;
}
