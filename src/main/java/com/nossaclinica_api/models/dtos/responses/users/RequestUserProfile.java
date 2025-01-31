package com.nossaclinica_api.models.dtos.responses.users;


import lombok.Data;

import java.util.Arrays;

@Data
public class RequestUserProfile {
    private String nome;
    private String avatar;

    public void tratarNomeSobreNome() {
        int sobrenome = Arrays.stream(this.nome.split(" ")).toArray().length - 1;
        String name = this.nome;
        this.nome = name.split(" ")[0] + " " + name.split(" ")[sobrenome];
    }
}
