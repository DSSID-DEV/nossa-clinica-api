package com.nossaclinica_api.controllers;

import com.nossaclinica_api.services.AtendimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/atendimentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrarAtendimentoController {

    private final AtendimentoService service;

    @Operation(summary = "Registra novo atendimento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de atendimento realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/{idAgenda}")
    public ResponseEntity<?> registrarNovoAtendimento(@PathVariable("idAgenda") Long idAgenda) {

        service.registrarNovoAtendimento(idAgenda);
        return ResponseEntity.noContent().build();
    }

}
