package com.nossaclinica_api.reports.utils;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

@Slf4j
public class CampoAssinatura {

    private static final float TOTAL_WIDTH_A4 = 195.0f;
    private static final Color BLACK = Color.BLACK;
    private static final int LINE_TOP = Rectangle.TOP;
    private static final int NO_BORDER = Rectangle.NO_BORDER;
    private static final int FONT_DEFAULT = Font.TIMES_ROMAN;
    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;


    public static PdfPTable montar(PdfPTable assinatura, DadosParaProntuario inserir, Rectangle pageSize) {
        var crm = String
                .format(Documento.CRM,
                        inserir.getConselho(),
                        inserir.getUf(),
                        inserir.getRegistro());
        var rqe = String.format(Documento.RQE, inserir.getRqes()
                .replace(" ", " - "));

        var nomeCrm = String.format(Documento.NOME_CRM, inserir.getEspecialista(), crm);

        assinatura.setWidthPercentage(45f);
        assinatura.setHorizontalAlignment(ALIGN_CENTER);
        var cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setBorder(LINE_TOP);
        cell.setBorderColorTop(BLACK);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        cell.setPhrase(new Phrase(nomeCrm, new Font(FONT_DEFAULT, 10)));
        assinatura.addCell(cell);
        if(StringUtils.isNotBlank(inserir.getRqes().trim())) {
            cell = new PdfPCell();
            cell.setBorder(NO_BORDER);
            cell.setHorizontalAlignment(ALIGN_CENTER);
            cell.setPhrase(new Phrase(rqe, new Font(FONT_DEFAULT, 8)));
            assinatura.addCell(cell);
        }
        assinatura.setLockedWidth(true);
        log.info("Total Width: {}", pageSize.getWidth() - 400);
        assinatura.setTotalWidth(TOTAL_WIDTH_A4);
        log.info(Documento.ASSINATURA_ADICIONADO_COM_SUCESSO);
        return assinatura;
    }
}
