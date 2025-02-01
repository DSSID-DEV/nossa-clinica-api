package com.nossaclinica_api.services;

import com.nossaclinica_api.models.dtos.DadosColposcopio;
import com.nossaclinica_api.models.dtos.DadosDoFechamento;
import com.nossaclinica_api.models.dtos.LaudoColposcopio;
import com.nossaclinica_api.models.dtos.requests.RequestExameColposcopio;
import com.nossaclinica_api.models.entities.Especialista;
import com.nossaclinica_api.reports.*;
import com.nossaclinica_api.reports.utils.Documento;
import com.nossaclinica_api.repositories.AgendamentoRepository;
import com.nossaclinica_api.repositories.ClienteRepository;
import com.nossaclinica_api.repositories.EspecialistaRepository;
import com.nossaclinica_api.repositories.custom.NumeroAtendimentoRepsitoryCustom;
import com.nossaclinica_api.repositories.custom.RepositoryAgendamentoCustom;
import com.nossaclinica_api.repositories.custom.RepositoryExameColposcopioCustom;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final ClienteRepository clientes;
    private final EspecialistaRepository medicos;
    private final RepositoryAgendamentoCustom agendamentos;
    private final AgendamentoRepository agendamentoRepository;
    private final TemplateAtestadoDefaultA5 atestadoDefaultA5;
    private final TemplateDeclaracaoDefaultA4 declaracaoDefaultA4;
    private final RepositoryExameColposcopioCustom repositoryCustom;
    private final TemplateReceituarioDefaultA5 receituarioDefaultA5;
    private final TemplateProntuarioPdfDefaultA4 prontuarioDefaultA4;
    private final TemplateRequisicaoPdfDefaultA5 requisicaoDefaultA5;
    private final TemplateLaudoGenericoDefaultA4 laudoGenericoDefaultA4;
    private final TemplateEcocardiogramaDefaultPdfA4 ecocardiogramaDefaultA4;
    private final NumeroAtendimentoRepsitoryCustom numeroAtendimentoRepository;
    private final TemplateParecerCardiologicoDefaultPdfA4 parecerCardiologicoDefaltA4;
    private final TemplateEntradaSaidaOrcamentoDefaultA5 entradaSaidaOrcamentoDefaultA5;
    private final TemplateProntuarioColposcopioDefaultPdfA4 prontuarioColposcopioDefaultA4;

    public byte[] gerarProntuarioPdf_A4(Long idAgendamento) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var baos = prontuarioDefaultA4.gerarPdf_A4(dadosParaProntuario, Documento.TIPO_PRONTUARIO);
           return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarParecerCardiologicoPdf_A4(Long idAgendamento, Optional<Object> laudoMedico) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var baos = parecerCardiologicoDefaltA4.gerarPdf_A4(dadosParaProntuario, Documento.TIPO_PARECER_CARDIOLOGICO, laudoMedico);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarEcocardiogramaPdfA4(Long idAgendamento, Optional<Object> laudo) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var filePDF = ecocardiogramaDefaultA4.gerarPdf_A4(dadosParaProntuario, Documento.TIPO_PARECER_CARDIOLOGICO, laudo);
            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteArrayOutputStream emitirExameColposcopio(RequestExameColposcopio request) {
        var laudo = carregarLaudoColpocopio(request);

        try {
            return prontuarioColposcopioDefaultA4.gerarPdf_A4(laudo, Documento.TIPO_LAUDO_COLPOSCOPIO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LaudoColposcopio carregarLaudoColpocopio(RequestExameColposcopio request) {

        List<DadosColposcopio> achados = repositoryCustom.listarAchados(request.getIdAgendamento(), request.getAchados());

        var profissional = nomeProfissional(request.getIdMedico());
        var cliente = clientes.getById(request.getIdCliente());
        Map<String, List<DadosColposcopio>> dadosDolaudo = carregarDadosDosAchados(achados);

        return LaudoColposcopio.builder()
                .profissional(profissional)
                .paciente(cliente.getNome())
                .nascidoEm(cliente.getNascidoEm())
                .idade(Utils.calcularIdade(cliente.getNascidoEm()))
                .ultimoExame(request.getUltimoExame())
                .ultimaMestruacao(request.getUltimaMestruacao())
                .ultimaGladula(request.getUltimaGladula())
                .outros(request.getOutros())
                .laudo(dadosDolaudo)
                .build();
    }

    public byte[] gerarDeclaracaoPdfA4(Long idAgendamento, Optional<Object> declaracao) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var filePDF = declaracaoDefaultA4.gerarPdf_A4(dadosParaProntuario, Documento.TIPO_PARECER_CARDIOLOGICO, declaracao);
            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarLaudoGenericoPdfA4(Long idAgendamento, Optional<Object> laudoMedico) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var filePDF = laudoGenericoDefaultA4.gerarPdf_A4(dadosParaProntuario, Documento.LAUDO, laudoMedico);
            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarAtestadoPdf_A5(Long idAgendamento, Optional<Object> documento) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var baos = atestadoDefaultA5.gerarPdf_A5(dadosParaProntuario, Documento.ATESTADO, documento);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarReceituarioPdf_A5(Long idAgendamento, Optional<Object> prescricao) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var filePDF = receituarioDefaultA5.gerarPdf_A5(dadosParaProntuario, Documento.ATESTADO, prescricao);
            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] gerarRequisicaoPdf_A5(Long idAgendamento, Optional<Object> requisicoes) {
        var dadosParaProntuario = agendamentos.gerarDocumentoClinico(idAgendamento);
        try {
            var filePDF = requisicaoDefaultA5.gerarPdf_A5(dadosParaProntuario, Documento.ATESTADO, requisicoes);
            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarFechamentoCaixaPdf_A5(Long idEspecialista) {
        var entradasOrcamento = agendamentos.gerarFechamentoCaixa(idEspecialista);
        var responsavel = medicos.getById(idEspecialista);
        var fechamento = carregarComResonsavel(responsavel);
        fechamento.getEntradas().addAll(entradasOrcamento);

        try {
            var baos = entradaSaidaOrcamentoDefaultA5.gerarPdf_A5(fechamento, Documento.FECHAMENTO_DE_CAIXA);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private DadosDoFechamento carregarComResonsavel(Especialista responsavel) {
        var fechamento = new DadosDoFechamento();
        fechamento.setIdResponsavel(responsavel.getIdEspecialista());
        fechamento.setSexoResponsavel(responsavel.getSexo());
        fechamento.setNomeSocialResponsavel(Utils.definiPronomeDosProfissionais(responsavel.getSexo(), false) + " " + responsavel.getNomeSocial());
        fechamento.setMedico(true);
        return fechamento;
    }

    public String buscarPaciente(Long idAgendamento) {
        var paciente = agendamentos.buscarPaciente(idAgendamento);
        return Documento.gerarNomeDocumento(paciente);
    }

    private String nomeProfissional(Long idMedico) {
        if(idMedico != null) {
            return medicos.getById(idMedico).getNome();
        }
        return "";
    }

    private Map<String, List<DadosColposcopio>> carregarDadosDosAchados(List<DadosColposcopio> achados) {
        Map<String, List<DadosColposcopio>> laudo = new HashMap<>();

        for(var achado: achados) {
            if(!laudo.containsKey(achado.getAnalse())) {
                List<DadosColposcopio> list = new ArrayList<>();
                laudo.put(achado.getAnalse(), list);
            }
            laudo.get(achado.getAnalse()).add(achado);
        }
        return laudo;
    }

    public byte[] emitirPorntuarioColposcopio(Long idAgendamento) {
        var agenda = agendamentoRepository.findById(idAgendamento).get();
        var cliente = agenda.getCliente();
        var numeroAtendimento = numeroAtendimentoRepository.buscarNumeroAtendimento(agenda);
        var profissional = agenda.getEspecialista().getNome();
        List<DadosColposcopio> achados = repositoryCustom.listarAchados(idAgendamento, List.of());
        Map<String, List<DadosColposcopio>> dadosDolaudo = carregarDadosDosAchados(achados);
        LocalDate ultimaMestruacao = agenda.getUltimaMestruacao() != null ? agenda.getUltimaMestruacao() : null;
        var dados = LaudoColposcopio.builder()
                .profissional(profissional)
                .paciente(cliente.getNome())
                .nascidoEm(cliente.getNascidoEm())
                .idade(Utils.calcularIdade(cliente.getNascidoEm()))
                .ultimoExame(null)
                .numeroAtendimento(numeroAtendimento)
                .ultimaMestruacao(ultimaMestruacao)
                .ultimaGladula("")
                .outros(null)
                .laudo(dadosDolaudo)
                .build();

        try {
            var baos = prontuarioColposcopioDefaultA4.gerarPdf_A4(dados, Documento.TIPO_LAUDO_COLPOSCOPIO);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
