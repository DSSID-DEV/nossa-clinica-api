package com.nossaclinica_api.services;


import com.nossaclinica_api.converters.ConverteAnaliseColposcopio;
import com.nossaclinica_api.models.dtos.responses.colposcopia.ResponseAnaliseColposcopio;
import com.nossaclinica_api.reports.TemplateProntuarioColposcopioDefaultPdfA4;
import com.nossaclinica_api.repositories.ClienteRepository;
import com.nossaclinica_api.repositories.ExameColposcopioRepository;
import com.nossaclinica_api.repositories.EspecialistaRepository;
import com.nossaclinica_api.repositories.custom.RepositoryExameColposcopioCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExameColposcopioService {


    private final EspecialistaRepository medicos;
    private final ClienteRepository clientes;
    private final ExameColposcopioRepository repository;
    private final RepositoryExameColposcopioCustom repositoryCustom;
    private final DocumentoService documentoService;
    private final TemplateProntuarioColposcopioDefaultPdfA4 prontuarioColposcopioDefaultA4;

    public List<ResponseAnaliseColposcopio> listarItensDeAnaliseColposcopio() {
        return repository.listarItensDeAnaliseColposcopio()
                .stream().map(ConverteAnaliseColposcopio::paraResponseAnaliseColposcopio)
                .collect(Collectors.toList());
    }

//    public ByteArrayOutputStream emitirExameColposcopio(RequestExameColposcopio request) {
//        List<DadosColposcopio> achados = repositoryCustom.listarAchados(request.getIdAgendamento(), request.getAchados());
//        var profissional = nomeProfissional(request.getIdMedico());
//        var cliente = clientes.getById(request.getIdCliente());
//        Map<String, List<DadosColposcopio>> dadosDolaudo = carregarDadosDosAchados(achados);
//
//        var laudo = LaudoColposcopio.builder()
//                .profissional(profissional)
//                .paciente(cliente.getNome())
//                .nascidoEm(cliente.getNascidoEm())
//                .idade(Utils.calcularIdade(cliente.getNascidoEm()))
//                .ultimoExame(request.getUltimoExame())
//                .ultimaMestruacao(request.getUltimaMestruacao())
//                .ultimaGladula(request.getUltimaGladula())
//                .outros(request.getOutros())
//                .laudo(dadosDolaudo)
//                .build();
//
//        var baos = documentoService.emitirExameColposcopio(laudo);
//        return null;
//    }

}
