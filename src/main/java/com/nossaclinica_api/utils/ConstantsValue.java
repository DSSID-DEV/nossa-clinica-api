package com.nossaclinica_api.utils;

public class ConstantsValue {
    public static final String STRING_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMERICO = "0123456789";
    public static final String ESPECIAL = "!@#$%^&*()_+";
    public static final String STRING_LOWER = "abcdefghijklmnopqrstuvwxyz";

    public static final String TODOS_OS_CARACTERES = STRING_UPPER + NUMERICO + ESPECIAL + STRING_LOWER;
    public static final String DESCRICAO = "Qual desses é o seu número de whatsapp?";
    public static final String MSG_GENERICA = """
            Segue a senha provisória de acesso à NOSSA CLÍNICA
            ao entrar crie uma nova senha para a sua segurança.
            Senha provisória: %s
            """;
    public static final String DUPLOS_ASTERISTICOS = "**";
    public static final String FULL_LIKE = "%$value%";
    public static final String LIKE_LEFT = "%$value";
    public static final String LIKE_RIGHT = "$value%";
    public static final String VALUE = "$value";
    public static final String ASCII = "[^\\p{ASCII}]";
    public static final String PATH_AVATAR_MEDICO = "/profissionais_medicos/avatas/";
    public static final String PATH_AVATAR_CLIENTE = "/clientes/avatas/";
    public static final String PATH_AVATAR_INTERNO = "/profissionais_internos/avatas/";
    public static final String PATH_AVATAR_EXTERNO = "/colaboradores/avatas/";
    public static final String PATH_AVATAR_SUPORTE = "/suporte/avatas/";
    public static final String PATH_AVATAR_ADMIN = "/admin/avatas/";
    public static final String DEFAULT_FORMAT_AVATAR = "%s%s%s";

}
