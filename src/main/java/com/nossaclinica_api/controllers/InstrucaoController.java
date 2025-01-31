package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.instrucao.RequestInstrucao;
import com.nossaclinica_api.services.InstrucaoService;
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
@RequestMapping(value = "/instrucoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstrucaoController {

    private final InstrucaoService services;

    @Operation(summary = "Registra novo instruçao", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instruçao registrado sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoInstrucao(@RequestBody RequestInstrucao instrucao) {
        var response = services.registrarNovoinstrucao(instrucao);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualização dados do instrucao", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instrucao atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idInstrucao}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarinstrucao(@PathVariable("idInstrucao") Long idInstrucao, @RequestBody RequestInstrucao instrucao) {

        if(!services.exists(idInstrucao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse instrucao na nossa base de dados");
        }

        var response = services.atualizarInstrucao(idInstrucao, instrucao);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Retorna instrucao encontrado por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instrucao encontrado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/{idInstrucao}")
    public ResponseEntity<?> buscarinstrucaoPorId(@PathVariable("idInstrucao") Long idInstrucao) {
        if(!services.exists(idInstrucao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse instrucao na nossa base de dados");
        }

        var response = services.buscarInstrucaoPorId(idInstrucao);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Relaciona de instrucões", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrucões listados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Não há nenhum registro de instrucaos na nossa base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<?> listaInstrucoes() {

        var instrucoes = services.listaInstrucoes();
        if (!instrucoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(instrucoes);
    }

    @Operation(summary = "Relaciona de instrucões do exame", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrucões listados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Não há nenhum registro de instrucaos na nossa base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/exame/{idExame}")
    public ResponseEntity<?> listaInstrucoesDoExame(@PathVariable("idExame") Long idExame) {

        var instrucoes = services.listaInstrucoesDoExame(idExame);
        if (!instrucoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(instrucoes);
    }

    @Operation(summary = "Remove o instrucao da base de dados", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "instrucao deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Não há nenhum registro de instrucaos na nossa base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/{idInstrucao}")
    public ResponseEntity<?> deletarInstrucao(@PathVariable("idInstrucao") Long idInstrucao) {

        if(!services.exists(idInstrucao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse instrucao na nossa base de dados");
        }
        services.deletarinstrucao(idInstrucao);
        return ResponseEntity.ok().build();
    }
}
