package com.nossaclinica_api.controllers;

import com.nossaclinica_api.config.security.JwtProvider;
import com.nossaclinica_api.config.security.UserDetailsImpl;
import com.nossaclinica_api.converters.UserConverte;
import com.nossaclinica_api.models.dtos.UserDTO;
import com.nossaclinica_api.models.dtos.requests.autenticacao.RequestDadosDeAcesso;
import com.nossaclinica_api.models.dtos.requests.autenticacao.RequestDadosDeEntrada;
import com.nossaclinica_api.models.dtos.responses.autenticacao.RequestValidarTelefone;
import com.nossaclinica_api.services.SmsService;
import com.nossaclinica_api.services.UserServices;
import com.nossaclinica_api.utils.ConstantsValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@PermitAll
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutenticacaoController {

    @Value("${nossaclinica-api.images.path-root}")
    private String pathRoot;
    @Value("${nossaclinica-api.images.users.avatas}")
    private String pathAvatar;

    private final UserServices services;

    private final SmsService smsService;

    private final AuthenticationManager authenticationManger;

   private final JwtProvider jwtProvider;

    @Operation(summary = "Realiza autenticação do usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody RequestDadosDeAcesso dadosDeAcesso) {
        Authentication authentication = authenticationManger.authenticate(
                new UsernamePasswordAuthenticationToken(dadosDeAcesso.getDadosDeAcesso(), dadosDeAcesso.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        var autenticado = UserConverte.usuarioAutenticado(userDetailsImpl,
                jwtProvider.generateToken(authentication));

        autenticado.setAvatar(addRoot(autenticado.getAvatar()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(autenticado);
    }


    @Operation(summary = "Cria conta de novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Já consta em nossa base usuário com esses dados."),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor."),

    })
    @PostMapping("/signup")
    public ResponseEntity<?> criarConta(@RequestBody UserDTO userDTO) {

        var checarConflito = services.checarConflito(userDTO);

        if(checarConflito.isExiste()) {
            log.warn(checarConflito.getMsgLog());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflito.getConflito());
        }

        var logado = services.signup(userDTO);

        Authentication authentication = authenticationManger.authenticate(
                new UsernamePasswordAuthenticationToken(logado.getUsername(), userDTO.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logado.setToken(jwtProvider.generateToken(authentication));
        logado.setAvatar(addRoot(logado.getAvatar()));
        return ResponseEntity.status(HttpStatus.OK).body(logado);
    }

    @Operation(summary = "Realiza registro novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de usuário realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(value = "/recuperar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoUsuario(@RequestBody RequestDadosDeEntrada entrada) {

        if(services.notExists(entrada.getLogin())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. Tente novamente!");
        }

        var verificacao = services.criarVerificacao(entrada.getLogin());

        return ResponseEntity.status(HttpStatus.OK).body(verificacao);
    }

    @Operation(summary = "Valida telefone para gerar senha provisória", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valida telefone e retorna senha provisória"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping("/send/senha-provisoria")
    public ResponseEntity<?> enviarSenhaProvisoria(@RequestBody RequestValidarTelefone validar) {

        var msg = smsService.sendSMS(validar);

        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    private String addRoot(String avatar) {
            return String.format(ConstantsValue.DEFAULT_FORMAT_AVATAR, pathRoot, pathAvatar, avatar);
    }

}
