package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.agendamentos.RequestAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.RequestStatusAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamento;
import com.nossaclinica_api.services.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "nossa-clinica-api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

    private final AgendamentoService services;
    private final EspecialistaServices medicoServices;
    private final ServicoService servicoService;
    private final ClienteServices clienteServices;
    private final EspecialidadeService especialidadeService;

    @Operation(summary = "Registra novo agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento registrado sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Dependencia não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados da inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovoAgendamento(@RequestBody RequestAgendamento agendamento) {
        if(!clienteServices.existsCliente(agendamento.getIdCliente())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos o cliente na nossa basse de dados");
        }
        if(!medicoServices.existsEspecialista(agendamento.getIdEspecialista())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos o médico na nossa basse de dados");
        }
        if(agendamento.getIdEspecialidade() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O campo especialidade é obrigatório!");
        }
        if(!especialidadeService.existsEspecialidade(agendamento.getIdEspecialidade())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos a especialidade informada");
        }
        if(!servicoService.existsById(agendamento.getIdServico())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos este serviço na nossa basse de dados");
        }
        if(services.existsAgendamento(agendamento)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já consta um agendamento do cliente para este médico nesta data, favor verificar!");
        }
        var response = services.registrarNovoAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualização dados do agendamento", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idAgendamento}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarAgendamento(@PathVariable("idAgendamento") Long idAgendamento, @RequestBody RequestAgendamento agendamento) {

        if(!services.exists(idAgendamento)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        if(!clienteServices.existsCliente(agendamento.getIdCliente())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos o cliente na nossa basse de dados");
        }
        if(!medicoServices.existsEspecialista(agendamento.getIdEspecialista())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos o médico na nossa basse de dados");
        }
        if(agendamento.getIdEspecialidade() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O campo especialidade é obrigatório!");
        }
        if(!especialidadeService.existsEspecialidade(agendamento.getIdEspecialidade())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos a especialidade informada");
        }
        if(!servicoService.existsById(agendamento.getIdServico())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos este serviço na nossa basse de dados");
        }

        var response = services.atualizarAgendamento(idAgendamento, agendamento);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "Atualização dados do agendamento", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "/{idAgendamento}/alterar-status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterarStatus(@PathVariable("idAgendamento") Long idAgendamento, @RequestBody RequestStatusAgendamento status) {

        if(!services.exists(idAgendamento)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        var response = services.alterarStatus(idAgendamento, status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Retorna dados para definição do agendamento para pagamento", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/{idAgenda}/preparar/pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> carregarDadosParaPagamento(@PathVariable("idAgenda") Long idAgenda) {

        if(!services.exists(idAgenda)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        var response = services.carregarDadosParaPagamento(idAgenda);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Retorna a uri da emissão de prontuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/{idAgenda}/uri/prontuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarUriDoProntuarioDoAtendimento(@PathVariable("idAgenda") Long idAgenda) {
        if(services.naoEmitirPdf(idAgenda)) {
            return ResponseEntity.noContent().build();
        }
        if(!services.exists(idAgenda)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        var response = services.buscarUriDoProntuarioDoAtendimento(idAgenda, 1);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Verfica se tem agenda para o médico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna verificação de agenda para o médico"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/teve-atendimento/especialista/{idEspecialista}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> temAgendaParaMedico(@PathVariable("idEspecialista") Long idEspecialista) {

        if(!services.exists(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        var response = services.temAgendaParaMedico(idEspecialista);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Realiza busca do agendamento por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(value = "/{idAgendamento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarAgendamentoParaEdicao(@PathVariable("idAgendamento") Long idAgendamento) {
        if(!services.exists(idAgendamento)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontramos agendamento com o dado informado em nossa basse de dados");
        }
        var response = services.buscarResponseAgendamento(idAgendamento);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Lista os agendamentos cadastrados por filtro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResponseAgendamento>> listarAgendamentos(@RequestParam(value = "idCliente", required = false) @DefaultValue Long idCliente,
                                                                            @RequestParam(value = "idEspecialista", required = false) @DefaultValue Long idEspecialista,
                                                                            @RequestParam(required = false, name = "dataDe") @DefaultValue String agendaDe,
                                                                            @RequestParam(required = false, name = "dataAte") @DefaultValue String agendaAte,
                                                                            @RequestParam(required = false) @DefaultValue String status) {
        var agendamentos = services.listarAgendamentos(idCliente, idEspecialista, agendaDe, agendaAte, status);
        if(agendamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(agendamentos);
    }

}
