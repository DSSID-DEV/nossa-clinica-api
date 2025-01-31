package com.nossaclinica_api.services;

public class Verificar {
    private static final String MEDICO = "MEDICO";
    private static final String CLIENTE = "CLIENTE";
    public static boolean isNotClienteAndIsNotMedico(String permissao) {
        return !permissao.equalsIgnoreCase(CLIENTE) && permissao.equalsIgnoreCase(MEDICO);
    }

    public static boolean isCliente(String permissao) {
        return permissao.equalsIgnoreCase(CLIENTE);
    }

    public static boolean isMedico(String permissao) {
        return permissao.equalsIgnoreCase(MEDICO);
    }
}
