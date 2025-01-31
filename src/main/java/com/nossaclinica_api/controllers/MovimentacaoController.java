package com.nossaclinica_api.controllers;


import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestEntradaExame;
import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestEstorno;
import com.nossaclinica_api.models.dtos.requests.movimentacoes.RequestMovimentacao;
import com.nossaclinica_api.services.AgendamentoService;
import com.nossaclinica_api.services.MovimentacaoService;
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
@RequestMapping(value = "/movimentacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovimentacaoController {

    private final MovimentacaoService services;
    private final AgendamentoService agendaService;

    @Operation(summary = "Registra nova movimentacao", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimentacao registrada sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(value = "/entrada", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovaMovimentacao(@RequestBody RequestMovimentacao movimentacao) {
        if(movimentacao.getIdAgendamento() != null) {
            if(!agendaService.exists(movimentacao.getIdAgendamento())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos o agendamento na nossa basse de dados");
            }
        }
        if(services.pagamentoJaRealizado(movimentacao)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O pagamento desse atendimento já foi registrado");
        }

        var response = services.registrarNovaMovimentacao(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Realiza estorno de pagamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estorno realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(value = "/estornar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> estornarPagamento(@RequestBody RequestEstorno estorno) {

        var response = services.estornarPagamento(estorno);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Retorna os pagamentos realizados da agenda", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna pagamentos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/estorno/agenda/{idAgenda}")
    public ResponseEntity<?> listarPagamentosRealizados(@PathVariable("idAgenda") Long idAgenda) {
        var response = services.listarPagamentosRealizados(idAgenda);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Realiza registro de entrada de exame", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro de entrada de exame realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(value = "/exames/laboratoriais", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarEntradaDeExame(@RequestBody RequestEntradaExame entradaExame) {

        services.registrarEntradaDeExame(entradaExame);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
