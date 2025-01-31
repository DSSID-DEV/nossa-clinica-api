package com.nossaclinica_api.reports.utils;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;

@Slf4j
public class TituloPDF {
    private static final int TIMES_ROMAN = Font.TIMES_ROMAN;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    private static final Color BLACK = Color.BLACK;
    private static final int BOLD = Font.BOLD;

    public static Paragraph montar(String tipoArquivo, float before, float after) {
        var titulo = new Paragraph(tipoArquivo, new Font(TIMES_ROMAN, 18, BOLD, BLACK));
        titulo.setAlignment(ALIGN_CENTER);
        titulo.setSpacingBefore(before);
        titulo.setSpacingAfter(after);
        log.info(Documento.TITULO_ADICIONADO_COM_SUCESSO);
        return titulo;
    }
}
