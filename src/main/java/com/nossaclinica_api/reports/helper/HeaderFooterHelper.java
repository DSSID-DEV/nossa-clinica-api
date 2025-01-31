package com.nossaclinica_api.reports.helper;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.utils.Cabecalho;
import com.nossaclinica_api.reports.utils.CampoAssinatura;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.utils.RodapePDF;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Component
@RequiredArgsConstructor
public class HeaderFooterHelper extends PdfPageEventHelper {

    private static final String DIMENSOES_A4 = "595.0x842.0";
    private static final String TOP = "top";
    private static final String LEFT = "left";
    private static final float WIDTH_A5 = 400;
    private static final float HEIGHT_A5 = 70;
    private static final float WIDTH_A4 = 700;
    private static final float HEIGHT_A4 = 100;
    private static final String WIDTH = "width";
    private static final String BOTTOM = "bottom";
    private static final String HEIGHT = "height";
    private static final float MARGIN_LEFT_A4 = 26;
    private static final float MARGIN_TOP_A5 = -73;
    private static final float MARGIN_LEFT_A5 = 20;
    private static final float MARGIN_TOP_A4 = -110;
    private static final float MARGIN_BOTTOM_A4 = 60;
    private static final float MARGIN_BOTTOM_A5 = 57;
    private static final int STAR_ROW = 0;
    private static final int END_ROW = -1;
    private static final float MARGIN_LEFT_ASSINATURA_A4 = 200;
    private static final float MARGIN_LEFT_ASSINATURA_A5 = 120.0f;
    private static final float MARGIN_BOTTOM_ASSINATURA_A4 = 90;
    private static final float MARGIN_BOTTOM_ASSINATURA_A5 = 85;

    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;

    private DadosParaProntuario inserir;
    private String tipoDeDocumento;

    private static final int ALIGN_CENTER = Element.ALIGN_CENTER;
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle pageSize = document.getPageSize();

        try {

            //ADICIONAR CABEÇALHO
            var widthHight = obterDimensoesDaLogo(pageSize);
            var margin = obterMargensLeftTop(pageSize);
            var cabecalho = Cabecalho.montar(pathRoot, pathLogomarca, widthHight);
            cabecalho.setAbsolutePosition(margin.get(LEFT), margin.get(TOP));
            canvas.addImage(cabecalho);

            if(inserir != null) {
                adicionarAssinaturaMedica(margin, pageSize, canvas);
            }

            //ADICIONAR RODAPE
            var rodape = new PdfPTable(1);
            rodape.setTotalWidth(pageSize.getWidth() - 70);
            rodape.setLockedWidth(true);
            RodapePDF.montar(rodape, pageSize);
            float marginBottom = obterMarginBottom(pageSize);
            rodape.writeSelectedRows(
                    STAR_ROW, END_ROW, // Todas as linhas
                    pageSize.getLeft() + 36, // Coordenada X (margem esquerda)
                    marginBottom, // Coordenada Y (margem inferior + altura da tabela)
                    canvas
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PdfPTable adicionarAssinaturaMedica(Map<String, Float> margin, Rectangle pageSize, PdfContentByte canvas) {
        if(Documento.requerAssinaturaMedica(tipoDeDocumento)) {
            var assinatura = new PdfPTable(1);
            margin = obterMarginsDaAssinatura(pageSize);
            CampoAssinatura.montar(assinatura, inserir, pageSize);
            assinatura.writeSelectedRows(
                    STAR_ROW, END_ROW,
                    margin.get(LEFT),
                    margin.get(BOTTOM),
                    canvas
            );
            log.info("margin-left da assinatura: {}", pageSize.getLeft() + 120);
        }
        return null;
    }

    private Map<String, Float> obterMarginsDaAssinatura(Rectangle pageSize) {
        String dimessoesDaPagina = pageSize.toString()
                .replace("Rectangle: ", "")
                .replace(" (rot: 0 degrees)", "");
        boolean isA4 = dimessoesDaPagina.equalsIgnoreCase(DIMENSOES_A4);
        Map<String, Float> margin = new HashMap<>();
        margin.put(LEFT, calcular(pageSize.getLeft(),
                isA4 ? MARGIN_LEFT_ASSINATURA_A4 :
                        MARGIN_LEFT_ASSINATURA_A5));
        margin.put(BOTTOM, calcular(pageSize.getBottom(),
                isA4 ? MARGIN_BOTTOM_ASSINATURA_A4 :
                        MARGIN_BOTTOM_ASSINATURA_A5));
        return margin;
    }

    private Float obterMarginBottom(Rectangle pageSize) {
        String dimessoesDaPagina = pageSize.toString()
                .replace("Rectangle: ", "")
                .replace(" (rot: 0 degrees)", "");
        return dimessoesDaPagina.equalsIgnoreCase(DIMENSOES_A4) ?
                calcular(pageSize.getBottom(), MARGIN_BOTTOM_A4)
                : calcular(pageSize.getBottom(), MARGIN_BOTTOM_A5);
    }

    private Map<String, Float> obterMargensLeftTop(Rectangle pageSize) {
        String dimessoesDaPagina = pageSize.toString()
                .replace("Rectangle: ", "")
                .replace(" (rot: 0 degrees)", "");
        return dimessoesDaPagina.equalsIgnoreCase(DIMENSOES_A4) ?
                toMargim(calcular(pageSize.getLeft(), MARGIN_LEFT_A4),
                        calcular(pageSize.getTop(), MARGIN_TOP_A4))
                : toMargim(calcular(pageSize.getLeft(), MARGIN_LEFT_A5),
                calcular(pageSize.getTop(), MARGIN_TOP_A5));
    }

    private float calcular(float pageSizeRef, float marginRef) {
        return pageSizeRef + marginRef;
    }

    private Map<String, Float> toMargim(float marginLeft, float marginTop) {
        Map<String, Float> map = new HashMap<>();
        map.put(LEFT, marginLeft);
        map.put(TOP, marginTop);
        return map;
    }

    private Map<String, Float> obterDimensoesDaLogo(Rectangle pageSize) {
        String dimessoesDaPagina = pageSize.toString()
                .replace("Rectangle: ", "")
                .replace(" (rot: 0 degrees)", "");
        return dimessoesDaPagina.equalsIgnoreCase(DIMENSOES_A4) ?  toMap(WIDTH_A4, HEIGHT_A4)
                : toMap(WIDTH_A5, HEIGHT_A5);
    }

    private Map<String, Float> toMap(float width, float height) {
        Map<String, Float> map = new HashMap<>();
        map.put(WIDTH, width);
        map.put(HEIGHT, height);
        return map;
    }
}
