package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.solicitacoes.RequestSolicitacaoEstornoDePagamento;
import com.nossaclinica_api.services.AutorizacaoService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/autorizacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoriazacaoController {

   private final AutorizacaoService services;

    @Operation(summary = "Realiza autenticação do usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping("/estorno")
    public ResponseEntity<?> solicitarAutorizacaoParaEstorar(@RequestBody RequestSolicitacaoEstornoDePagamento movimentacao) {

        if(services.existeSolicitacao(movimentacao.getFormasDePagamento())) {
            ResponseEntity.status((HttpStatus.CONFLICT)).body("Já foi solicitado a aturização para gestor!");
        }
        services.solicitarEstornoDePagamento(movimentacao);
        return ResponseEntity.status(HttpStatus.OK).body("Solicitação enviada com sucesso, aguarde o retorno do gestor via whatsapp.");
    }


}
