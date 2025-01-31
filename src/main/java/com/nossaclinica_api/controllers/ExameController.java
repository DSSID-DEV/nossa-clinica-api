package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.exames.RequestExame;
import com.nossaclinica_api.services.ExameService;
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
@RequestMapping(value = "/exames", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExameController {

    private final ExameService services;

    @Operation(summary = "Registra novo exame", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exame registrado sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoExame(@RequestBody RequestExame exame) {
        if(services.existsExema(exame)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já consta esse exame na nossa base de dados");
        }
        //TODO: ALTERAR O TANMANHO DE CARACTERES NA COLUNA INTERPRETACAO
        var response = services.registrarNovoExame(exame);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualização dados do exame", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exame atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idExame}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarExame(@PathVariable("idExame") Long idExame, @RequestBody RequestExame exame) {
        if(!services.exists(idExame)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse exame na nossa base de dados");
        }
        var response = services.atualizarExame(idExame, exame);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Retorna Exame encontrado por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exame encontrado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/{idExame}")
    public ResponseEntity<?> buscarExamePorId(@PathVariable("idExame") Long idExame) {
        if(!services.exists(idExame)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse exame na nossa base de dados");
        }

        var response = services.buscarExamePorId(idExame);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Relaciona de Exames", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exames listados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Não há nenhum registro de exames na nossa base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<?> listaExames(@PathVariable(required = false) String tipoExame, @PathVariable(required = false) String params) {

        var exames = services.listarExames(tipoExame, params);
        if (exames.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(exames);
    }

    @Operation(summary = "Remove o exame da base de dados", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exame deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Não há nenhum registro de exames na nossa base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/{idExame}")
    public ResponseEntity<?> deletarExame(@PathVariable("idExame") Long idExame) {

        if(!services.exists(idExame)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos esse exame na nossa base de dados");
        }
        services.deletarExame(idExame);
        return ResponseEntity.ok().build();
    }
}
