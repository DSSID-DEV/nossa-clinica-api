package com.nossaclinica_api.reports;

import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TemplateLaudoGenericoDefaultA4 {

    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;

    @Value("${nossaclinica-api.images.logomarca}")
    private String pathLogomarca;

    public File gerarPdf_A4(DadosParaProntuario inserir, String tipoDocumento, Optional<Object> laudoMedico) throws IOException {
        float espacamentoDoRodape = 570f;

        return null;
    }
}
