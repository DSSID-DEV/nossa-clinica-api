package com.nossaclinica_api.controllers;

import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.services.DocumentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService service;

    @Value("${nossaclinica-api.config.documentos}")
    private String diretorio;

    @GetMapping(value = "/gerar/prontuario/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarProntuarioPdf_A4(@PathVariable("idAgendamento") Long idAgendamento) {

        var documentoPdf =
            service.gerarProntuarioPdf_A4(idAgendamento);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }

    @GetMapping(value = "/gerar/colposcopio/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarProntuarioColposcopioPdfA4(@PathVariable("idAgendamento") Long idAgendamento) {
        var documento =
                        service.emitirPorntuarioColposcopio(idAgendamento);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documento);
    }

    @GetMapping(value = "/gerar/ecg/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarParecerCardiologicoPdf_A4(@PathVariable("idAgendamento") Long idAgendamento) {

        var documentoPdf =
                service.gerarParecerCardiologicoPdf_A4(idAgendamento, null);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }

    @GetMapping(value = "/gerar/ecocardiograma/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarEcocardiogramaPdfA4(@PathVariable("idAgendamento") Long idAgendamento, @RequestBody Optional<Object> laudo) {

        var documento =
                service.gerarEcocardiogramaPdfA4(idAgendamento, laudo);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documento);
    }

    @GetMapping(value = "/gerar/declaracao/{tipo-declaracao}/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarDeclaracaoPdfA4(@PathVariable("idAgendamento") Long idAgendamento, @RequestBody Optional<Object> declaracao) {

        var documento =
                service.gerarDeclaracaoPdfA4(idAgendamento, declaracao);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documento);
    }

    @GetMapping(value = "/gerar/laudo/generico/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarLaudoGenericoPdfA4(@PathVariable("idAgendamento") Long idAgendamento, @RequestBody Optional<Object> declaracao) {

        var documento =
                service.gerarLaudoGenericoPdfA4(idAgendamento, declaracao);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documento);
    }

    @GetMapping(value = "/gerar/atestado/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarAtestadoPdf_A5(@PathVariable("idAgendamento") Long idAgendamento, Optional<Object> informacoes) {

        var documentoPdf =
                service.gerarAtestadoPdf_A5(idAgendamento, informacoes);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }

    @GetMapping(value = "/gerar/receituario/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarReceituarioPdf_A5(@PathVariable("idAgendamento") Long idAgendamento, Optional<Object> prescricao) {

        var documentoPdf =
                service.gerarReceituarioPdf_A5(idAgendamento, prescricao);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }

    @GetMapping(value = "/gerar/requisicao/{idAgendamento}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarRequisicaoPdf_A5(@PathVariable("idAgendamento") Long idAgendamento, Optional<Object> requisicoes) {

        var documentoPdf =
                service.gerarRequisicaoPdf_A5(idAgendamento, requisicoes);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }

    @GetMapping(value = "/gerar/fechamento-de-caixa/do/{idMedico}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> gerarFechamentoCaixaPdf_A5(@PathVariable("idMedico") Long idMedico) {

        var documentoPdf =
                service.gerarFechamentoCaixaPdf_A5(idMedico);
        var headers = montarHeadersDefault();
        return  ResponseEntity.ok()
                .headers(headers)
                .body(documentoPdf);
    }


    private HttpHeaders montarHeadersDefault() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(Documento.CONTENT_DISPOSITION, "Prontuario.pdf");
        return headers;
    }
}
