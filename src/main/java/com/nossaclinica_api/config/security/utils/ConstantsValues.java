package com.nossaclinica_api.config.security.utils;

import java.util.List;

public class ConstantsValues {
    public static final List<String> AUTH_WHITELIST = List.of("/auth/**", "/swagger-ui.html", "/v3/api-docs");

    public static final String TYPE_TOKEN = "Bearer ";
    public static final String HIERARCHY = """
            ROLE_SUPORTE > ROLE_ADMINISTRADOR \n 
            ROLE_ADMINISTRADOR > ROLE_RECEPCIONISTA \n
            ROLE_SUPORTE > ROLE_MEDICO \n
            ROLE_SUPORTE > ROLE_CLIENTE
            """;
    public static final String PONTO = ".";
    public static final String PADRAO_USERNAME = "{nome}.{sobrenome}{id}@nossaclinica";
    public static final String DOMINIO_NOSSA_CLINICA = "@nossaclinica";
    public static final String NOME = "{nome}";
    public static final String SOBRENOME = "{sobrenome}";
    public static final String ID = "{id}";
    public static final String PNG = ".png";
}
