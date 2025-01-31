package com.nossaclinica_api.reports.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosDoFechamento;
import com.nossaclinica_api.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormularioEntradaSaidaOrcamentoPDF {

    private static final int TIMES_ROMAN = Font.TIMES_ROMAN;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    private static final int ALIGN_RIGHT = Element.ALIGN_RIGHT;
    private static final int ALIGN_LEFT = Element.ALIGN_LEFT;
    private static final int BOLD = Font.BOLD;
    private static final int NO_BORDER = Rectangle.NO_BORDER;
    private static final int TOP = Rectangle.TOP;

    public static PdfPTable montar(DadosDoFechamento inserir, float width, float after, float before) {
        var numColumns = inserir.isMedico() ? 4 : 2;
        var larguraDasColunas = inserir.isMedico() ? Documento.LARGURA_DAS_4_COLUNAS : Documento.LARGURA_DAS_COLUNAS_PARA_EXAME;
        var descritivo = new PdfPTable(numColumns);
        descritivo.setWidthPercentage(width);
        descritivo.setSpacingAfter(after);
        descritivo.setWidths(larguraDasColunas);

        for(var entrada: inserir.getEntradas()) {
            if(inserir.isMedico()) {
                adicionarCelulas(descritivo, Utils.trararStringVazia(entrada.getQtdTipoServico().toString()), false, false, false);
            }
            adicionarCelulas(descritivo, "  "+Utils.trararStringVazia(entrada.getServico()), true,false, false);
            if(inserir.isMedico()) {
                adicionarCelulas(descritivo, Utils.trararStringVazia(entrada.getValorSomado().toString()), false,false, false);
                adicionarCelulas(descritivo, Utils.trararStringVazia(entrada.getValorCalculado().toString()), false,false, false);
            }
        }
        inserir.calcularValorTotal();
        descritivo.setSpacingBefore(2f);
        if(inserir.isMedico()) {
            adicionarCelulas(descritivo, Utils.trararStringVazia(inserir.getQuantidadeDeServico().toString()), false, false, true);
        }
        adicionarCelulas(descritivo, inserir.isMedico() ? "" : "Valor total", !inserir.isMedico(), !inserir.isMedico(), true);
        adicionarCelulas(descritivo, Utils.converteEmMoeda(inserir.getSomaDoServico()), false,!inserir.isMedico(), true);
        if(inserir.isMedico()) {
            adicionarCelulas(descritivo, Utils.converteEmMoeda(inserir.getValorTotalCalculado()), false, true, true);
        }
        log.info(Documento.FORMULARIO_ADICIONADO_COM_SUCESSO);
        descritivo.setSpacingBefore(before);
        return descritivo;
    }
    private static void adicionarCelulas(PdfPTable descricao, String valor, boolean left, boolean header, boolean borderOnTop) throws DocumentException {
        var estiloDaFonte = header ? new Font(TIMES_ROMAN, 12, BOLD) : new Font(TIMES_ROMAN, 12);
        var border = borderOnTop ? TOP : NO_BORDER;
        var fontAlign = left ? ALIGN_LEFT : ALIGN_RIGHT;
        //VALOR DO CAMPO
        valor = valor.equals("0.00") ? "-------" : valor;
        var entrada = new PdfPCell(new Phrase(valor, estiloDaFonte));
        entrada.setBorder(border);
        entrada.setPadding(2f);
        entrada.setHorizontalAlignment(fontAlign);
        descricao.addCell(entrada);
    }
}
