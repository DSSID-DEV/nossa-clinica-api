package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.RequestExameColposcopio;
import com.nossaclinica_api.models.dtos.responses.colposcopia.ResponseAnaliseColposcopio;
import com.nossaclinica_api.services.DocumentoService;
import com.nossaclinica_api.services.ExameColposcopioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/exames/colposcopio", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExameColposcopioController {

    private final ExameColposcopioService service;

    private final DocumentoService documentos;

    @Operation(summary = "Retorna lista de itens de analise colposcopio", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<List<ResponseAnaliseColposcopio>> listarItensDeAnaliseColposcopio() {
        var itens = service.listarItensDeAnaliseColposcopio();
        if(itens.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(itens);
    }

    @Operation(summary = "Emite prontuário Colposcópio", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emite prontuário com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping
    public ResponseEntity<?> emitirExameColposcopio(@RequestBody RequestExameColposcopio request) {

        var baos = documentos.emitirExameColposcopio(request);

        return ResponseEntity.status(HttpStatus.OK).body(baos.toByteArray());
    }
}
