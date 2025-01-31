package com.nossaclinica_api.reports.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;
@Slf4j
public class RodapePDF {

    private static final String DIMENSOES_A4 = "595.0x842.0";
    private static final String QUEBRA_LINHA = Documento.QUEBRA_LINHA;
    private static final int TOP = Rectangle.TOP;
    private static final int TIMES_ROMAN = Font.TIMES_ROMAN;
    private  static final int FONT_SIZE_PRINCIPAL_A4 = 10;
    private  static final int FONT_SIZE_ECUNDARIO_A4 = 9;
    private  static final int FONT_SIZE_PRINCIPAL_A5 = 9;
    private  static final int FONT_SIZE_SECUNDARIO_A5 = 8;
    private static final int BOLDITALIC = Font.BOLDITALIC;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;


    public static PdfPTable montar(float width) {
        var tableRodape = new PdfPTable(1);
        tableRodape.setWidthPercentage(width);
        var cellRodape = new PdfPCell();
        cellRodape.setPadding(0);
        cellRodape.setBorderColorTop(new Color(46, 38, 70));
        cellRodape.setBorder(TOP);
        var rodape = new Paragraph();
        rodape.add(new Chunk(Documento.ENDERECO_CLINICA
                .concat(Documento.CIDADE.concat(QUEBRA_LINHA)), new Font(TIMES_ROMAN, 10, BOLDITALIC)));
        rodape.add(new Chunk(Documento.CONTATO
                .concat(Documento.CONTATOS)
                .concat(QUEBRA_LINHA), new Font(TIMES_ROMAN, 9, BOLDITALIC)));
        rodape.add(new Chunk(Documento.CNPJ, new Font(TIMES_ROMAN, 9, BOLDITALIC)));
        rodape.setAlignment(ALIGN_CENTER);
        cellRodape.addElement(rodape);
        tableRodape.addCell(cellRodape);
        log.info(Documento.RODAPE_ADICIONADO_COM_SUCESSO);
        return tableRodape;
    }

    public static void montar(PdfPTable rodape, Rectangle pageSize) {

        rodape.setWidthPercentage(100);
        var cellRodape = new PdfPCell();
        cellRodape.setBorderColorTop(new Color(46, 38, 70));
        cellRodape.setBorder(TOP);
        var paragraph = new Paragraph();
        paragraph.add(new Chunk(Documento.ENDERECO_CLINICA
                .concat(Documento.CIDADE.concat(QUEBRA_LINHA)),
                new Font(TIMES_ROMAN,
                        sizeFontPrimaria(pageSize),
                        BOLDITALIC)));
        paragraph.add(new Chunk(Documento.CONTATO
                .concat(Documento.CONTATOS)
                .concat(QUEBRA_LINHA),
                new Font(TIMES_ROMAN,
                        sizeFontSecundaria(pageSize),
                        BOLDITALIC)));
        paragraph.add(new Chunk(Documento.CNPJ,
                new Font(TIMES_ROMAN,
                        sizeFontSecundaria(pageSize),
                        BOLDITALIC)));
        paragraph.setAlignment(ALIGN_CENTER);
        cellRodape.addElement(paragraph);
        cellRodape.setPadding(0);
        rodape.addCell(cellRodape);
        log.info(Documento.RODAPE_ADICIONADO_COM_SUCESSO);
    }

    private static float sizeFontSecundaria(Rectangle pageSize) {
        return isA4(pageSize) ? FONT_SIZE_ECUNDARIO_A4 : FONT_SIZE_SECUNDARIO_A5;
    }

    private static float sizeFontPrimaria(Rectangle pageSize) {
        return isA4(pageSize) ? FONT_SIZE_PRINCIPAL_A4 : FONT_SIZE_PRINCIPAL_A5;
    }
    private static boolean isA4(Rectangle pageSize) {
        String dimessoesDaPagina = pageSize.toString()
                .replace("Rectangle: ", "")
                .replace(" (rot: 0 degrees)", "");
        return dimessoesDaPagina.equalsIgnoreCase(DIMENSOES_A4);
    }

}
