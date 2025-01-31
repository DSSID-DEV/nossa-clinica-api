package com.nossaclinica_api.repositories.queries;

public class ClienteQueries {
    public static final String VERIFICAR_SE_EXISTE_CLIENTE_CADASTRADO = """
            select count(cliente) > 0 from Cliente cliente
            where cliente.nome like :nome 
            and cliente.telefone = :telefone 
            and cliente.nascidoEm = :nascidoEm 
            and cliente.email = :email
            """;
    public static final String VERIFICAR_SE_EXISTE_CLIENTE_DO_ID_CLIENTE = """
            select count(cliente) > 0 from Cliente cliente
            where cliente.idCliente = :idCliente
            """;
    public static final String DESATIVAR_CLIENTE = """
            update Cliente cliente 
            set cliente.ativo = false
            where cliente.idCliente = :idCliente
            """;

    public static final String REATIVAR_CLIENTE = """
            update Cliente cliente 
            set cliente.ativo = true
            where cliente.idCliente = :idCliente
            """;
    public static final String RELACIONAR_CLIENTES_ATIVOS_OU_INATIVOS = """
            select cliente.id_cliente as idCliente, cliente.nome as nome, 
                 cliente.cpf as cpf, cliente.telefone as telefone, 
                 cliente.whatsapp as whatsapp, cliente.nascido_em nascidoEm, 
                 cliente.ativo as ativo 
             from clientes cliente left join 
              depedentes dependente on dependente.id_cliente = cliente.id_cliente 
              where cliente.ativo = :ativo 
              %s                          
            """;

//            """
//            select cliente from Cliente cliente
//            where cliente.ativo = :ativo
//            """;
    public static final String CLAUSULAS_NOME_OR_CPF_OR_EMAIL = """
            and ((cliente.cpf like :dadosDaPesquisa 
            or cliente.nome like :dadosDaPesquisa 
            or cliente.email like :dadosDaPesquisa)  
            or dependente.id_titular = (select c.id_cliente from clientes c
            where c.cpf like :dadosDaPesquisa 
            or c.nome like :dadosDaPesquisa 
            or c.email like :dadosDaPesquisa)
            ) 
            order by cliente.nome
            """;


//            """
//            and cliente.cpf like :dadosDaPesquisa
//            or cliente.nome like :dadosDaPesquisa
//            or cliente.email like :dadosDaPesquisa
//            order by cliente.nome
//            """;

    public static final String BUSCAR_POR_ID = """
            select cliente from Cliente cliente 
            left join fetch cliente.endereco
            where cliente.idCliente = :idCliente
            """;
    public static final String VERIFICAR_TITULARIDADE = """
            select case when count(dependente.id_dependente) > 0 then false else true end as titular from depedentes dependente             
             where dependente.id_cliente = :idCliente 
            """;
}
