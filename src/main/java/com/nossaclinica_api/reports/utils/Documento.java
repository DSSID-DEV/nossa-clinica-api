package com.nossaclinica_api.reports.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import java.util.List;

public class Documento {
    public static final String QUEBRA_LINHA = "\n";
    public static final String ERROR = "Erro ao gerar PDF";
    public static final String RODAPE_ADICIONADO_COM_SUCESSO = "Rodapé adicionado com sucesso!";;
    public static final String TITULO_ADICIONADO_COM_SUCESSO = "Formulário adicionado com sucesso!";
    public static final String CABECALHO_ADICIONADO_COM_SUCESSO = "Cabeçalho adicionado com sucesso!";
    public static final String FORMULARIO_ADICIONADO_COM_SUCESSO = "Formulário adicionado com sucesso!";
    public static final String ERRO_AO_CARREGAR_IMAGE_CABECALHO = "Erro ao carregar a imagem do cabeçalho: {}";
    public static final String A4="DOCUMENTO_A4";
    public static final String TIPO_PRONTUARIO = "PRONTUÁRIO";
    public static final String TIPO_PARECER_CARDIOLOGICO = "PARECER CARDIOLÓGICO";
    public static final String TIPO_ECOCARDIOLOGICO = "ECOCARDIOLOGICO";
    public static final String LAUDO = "LAUDO MÉDICO";
    public static final String ATESTADO = "ATESTADO MÉDICO";
    public static final String FECHAMENTO_DE_CAIXA = "FECHAMENTO DE CAIXA";
    public static final String DECLARACAO_DE_COMPARECIMENTO = "DECLARACAO DE COMPARECIMENTO";
    public static final List<String> REQUERENTES_DE_ASSINATURA = List.of(TIPO_PARECER_CARDIOLOGICO, TIPO_ECOCARDIOLOGICO, LAUDO,
    ATESTADO, DECLARACAO_DE_COMPARECIMENTO);
    public static final String EXTENSAO = ".pdf";
    public static final String CONTENT_DISPOSITION = "inline";
    public static final String CABECALHO = "cabecalho.png";
    public static final String LOGO_MARCA = "";
    public static final float[] LARGURA_DAS_COLUNAS = {10f, 5f};
    public static final float[] LARGURA_DAS_COLUNAS_PARA_EXAME = {15f, 15f};
    public static final float[] LARGURA_DAS_4_COLUNAS = {2f, 15f, 5f, 5f};
    public static final String FIELD_PACIENTE = "Paciente: ";
    public static final String FIELD_DATA_NASCIMENTO = "Data de Nasc.: ";
    public static final String FIELD_ENDERECO = "Endereço: ";
    public static final String FIELD_CONTATO = "Contato: ";
    public static final String FIELD_ESPECIALISTA = "Atendido por: ";
    public static final String FIELD_DATA_DO_ATENDIMENTO = "Atendido em: ";
    public static final String CIDADE = "Lagoa de Itaenga - PE";
    public static final String ENDERECO_CLINICA = "Av. Tancredo Neves, 55 - Centro";
    public static final String CONTATO = "Contatos:";
    public static final String CONTATOS = "Whatsapp: (81) 97320-5636  -  Instagram: https://www.instagram.com/nossaclinica.saude";
    public static final String CNPJ = "CNPJ: 36.938.836/0001-56";
    public static final String ASSINATURA_ADICIONADO_COM_SUCESSO = "Assinatura adicionado com sucesso!";
    public static final String CRM = "%s/%s %s";
    public static final String RQE = "RQE: %s";
    public static final String NOME_CRM = "%s  -  %s";
    public static final Document PAPEL_A5 = new Document(PageSize.A5, 28f, 28f, 60f, 30f);
    public static final Document PAPEL_A4 = new Document(PageSize.A4, 28f, 28f, 100f, 30f);
    public static final String FIELD_PROFISSIONAL = "Profissional: ";
    public static final String MARCADOR = "•";
    public static final String DESCRICAO = "Descrição:";
    public static final String FIELD_IDADE = "Idade:";
    public static final String FIELD_NUMERO_DE_ATENDIMENTO = "Atendimento nº";
    public static final String FIELD_ULTIMA_MESTRUACAO = "Mestruação em:";
    public static final String COLO_UTERINO = "Colo Uterino:";
    public static final String SECRECAO = "Secreção:";
    public static final String ORIFICIO_CERVICAL = "Orifício Cervical:";
    public static final String MUCOSA = "Mucosa:";
    public static final String ZONA_DE_TRANSFORMACAO_NORMAL = "Zona de Transformação Normal::";
    public static final String ECTOPIA = "Ectopia:";
    public static final String JUNCAO_ESCAMO_COLUNAR = "Junção Escamo Colunar:";
    public static final String VASCULARIZACAO = "Vascularização:";
    public static final String ZONA_DE_TRANSFORMACAO_ANORMAL = "Zona de Transformação Anormal:";
    public static final String TESTE_DE_SHILLER = "Teste de Shiller:";
    public static final String OUTROS_ACHADOS = "Outros Achados:";
    public static final String DATA = "Data:";
    public static final String TIPO_LAUDO_COLPOSCOPIO = "LAUDO COLPOSCÓPIO";
    public static final String FIELD_NUMERO_ATENDIMENTO = "Atendimento de nº: ";
    public static final String DECLARACAO = "DECLARAÇÃO";


    public static String gerarNomeDocumento(String paciente) {
        int index = paciente.split("_").length -1;
        return TIPO_PRONTUARIO + "_"
                + paciente.split(" ")[0] + "_" +
                paciente.split("_")[index] + EXTENSAO;
    }

    public static boolean requerAssinaturaMedica(String tipoArquivo) {
        return REQUERENTES_DE_ASSINATURA.contains(tipoArquivo);
    }
}
