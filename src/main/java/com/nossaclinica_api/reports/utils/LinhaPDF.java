package com.nossaclinica_api.reports.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Slf4j
public class LinhaPDF {

    private static final String QUEBRA_LINHA = Documento.QUEBRA_LINHA;
    private static final int TOP = Rectangle.TOP;
    private static final int TIMES_ROMAN = Font.TIMES_ROMAN;
    private static final int BOLDITALIC = Font.BOLDITALIC;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    public static PdfPTable montar(float width) {
        var tableRodape = new PdfPTable(1);
        tableRodape.setWidthPercentage(width);
        var cellRodape = new PdfPCell();
        cellRodape.setPadding(0);
        cellRodape.setBorderColorTop(new Color(46, 38, 70));
        cellRodape.setBorder(TOP);
        cellRodape.setFixedHeight(1f);
        cellRodape.setFixedHeight(5f);
        tableRodape.addCell(cellRodape);
        log.info(Documento.RODAPE_ADICIONADO_COM_SUCESSO);
        return tableRodape;
    }

}
