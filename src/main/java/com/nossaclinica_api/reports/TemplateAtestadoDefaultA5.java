package com.nossaclinica_api.reports;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.helper.HeaderFooterHelper;
import com.nossaclinica_api.reports.conteudos.impl.Atestado;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.reports.conteudos.IConteudo;
import com.nossaclinica_api.reports.utils.TituloPDF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateAtestadoDefaultA5 {

    private final HeaderFooterHelper headerFooterHelper;

    private static final float WIDTH = 100;
    private static final float MAX_HEIGHT_PAGE = 825f;
    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;

    @Value("${nossaclinica-api.config.documentos}")
    private String diretorio;

    public File gerarPdf_A5(DadosParaProntuario inserir, String tipoDocumento, Optional<Object> observacao)throws IOException {
        var path = new File(diretorio);
        if (!path.exists()) {
            path.mkdir();
        }

        headerFooterHelper.setInserir(inserir);
        headerFooterHelper.setTipoDeDocumento(tipoDocumento);

        var filePDF = new File(path + "/".concat(tipoDocumento).concat(Documento.EXTENSAO));
        var documento = Documento.PAPEL_A5;
        var write =  PdfWriter.getInstance(documento, new FileOutputStream(filePDF));

        try {
            write.setPageEvent(headerFooterHelper);
            documento.open();

            //MONTAR TIPO DE DOCUMENTO
            var titulo = TituloPDF.montar(tipoDocumento, 0, 0);
            titulo.getFont().setSize(16);
            titulo.setSpacingAfter(15f);
            documento.add(titulo);
            IConteudo atestado = new Atestado();
            var conteudo = atestado.montar(inserir, observacao);
            documento.add(conteudo);

        } catch (DocumentException e) {
            log.error(Documento.ERROR, e.getMessage());
            new RuntimeException(Documento.ERROR + e.getCause());
        } finally {
            documento.close();
        }
        return filePDF;
    }

}
