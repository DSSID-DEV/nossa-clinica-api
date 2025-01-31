package com.nossaclinica_api.controllers;


import com.nossaclinica_api.models.dtos.requests.especialidadesmedica.RequestEspecialidadeMedica;
import com.nossaclinica_api.models.dtos.requests.especialidadesmedica.RequestRqeAreaDeAtuacao;
import com.nossaclinica_api.services.EspecialidadeMedicaServices;
import com.nossaclinica_api.services.EspecialistaServices;
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
@RequestMapping(value = "/especialidades/especialista", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadeMedicaController {

    private final EspecialistaServices medicoServices;
    private final EspecialidadeMedicaServices services;

    /**
     * TODO: TODAS AS REQUISIÇÕES PARA AS ESPECIALIDADES DO MÉDICO
     */

    /***
     * @return status 200
     */

    @Operation(summary = "Realiza registro nova especialidade ao médico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova especialidade registrada ao médico com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/{idEspecialista}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> adicionaEspecialidade(@PathVariable("idEspecialista") Long idEspecialista, @RequestBody RequestEspecialidadeMedica especialidadeMedica) {

        if(!medicoServices.existsEspecialista(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não localizado em nossa base de dados");
        }

        if(services.exists(idEspecialista, especialidadeMedica)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já consta em nossa base de dados essa especialidade para esse médico");
        }

        var responde = services.registrarNovaEspecialidadeMedico(idEspecialista, especialidadeMedica);
        return ResponseEntity.status(HttpStatus.OK).body(responde);
    }

    /***
     *
     * @return atualizado
     */
    @Operation(summary = "Realiza atualização da especialidade do médico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "especialidade atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @PutMapping(value = "{idEspecialidadeDoMedico}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizarEspecialidadeDoMedico(@PathVariable("idEspecialidadeDoMedico")
                                                                Long idEspecialidadeDoMedico,
                                                            @RequestBody RequestRqeAreaDeAtuacao rqeAreaDeAtuacao) {

        if(!services.existsById(idEspecialidadeDoMedico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Registro para Esse Medico!");
        }

        var atualizado = services.atualizarEspecializacaoMedica(idEspecialidadeDoMedico, rqeAreaDeAtuacao);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    /***
     *
     * @return status 200
     */
    @Operation(summary = "Deleta uma especialidade médica da aplicação", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade médica excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @DeleteMapping("/especialidade/{idEspecialidadeDoMedico}")
    public ResponseEntity<?> excluirEspecialidadeMedica(@PathVariable("idEspecialidadeDoMedico") Long idEspecialidadeDoMedico) {
        if(!services.existsById(idEspecialidadeDoMedico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade médica não encontrada na nossa base de dados!");
        }
        services.removerEspecialidade(idEspecialidadeDoMedico);
        return ResponseEntity.ok().build();
    }

    /***
     * @return especialidadesMedica
     */
    @Operation(summary = "Realiza registro nova especialidade ao médico", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nova especialidade registrada ao médico com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/{idEspecialista}/especialidades")
    public ResponseEntity<?> listarEspecialidadeDoMedico(@PathVariable("idEspecialista") Long idEspecialista) {

        if(!medicoServices.existsEspecialista(idEspecialista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não localizado em nossa base de dados");
        }
        var especialidades = services.listarEspecialidadeDoMedico(idEspecialista);
        if(especialidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(especialidades);
    }


    /***
     *
     * @return status especialidadeMedica
     */
    @Operation(summary = "Busca uma especialidade médica da aplicação por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade médica retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor"),

    })
    @GetMapping("/especialidade/{idEspecialidadeDoMedico}")
    public ResponseEntity<?> buscarEspecialidadeMedicaPorId(@PathVariable("idEspecialidadeDoMedico") Long idEspecialidadeDoMedico) {
        if(!services.existsById(idEspecialidadeDoMedico)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade médica não encontrada na nossa base de dados!");
        }
        var especialidadeMedica = services.buscarEspecialidadeMedicaPorId(idEspecialidadeDoMedico);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeMedica);
    }

}
