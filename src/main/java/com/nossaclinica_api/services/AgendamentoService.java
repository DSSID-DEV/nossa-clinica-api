package com.nossaclinica_api.services;

import com.nossaclinica_api.converters.ConverteAgendamento;
import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.models.dtos.AgendaMedica;
import com.nossaclinica_api.models.dtos.requests.agendamentos.RequestAgendamento;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.agendamentos.RequestStatusAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamentoParaEdicao;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseDadosParaPagamento;
import com.nossaclinica_api.models.entities.Agendamento;
import com.nossaclinica_api.models.entities.NumeroAtendimento;
import com.nossaclinica_api.reports.utils.ConstantesValues;
import com.nossaclinica_api.repositories.*;
import com.nossaclinica_api.repositories.custom.EndpointRepositoryCustom;
import com.nossaclinica_api.repositories.custom.NumeroAtendimentoRepsitoryCustom;
import com.nossaclinica_api.repositories.custom.RepositoryAgendamentoCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final WhatsappService whatsapp;
    private final AgendamentoRepository repository;
    private final EndpointRepositoryCustom endpoint;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final EspecialistaRepository medicoRepository;
    private final RepositoryAgendamentoCustom repositoryCustom;
    private final EspecialidadeRepository especialidadeRepository;
    private final NumeroAtendimentoRepsitory numeroAtendimentoRepository;
    private final NumeroAtendimentoRepsitoryCustom numeroAtendimentoRepositoryCustom;

    public boolean existsAgendamento(RequestAgendamento agenda) {
        return repository.existsAgendamento(agenda.getIdCliente(),
                agenda.getIdEspecialista(), agenda.getIdEspecialidade(),
                agenda.getIdServico(), agenda.getAgendadoPara());
    }
    public boolean exists(Long idAgendamento) {
        return repository.existsById(idAgendamento);
    }

    public ResponseId registrarNovoAgendamento(RequestAgendamento agendamento) {
        var entity = new Agendamento();
        carregarEntity(entity, agendamento);
        return ConverteResponseId.doAgendamento(repository.save(entity));
    }

    public ResponseId atualizarAgendamento(Long idAgendamento, RequestAgendamento agendamento) {
        var entity = repository.findById(idAgendamento).get();
        var dataAlterada = agendamento.getAgendadoPara();
        var numeroAtendimento = numeroAtendimentoRepository
                .buscarDoCliente(entity.getCliente().getIdCliente(),
                entity.getEspecialista().getIdEspecialista(),
                entity.getEspecialidade().getIdEspecialidade(),
                        entity.getServico().getNome(), entity.getAgendadoPara());

        carregarEntity(entity, agendamento);

        atualizarNumeroAtendimento(numeroAtendimento, entity, dataAlterada);

        return ConverteResponseId.doAgendamento(repository.save(entity));
    }

    @Transactional
    private void atualizarNumeroAtendimento(NumeroAtendimento numeroAtendimento, Agendamento agenda, LocalDate dataAlterada) {
        if(!numeroAtendimento.getEspecialista().equals(agenda.getEspecialista()) ||
            !numeroAtendimento.getEspecialidade().equals(agenda.getEspecialidade()) ||
                    !numeroAtendimento.getTipoDeAtendimento().equals(agenda.getServico().getNome())) {
            numeroAtendimento.setTipoDeAtendimento(agenda.getServico().getNome());
            numeroAtendimento.setCliente(agenda.getCliente());
            numeroAtendimento.setEspecialista(agenda.getEspecialista());
            numeroAtendimento.setEspecialidade(agenda.getEspecialidade());
            numeroAtendimento.setDataDoAtendimento(LocalDateTime.of(agenda.getAgendadoPara(), LocalTime.now()));
            var numero = numeroAtendimentoRepositoryCustom.obterUltimoNumeroAtendimento(agenda);
            numeroAtendimento.setNumeroAtendimento(numero);
        }
        numeroAtendimento.setDataDoAtendimento(LocalDateTime.of(dataAlterada, LocalTime.now()));
        numeroAtendimentoRepository.save(numeroAtendimento);
    }

    public ResponseAgendamento buscarPorId(Long idAgendamento) {
        return repositoryCustom.buscarPorId(idAgendamento);
    }

    public ResponseAgendamentoParaEdicao buscarResponseAgendamento(Long idAgendamento) {
        var entity = repository.buscarAgendamentoParaEdicao(idAgendamento);
        if(!entity.isPresent()) {
            return null;
        }
        return ConverteAgendamento.paraEdicao(entity.get());
    }

    public List<ResponseAgendamento> listarAgendamentos(Long idCliente, Long idEspecialista, String agendaDe, String agendaAte, String status) {
        return repositoryCustom.listar(idCliente, idEspecialista, agendaDe, agendaAte, status);
    }

    private Agendamento carregarEntity(Agendamento entity, RequestAgendamento agendamento) {
        var cliente = clienteRepository.getById(agendamento.getIdCliente());
        var medico = medicoRepository.getById(agendamento.getIdEspecialista());
        var servico = servicoRepository.getById(agendamento.getIdServico());
        var especialidade = especialidadeRepository.getById(agendamento.getIdEspecialidade());
        entity.setEspecialista(medico);
        entity.setServico(servico);
        entity.setCliente(cliente);
        entity.setEspecialidade(especialidade);
        entity.setStatus(agendamento.getStatus());
        entity.setMarcadoPara(agendamento.getMarcadoPara());
        entity.setUltimaMestruacao(agendamento.getUltimaMestruacao());
        entity.setAgendadoPara(agendamento.getAgendadoPara());
        return entity;
    }
    public ResponseDadosParaPagamento carregarDadosParaPagamento(Long idAgenda) {
        return repositoryCustom.carregarDadosParaPagamento(idAgenda);
    }

    public ResponseId alterarStatus(Long idAgendamento, RequestStatusAgendamento agendamento) {
        var entity = repository.getById(idAgendamento);

        entity.setStatus(agendamento.getStatus());
        return ConverteResponseId.doAgendamento(repository.save(entity));
    }

    public boolean temAgendaParaMedico(Long idMedico) {
        return repositoryCustom.temAgendaParaMedico(idMedico);
    }

    public void buscarAgendaMedica() {
        var agendaDosMedicos = repositoryCustom.buscarAgendaMedica();
        if(agendaDosMedicos.isEmpty()) {
            log.info("Não há agendas médicas para amanhã");
            return;
        }
        Map<Long, List<AgendaMedica>> agendaMedica = new HashMap<>();
        for(var agenda : agendaDosMedicos) {
            if(!agendaMedica.containsKey(agenda.getIdEspecialista())) {
                List<AgendaMedica> listAgenda = new ArrayList<>();
                agendaMedica.put(agenda.getIdEspecialista(), listAgenda);
            }
            agendaMedica.get(agenda.getIdEspecialista()).add(agenda);
        }
        log.info(ConstantesValues.ENVIDADO_PARA_CAMADA_DE_SERVICO_DO_WHATSAPP);
        whatsapp.sendMensagemDePassagemDaAgendaDoDiaSeguinte(agendaMedica);
    }

    public void buscarPacientesAgendadosDoDiaSeguinte() {
        var agendadosDeAmanha = repositoryCustom.buscarPacientesAgendadoDeAmanha();
        if(agendadosDeAmanha.isEmpty()) {
            log.info("Não há agendas para pacientes para amanhã");
            return;
        }
        log.info(ConstantesValues.ENVIDADO_PARA_CAMADA_DE_SERVICO_DO_WHATSAPP);
        whatsapp.sendMensagemDeLembreteDaConsultaDoDiaSeguinte(agendadosDeAmanha);
    }

    public String buscarUriDoProntuarioDoAtendimento(Long idAgenda, Integer tipo) {
        var agenda = repository.getById(idAgenda);
        var uri = endpoint.para(agenda.getServico().getIdServico(), tipo);
        return uri.concat(idAgenda.toString());
    }

    public boolean naoEmitirPdf(Long idAgenda) {
        var agenda = repository.getById(idAgenda);
        var imprimivel = List.of(1L, 4L, 7L);
        return !imprimivel.contains(agenda.getServico().getIdServico());
    }
}
