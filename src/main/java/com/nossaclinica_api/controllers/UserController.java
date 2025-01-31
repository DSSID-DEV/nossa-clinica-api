package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.DadosDoUsuario;
import com.nossaclinica_api.models.dtos.requests.users.ResponseUser;
import com.nossaclinica_api.models.dtos.requests.autenticacao.RequestSenha;
import com.nossaclinica_api.models.dtos.UserDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserServices services;

    @Operation(summary = "Realiza registro novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de usuário realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoUsuario(@RequestBody UserDTO user) {

        var checarConflito = services.checarConflito(user);

        if(checarConflito.isExiste()) {
            log.warn(checarConflito.getMsgLog());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        var novoUsuario = services.registrarNovoUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @Operation(summary = "Realiza atualização do usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idUser}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarUsuario(@PathVariable("idUser") Long idUser, @RequestBody DadosDoUsuario user) {
        var usuarioAtualizado = services.atualizarUsuario(user, idUser);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
    }

    @Operation(summary = "Realiza atualização da senha de acesso do usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova senha salvo com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping("/{idUser}/alterar-senha")
    public ResponseEntity<?> alterarSenha(@PathVariable("idUser") Long idUser, @RequestBody RequestSenha senha) {
        var usuarioLogado = services.alterarSenha(senha, idUser);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioLogado);
    }

    @Operation(summary = "Realiza busca dos dados do perfil do usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/profile/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarProfile(@PathVariable("idUser") Long idUser) {
        var profile = services.buscarProfile(idUser);
        return  ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @Operation(summary = "Realiza busca usuário por dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/do")
    public ResponseEntity<?> buscarUsuario(@RequestParam("dados") String dados) {
        //TODO: ALTERAR CÓDIGO PARA USAR SPECIFICATION
        var user = services.buscarUsuario(dados);
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Realiza busca usuário por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/id/{idUser}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable("idUser") Long idUser) {
        var user = services.buscarPorId(idUser);
        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Retorna uma lista de usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários na cadastrados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping
    public ResponseEntity<List<ResponseUser>> listarUsuarios() {
        var users = services.listarUsuarios();
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return  ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Operation(summary = "Desativa um usuário da aplicação", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário desativado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> desativarUsuario(@PathVariable("idUser") Long idUser) {
        services.desativarUsuarios(idUser);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reativa um usuário da aplicação", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário reativado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários na cadastrados"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping("/{idUser}")
    public ResponseEntity<?> ativarUsuario(@PathVariable("idUser") Long idUser) {
        services.ativarUsuarios(idUser);
        return ResponseEntity.ok().build();
    }

}
