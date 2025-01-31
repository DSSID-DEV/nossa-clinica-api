package com.nossaclinica_api.controllers;

import com.nossaclinica_api.models.dtos.requests.especialidades.EspecialidadeDTO;
import com.nossaclinica_api.services.EspecialidadeService;
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
@RequestMapping(value = "/especialidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadeController {

    private final EspecialidadeService services;


    /****
     * @return especialidade
     */
    @Operation(summary = "Realiza registro nova especialidade", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo registro de especialidade realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovaEspecialidade(@RequestBody EspecialidadeDTO dto) {

        var checarConflitos = services.checarConflito(dto);

        if(checarConflitos.isExiste()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(checarConflitos.getConflito());
        }
        var especialidade = services.registrarNovaEspecialidade(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidade);
    }

    /****
     * @return especialidade
     */
    @Operation(summary = "Realiza atualização da especialidade", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping(value = "/{idEspecialidade}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registrarNovaEspecialidade(@PathVariable("idEspecialidade") Long idEspecialidade,
                                                        @RequestBody EspecialidadeDTO dto) {

        if(!services.existsById(idEspecialidade)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a especialidade na nossa base de dados");
        }

        var especialidade = services.atualizarEspecialidade(idEspecialidade, dto);
        return ResponseEntity.status(HttpStatus.OK).body(especialidade);
    }

    /****
     * @return especialidade
     */
    @Operation(summary = "Retorna busca de especialidade", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna especialidade atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/{idEspecialidade}")
    public ResponseEntity<?> buscarPorId(@PathVariable("idEspecialidade") Long idEspecialidade) {

        if(!services.existsById(idEspecialidade)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a especialidade na nossa base de dados");
        }
        var especialidade = services.buscarPorId(idEspecialidade);
        return ResponseEntity.status(HttpStatus.OK).body(especialidade);
    }

    /****
     * @return especialidades
     */
    @Operation(summary = "Relaciona as especialidades registradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista especialidades atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<?> listarEspecialidades() {
        var especialidades = services.listarEspecialidades();
        if(especialidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(especialidades);
    }

    /****
     * @return status 200
     */
    @Operation(summary = "Retorna busca de especialidade", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna especialidade atualizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "422", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping(value = "/{idEspecialidade}")
    public ResponseEntity<?> removerEspecialidade(@PathVariable("idEspecialidade") Long idEspecialidade) {

        if(!services.existsById(idEspecialidade)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não consta a especialidade na nossa base de dados");
        }
        services.removerEspecialidade(idEspecialidade);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
