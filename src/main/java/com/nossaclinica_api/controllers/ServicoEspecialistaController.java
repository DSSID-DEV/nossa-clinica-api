package com.nossaclinica_api.controllers;


import com.nossaclinica_api.models.dtos.requests.servicosmedico.RequestServicoMedico;
import com.nossaclinica_api.models.dtos.requests.servicosmedico.RequestServicoPreco;
import com.nossaclinica_api.models.dtos.responses.servicosmedico.ResponseServicoMedico;
import com.nossaclinica_api.services.ServicoMedicoService;
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
@RequestMapping(value = "/servicos/especialista", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicoEspecialistaController {

    private final ServicoMedicoService services;

    /***
     * @return status 201
     */

    @Operation(summary = "Realiza registro novo serviço ao médico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Novo serviço registrado ao médico com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/{idEspecialista}/servico", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adicionaServico(@PathVariable("idEspecialista") Long idEspecialista, @RequestBody RequestServicoMedico servicoMedico) {

        var checarConflito = services.checarConflito(idEspecialista, servicoMedico);
        if(checarConflito.isExiste()) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        if(!services.existsMedico(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialista não localizado em nossa base de dados");
        }
        var response = services.registrarNovoServicoDoEspecialista(idEspecialista, servicoMedico);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /***
     * @return status 200
     */
    @Operation(summary = "Realiza atualização da especialidade do médico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialidade atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/servico/{idServicoMedico}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarServicoDoMedico(@PathVariable("idServicoMedico")
                                                            Long idServicoMedico,
                                                            @RequestBody RequestServicoPreco dto) {

        if(!services.existServicoMedico(idServicoMedico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para esse serviço médico!");
        }

        var atualizado = services.atualizarServicoMedico(idServicoMedico, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    /***
     * @return servicoMedico
     */
    @Operation(summary = "Busca serviço médico pelo idServicoMedico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço médico retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Médico(s) não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/servico/{idServicoMedico}")
    public ResponseEntity<?> buscarServicoMedicoPorId(@PathVariable("idServicoMedico") Long idServicoMedico) {
        if(!services.existServicoMedico(idServicoMedico)) {
            return ResponseEntity.notFound().build();
        }
        var servicoMedico = services.buscarPorId(idServicoMedico);
        return  ResponseEntity.status(HttpStatus.OK).body(servicoMedico);
    }

    /***
     * @return servicos
     */
    @Operation(summary = "Relaciona os serviços do médico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço médico retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Médico(s) não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/{idEspecialista}")
    public ResponseEntity<List<ResponseServicoMedico>> relacionarServicosDoMedico(@PathVariable("idEspecialista") Long idEspecialista) {

        var servicos = services.listarServicosDoMedico(idEspecialista);

        if(servicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(servicos);
    }


    /***
     * @return status 200
     */
    @Operation(summary = "Deleta um serviço médico da aplicação", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço médico excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/servico/{idServicoMedico}")
    public ResponseEntity<?> ecluirEspecialidadeMedica(@PathVariable("idServicoMedico") Long idServicoMedico) {
        if(!services.existServicoMedico(idServicoMedico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servico médico não encontrado na nossa base de dados!");
        }
        services.removerServico(idServicoMedico);
        return ResponseEntity.ok().build();
    }

}
