package com.nossaclinica_api.reports;

import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.reports.utils.Documento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateReceituarioDefaultA5 {
    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;

    @Value("${nossaclinica-api.config.documentos}")
    private String diretorio;

    public File gerarPdf_A5(DadosParaProntuario dadosParaProntuario, String tipoDecaracao, Optional<Object> declaracao) {
        float espacamentoDoRodape = 570f;
        var path = new File(diretorio);
        if (!path.exists()) {
            path.mkdir();
        }

        var filePDF = new File(path + "/".concat(tipoDecaracao).concat(Documento.EXTENSAO));


        return filePDF;
    }
}
