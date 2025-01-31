package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.clientes.RequestCliente;
import com.nossaclinica_api.models.dtos.requests.clientes.RequestClienteDependente;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseClienteResumido;
import com.nossaclinica_api.services.ClienteServices;
import com.nossaclinica_api.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    private final ClienteServices services;
    private final UserServices usuarioServices;

    @Operation(summary = "Realiza registro novo cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de cliente realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoCliente(@Validated @RequestBody RequestCliente cliente) {

        var checarConflito = services.checarConflito(cliente.getNome(), cliente.getTelefone(),
                cliente.getNascidoEm(), cliente.getEmail());

        if(checarConflito.isExiste()) {
            log.warn(checarConflito.getMsgLog());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        var novoCliente = services.registrarNovoCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Operation(summary = "Realiza registro novo dependente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro request dependente com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/{idTitular}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoDependente(@PathVariable("idTitular") Long idTitular, @Validated @RequestBody RequestClienteDependente request) {

        if(!services.exists(idTitular)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para esse Usuário!");
         }

        var checarConflito = services.checarConflito(request.getDependente().getNome(),
                request.getDependente().getTelefone(),
                request.getDependente().getNascidoEm(), request.getDependente().getEmail());

        if(checarConflito.isExiste()) {
            log.warn(checarConflito.getMsgLog());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        var novoCliente = services.registrarNovoDependente(idTitular, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Operation(summary = "Realiza atualização do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarCliente(@PathVariable("idCliente") Long idCliente, @Validated @RequestBody RequestCliente cliente) {

        if(!services.existsCliente(idCliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para Esse Cliente!");
        }

        var clienteAtualizado = services.atualizarCliente(cliente, idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
    }

    @Operation(summary = "Realiza reativação do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente reativado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/do/{idCliente}/reativar")
    public ResponseEntity<?> reativarCliente(@PathVariable("idCliente") Long idCliente) {

        if(!services.existsCliente(idCliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para Esse Cliente!");
        }

        services.reativarCliente(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente reativado com sucesso!");
    }

    @Operation(summary = "Realiza busca cliente por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/do/{idCliente}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable("idCliente") Long idCliente) {
        var cliente = services.findById(idCliente);
        return  ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @Operation(summary = "Realiza busca cliente por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/{idCliente}/titular")
    public ResponseEntity<?> verificarTitularidade(@PathVariable("idCliente") Long idCliente) {
        var response = services.verificarTitularidade(idCliente);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Retorna listagem de clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Cliente(s) não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<List<ResponseClienteResumido>> consultarClientePorParametros(@RequestParam(value = "dadoDePesquisa", required = false) String dadoDePesquisa,
                                                                                       @RequestParam(value = "ativo") boolean ativo) {
        var clientes = services.consultarClientePorParametros(dadoDePesquisa, ativo);
        if(clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @Operation(summary = "Deleta um cliente da aplicação", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/do/{idCliente}")
    public ResponseEntity<?> desativarCliente(@PathVariable("idCliente") Long idCliente) {
        services.desativarCleinte(idCliente);
        return ResponseEntity.ok().build();
    }
}
