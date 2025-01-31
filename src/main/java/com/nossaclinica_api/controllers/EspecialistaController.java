package com.nossaclinica_api.controllers;


import com.nossaclinica_api.models.dtos.requests.especialistas.RequestEspecialista;
import com.nossaclinica_api.models.dtos.responses.especialistas.ResponseEspecialista;
import com.nossaclinica_api.services.EspecialistaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/especialistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialistaController {

    private final EspecialistaServices services;

    /****
     * @return novoEspecialista
     */
    @Operation(summary = "Realiza registro novo especialista", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de especialista(a) realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoEspecialista(@Validated @RequestBody RequestEspecialista especialista) {

        var checarConflito = services.checarConflito(especialista);

        if(checarConflito.isExiste()) {
            log.warn(checarConflito.getMsgLog());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        var novoEspecialista = services.registrarNovoEspecialista(especialista);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEspecialista);
    }

    /***
     *
     * @return atualizado
     */
    @Operation(summary = "Realiza atualização do especialista", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialista atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idEspecialista}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarEspecialista(@PathVariable("idEspecialista") Long idEspecialista, @Validated @RequestBody RequestEspecialista especialista) {

        if(!services.existsEspecialista(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para Esse Especialista!");
        }

        var atualizado = services.atualizarEspecialista(especialista, idEspecialista);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    /***
     *
     * @return status 200
     */
    @Operation(summary = "Realiza reativação do especialista", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialista reativado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/do/{idEspecialista}/reativar")
    public ResponseEntity<?> reativarEspecialista(@PathVariable("idEspecialista") Long idEspecialista) {

        if(!services.existsEspecialista(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para Esse especialista!");
        }

        services.reativarEspecialista(idEspecialista);
        return ResponseEntity.status(HttpStatus.OK).body("especialista reativado com sucesso!");
    }

    /***
     *
     * @return especialista
     */
    @Operation(summary = "Realiza busca especialista por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialista retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/{idEspecialista}")
    public ResponseEntity<?> buscarEspecialistaPorId(@PathVariable("idEspecialista") Long idEspecialista) {
        if(!services.existsEspecialista(idEspecialista)) {
            return ResponseEntity.notFound().build();
        }
        var especialista = services.buscarEspecialistaPorId(idEspecialista);
        return  ResponseEntity.status(HttpStatus.OK).body(especialista);
    }

    /***
     *
     * @return especialista
     */
    @Operation(summary = "Realiza busca especialista por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialista retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/{idEspecialista}/especialista")
    public ResponseEntity<?> buscarEspecialistaResumido(@PathVariable("idEspecialista") Long idEspecialista) {
        var especialista = services.buscarEspecialistaResumido(idEspecialista);
        return  ResponseEntity.status(HttpStatus.OK).body(especialista);
    }

    /***
     * @return listagem de especialistas
     */
    @Operation(summary = "Retorna listagem de especialistas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialistas retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "especialista(s) não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<List<ResponseEspecialista>> buscarEspecialistas(@RequestParam(value = "dadosDaPesquisa", required = false) @DefaultValue String dadosDaPesquisa,
                                                                    @RequestParam(value = "ativo") boolean ativo) {
        var especialistas = services.listarTodosDoParametro(dadosDaPesquisa, ativo);
        if(especialistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(especialistas);
    }

    /***
     * @return status ok
     */
    @Operation(summary = "Deleta um especialista da aplicação", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialista desativado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/{idEspecialista}")
    public ResponseEntity<?> desativarEspecialista(@PathVariable("idEspecialista") Long idEspecialista) {
        services.desativarEspecialista(idEspecialista);
        return ResponseEntity.ok().build();
    }

}
