package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.servicos.RequestServico;
import com.nossaclinica_api.services.ServicoService;
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
@RequestMapping(value = "/servicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicoController {

    private final ServicoService services;


    /****
     * @return servico
     */
    @Operation(summary = "Realiza registro novo servico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de servico realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovaservico(@RequestBody RequestServico dto) {

        var checarConflitos = services.checarConflito(dto);

        if(checarConflitos.isExiste()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflitos.getConflito());
        }
        var servico = services.registrarNovoServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }

    /****
     * @return servico
     */
    @Operation(summary = "Realiza atualização da servico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "servico atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping(value = "/{idServico}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovaservico(@PathVariable("idServico") Long idServico,
                                                        @RequestBody RequestServico dto) {

        if(!services.existsById(idServico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a servico na nossa base de dados");
        }

        var servico = services.atualizarservico(idServico, dto);
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    /****
     * @return servico
     */
    @Operation(summary = "Retorna busca de servico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna servico atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/{idServico}")
    public ResponseEntity<?> buscarPorId(@PathVariable("idServico") Long idServico) {

        if(!services.existsById(idServico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a servico na nossa base de dados");
        }
        var servico = services.buscarPorId(idServico);
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    /****
     * @return servicos
     */
    @Operation(summary = "Relaciona as servicos registradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista servicos atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<?> listarServicos() {
        var servicos = services.listarServicos();
        if(servicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

    /****
     * @return status 200
     */
    @Operation(summary = "Remove o servico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servico removido com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping(value = "/{idServico}")
    public ResponseEntity<?> removerservico(@PathVariable("idServico") Long idServico) {

        if(!services.existsById(idServico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a servico na nossa base de dados");
        }
        services.removerServico(idServico);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
