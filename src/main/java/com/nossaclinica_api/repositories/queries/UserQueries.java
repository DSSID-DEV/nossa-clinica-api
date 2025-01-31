package com.nossaclinica_api.repositories.queries;

public class UserQueries {
    public static final String BUSCAR_USUARIO_POR_EMAIL_OR_TELEFONE = """
            select user from User user 
            where user.username = :dadosDeEntrada 
            or user.telefone = :dadosDeEntrada 
            or user.email = :dadosDeEntrada
            """;
    public static final String EXISTS_USERNAME = """
            select count(user) > 0 from User user 
            where user.username = :username
            """;
    public static final String EXISTS_USER = """
            select count(user) > 0 from User user 
            where user.username like :username and user.email = :email and user.telefone = :telefone
            """;
    public static final String EXISTS_USER_WITH_THIS_LOGIN = """
            select case when count(user) = 0 then true else false end from User user 
            where user.username = :login 
            or user.telefone = :login 
            or user.email = :login
            """;
    public static final String BUSCA_ALEATORIA = """
            select user from User user 
            where user.idUser <> :idUser 
            and rownum < 4
            """;
    public static final String BUSCAR_USUARIO_MAIS_ALEATORIOS = """
            select u.* from users u 
            where u.username = :login 
            or u.telefone = :login 
            or u.email = :login 
            union 
            select u.* from users u 
            where u.username <> :login 
            or u.telefone <> :login 
            or u.email <> :login 
            and u.whatsapp is not null or u.telefone is not null 
            limit 5
            """;
    public static final String BUSCAR_USUARIO_POR_USERNAME = """
            select user from User user where user.username = :username
            """;
    public static final String BUSCAR_USUARIO_AUTORIZADOR = """
            select user from User user where user.permissao = 'SUPORTE'
            """;
}
