package com.nossaclinica_api.reports;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.helper.HeaderFooterHelper;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.utils.FormularioPDF;
import com.nossaclinica_api.reports.utils.TituloPDF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateProntuarioPdfDefaultA4 {

    private final HeaderFooterHelper headerFooterHelper;

    private static final float WIDTH = 100;
    private static final float MAX_HEIGHT_PAGE = 825f;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;


    public ByteArrayOutputStream gerarPdf_A4(DadosParaProntuario inserir, String tipoDocumento) throws IOException {

        headerFooterHelper.setInserir(inserir);
        headerFooterHelper.setTipoDeDocumento(tipoDocumento);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document prontuario = new Document(PageSize.A4, 28f, 28f, 100f, 30f);
        try {
            var write =  PdfWriter.getInstance(prontuario, baos);
            write.setPageEvent(headerFooterHelper);
            prontuario.open();

//          //MONTAR FORMULARIO DO PACIENTE
            PdfPTable formulario = FormularioPDF.montar(inserir, WIDTH, 10f, 1f);
            formulario.setSpacingBefore(800f);
            log.info("Height formulario {}", formulario.getTotalHeight());
            prontuario.add(formulario);
            log.info("Height da pagina com formulario {}", prontuario.getPageSize());

            //MONTAR TIPO DE DOCUMENTO
            var titulo = TituloPDF.montar(tipoDocumento, 4f, 20f);
            log.info("Height titulo {}", formulario.getTotalHeight());
            prontuario.add(titulo);
            log.info("Height da pagina com titulo {}", prontuario.getPageSize());
            prontuario.close();
        } catch (DocumentException e) {
            log.error(Documento.ERROR, e.getMessage());
            new RuntimeException(Documento.ERROR + e.getCause());
        }

        return baos;
    }

}
