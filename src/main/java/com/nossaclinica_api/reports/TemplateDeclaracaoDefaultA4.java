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
public class TemplateDeclaracaoDefaultA4 {

    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;


    public File gerarPdf_A4(DadosParaProntuario dadosParaProntuario, String tipoDocumento, Optional<Object> declaracao) {
        float espacamentoDoRodape = 570f;



        return null;
    }
}
