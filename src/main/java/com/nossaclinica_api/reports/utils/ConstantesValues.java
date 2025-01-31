package com.nossaclinica_api.reports.utils;

import java.util.List;

public class ConstantesValues {
    public static final String EMERGENCIA = "Emergência";
    public static final String CONSULTA = "Consulta";
    public static final String EXAME_COMPLEMENTAR = "Exame Complementar";
    public static final List<String> TIPO_ATENDIMENTOS = List.of(EMERGENCIA, CONSULTA, EXAME_COMPLEMENTAR);
    public static final String ATESTADO_MEDICO = """
                                Atesto para os devidos fins, que %s %s  foi %s nesse serviço em %s tendo sido liberado às %s horas.             
            """;

    public static final String CIDADE_E_DATA = """
            Lagoa de Itaenga %s
            """;

    public static final String CID = "CID: ";
    public static final String NOTA = "Nota: ";
    public static final String NOTA_ATESTADO = """
            Este atestado é válido para as finalidades previstas no Art. 86 do ROPS, aprovado pelo decreto nº 50.501 de 14/03/1967 e será expedido para a justificativa de 1 à 15 dias de afastamento do trabalho.
            """;
    public static final String ESPACO = "  ";
    public static final String OBSERVACAO = "Observação:";
    public static final String SOLICITACAO_RESPONDIDA_POR_WHATSAPP =
            "Aguarde a autorização via whatsaap";

    public static final String ABRE_CHAVE = "{ ";
    public static final String FECHA_CHAVE = "}";
    public static final String CHAT_ID = "chatId";
    public static final String CONTENT_TYPE = "contentType";
    public static final String CONTENT = "content";
    public static final String MINE_TYPE = "minetype";
    public static final String DATA = "data";
    public static final String VIRGULA = ",";
    public static final String COD55 = "55";
    public static final String CUS = "@c.us";
    public static final String STRING = "string";

    public static final String APENAS_NUMEROS = "[^\\d]";
    public static final String AVISO_DE_CONSULTA_AGENDADA =
            "Bom dia %s, tudo bem contigo?\nPassando só para lembrar que %s tem %s para %s %s agendado para %s às %s.☺\uFE0F";

    public static final String AVISO_DE_CONSULTA_AGENDADA_SOLICITA_CONFIRMACAO =
            "Bom dia %s, tudo bem contigo?\nPassando para lembrar que %s tem %s para %s %s agendado para %s às %s.\nPoderias me confirmar se virás para o atendimento?";
    public static final boolean CASADO = false;
    public static final String SAUDACAO_DE_BOM_DIA = "Bom dia %s %s!";
    public static final String INICIO_DA_TAREFA_DE_ENVIO_DE_MENSAGEM = "Início da tarefa de envio de mensagem - %s";
    public static final String FIM_DA_TAREFA_DE_ENVIO_DE_MENSAGEM = "Fim da execução da tarefa de envio de mensagem - %s";
    public static final String ENVIDADO_PARA_CAMADA_DE_SERVICO_DO_WHATSAPP = "Encaminhada para camada de serviço do whatsapp";
    public static final String MENSAGEM_ENVIADO_PARA = "Mensagem enviada para %s";
    public static final String SOLICITACAO_ENVIADA_PARA = "Solicitação enviada para %s";
}
