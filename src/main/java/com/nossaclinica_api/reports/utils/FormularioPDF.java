package com.nossaclinica_api.reports.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class FormularioPDF {
    private static final int TIMES_ROMAN = Font.TIMES_ROMAN;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    private static final int ALIGN_RIGHT = Element.ALIGN_RIGHT;
    private static final int ALIGN_LEFT = Element.ALIGN_LEFT;
    private static final int LABEL = 0;
    private static final int CAMPO = 1;
    private static final int BOLD = Font.BOLD;
    private static final int NO_BORDER = Rectangle.NO_BORDER;
    private static final String VAZIO = "          ";

    public static PdfPTable montar(DadosParaProntuario inserir, float width, float after, float before) throws DocumentException {
        var formulario = new PdfPTable(2);
        formulario.setWidthPercentage(width);
        formulario.setSpacingAfter(after);

        var larguraDasColunas = Documento.LARGURA_DAS_COLUNAS;
        formulario.setWidths(larguraDasColunas);

        var paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_PACIENTE, Formatar.string(inserir.getPaciente()), 0);
        var cell = new PdfPCell();
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_DATA_NASCIMENTO, Formatar.date(inserir.getNascidoEm()), 0);
        cell = new PdfPCell();
        paragraph.setAlignment(ALIGN_RIGHT);
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_ENDERECO, Formatar.string(inserir.getTipoLogradouro() + " "
                + inserir.getLogradouro() + ", "+ inserir.getNumero()), 0);
        cell = new PdfPCell();
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_CONTATO, inserir.getContato(), 0);
        cell = new PdfPCell();
        paragraph.setAlignment(ALIGN_RIGHT);
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        cell.setHorizontalAlignment(ALIGN_RIGHT);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_ESPECIALISTA, obterNomeEspecialista(inserir), 0);
        cell = new PdfPCell();
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_DATA_DO_ATENDIMENTO, Formatar.date(LocalDate.now()), 0);
//        adicionarCelulas(paragraph, Documento.FIELD_NUMERO_ATENDIMENTO, (inserir.getNumeroDeAtendimento()), 1);
        cell = new PdfPCell();
        paragraph.setAlignment(ALIGN_RIGHT);
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        cell.setHorizontalAlignment(ALIGN_RIGHT);
        formulario.addCell(cell);

        paragraph = new Paragraph();
        adicionarCelulas(paragraph, Documento.FIELD_NUMERO_ATENDIMENTO, inserir.getNumeroDeAtendimento() +"/"+Utils.obterAnoCorrente(), 0);
        cell = new PdfPCell();
        cell.setColspan(2);
        paragraph.setAlignment(ALIGN_RIGHT);
        cell.addElement(paragraph);
        cell.setBorder(NO_BORDER);
        cell.setHorizontalAlignment(ALIGN_RIGHT);
        formulario.addCell(cell);

//        adicionarCelulas(formulario, Documento.FIELD_PACIENTE, Formatar.string(inserir.getPaciente()), true);
//        adicionarCelulas(formulario, Documento.FIELD_DATA_NASCIMENTO, Formatar.date(inserir.getNascidoEm()), false);
//        adicionarCelulas(formulario, Documento.FIELD_CONTATO, inserir.getContato(), false);
//
//        adicionarCelulas(formulario, Documento.FIELD_ENDERECO, Formatar.string(inserir.getTipoLogradouro() + " "
//                + inserir.getLogradouro() + ", "+ inserir.getNumero()), true);
//        adicionarCelulas(formulario, Documento.FIELD_NUMERO_ATENDIMENTO, (inserir.getNumeroDeAtendimento()), false);
//        adicionarCelulas(formulario, Documento.FIELD_DATA_DO_ATENDIMENTO, Formatar.date(LocalDate.now()), false);
//
//        adicionarCelulas(formulario, Documento.FIELD_ESPECIALISTA, inserir.getEspecialista(), true);

        log.info(Documento.FORMULARIO_ADICIONADO_COM_SUCESSO);
        formulario.setSpacingBefore(before);
        return formulario;
    }

    private static String obterNomeEspecialista(DadosParaProntuario inserir) {
        return inserir.isMedico() ? Utils.definiPronomeDosProfissionais(inserir.getSexoEspecialista(),false)
                .concat(inserir.getEspecialista()) : inserir.getEspecialista();
    }

    private static void adicionarCelulas(Paragraph paragraph, String field, String valor, int posicao) throws DocumentException {
        var negrito = new Font(TIMES_ROMAN, 12, BOLD);
        var normal = new Font(TIMES_ROMAN, 12);

        //NOME DO CAMPO
        var label = new Chunk(field, negrito);
        //VALOR DO CAMPO
        var campo = new Chunk(Utils.trararStringVazia(valor), normal);
        var entrada = new Paragraph();

        entrada.add(LABEL, label);
        entrada.add(CAMPO, campo);
        var phrase = new Phrase();
        phrase.add(entrada);
        paragraph.add(posicao, phrase);
    }

//    private static void adicionarCelulas(PdfPTable formulario, String field, String valor, boolean esquerda) throws DocumentException {
//        var negrito = new Font(TIMES_ROMAN, 12, BOLD);
//        var normal = new Font(TIMES_ROMAN, 12);
//        var alinhamento = esquerda ? ALIGN_LEFT : ALIGN_RIGHT;
//
//        //NOME DO CAMPO
//        var label = new Chunk(field, negrito);
////        label.setBorder(NO_BORDER);
////        label.setPadding(0);
////        label.setHorizontalAlignment(alinhamento);
////        formulario.addCell(label);
//
//
//        //VALOR DO CAMPO
//        var entrada = new Chunk(Utils.trararStringVazia(valor), normal);
////        entrada.setBorder(NO_BORDER);
////        entrada.setPadding(0);
////        entrada.setHorizontalAlignment(alinhamento);
////        formulario.addCell(entrada);
//
////        COLUNA FORMATADO
////        var coluna = montarColuna(label, entrada, esquerda);
//        var cell = new PdfPCell();
//        var paragraph = new Paragraph();
//        paragraph.add(LABEL, label);
//        paragraph.add(ENTRADA, entrada);
//        cell.addElement(paragraph);
//        cell.setBorder(NO_BORDER);
//        formulario.addCell(cell);
//    }

    private static PdfPCell montarColuna(PdfPCell label, PdfPCell entrada, boolean esquerda) throws DocumentException {
        var alinhamento = esquerda ? ALIGN_LEFT : ALIGN_RIGHT;
        var larguraDaColuna = new float[]{2.2f, 10f};
        var coluna = new PdfPTable(2);
        if(esquerda) {
            coluna.setWidths(larguraDaColuna);
        }
        coluna.setWidthPercentage(100);
        coluna.setSpacingBefore(0f);
        coluna.setSpacingAfter(0f);
        label.setHorizontalAlignment(alinhamento);
        label.setBorder(NO_BORDER);
        label.setPadding(0);
        coluna.addCell(label);
        entrada.setBorder(NO_BORDER);
        coluna.addCell(entrada);
        coluna.setHorizontalAlignment(alinhamento);
        var cell = new PdfPCell();
        cell.addElement(coluna);
        cell.setBorder(NO_BORDER);
        cell.setPadding(0);
        return cell;
    }

}
