package com.nossaclinica_api.repositories.custom;

import com.nossaclinica_api.converters.BaseDeCalculoConverter;
import com.nossaclinica_api.converters.ConverteAgendamento;
import com.nossaclinica_api.models.dtos.AgendaDoPaciente;
import com.nossaclinica_api.models.dtos.AgendaMedica;
import com.nossaclinica_api.models.dtos.BaseDeCalculo;
import com.nossaclinica_api.models.dtos.DadosParaProntuario;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseAgendamento;
import com.nossaclinica_api.models.dtos.responses.agendamentos.ResponseDadosParaPagamento;
import com.nossaclinica_api.repositories.queries.AgendamentoQueries;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RepositoryAgendamentoCustom {

    private static final String AND_AGENDAMENTO = "and agenda.";
    @PersistenceContext
    private EntityManager entityManager;

    public List<ResponseAgendamento> listar(Long idCliente,
                                            Long idEspecialista,
                                            String agendaDe,
                                            String agendaAte,
                                            String status) {
        String sql = montarQuery(idCliente, idEspecialista, agendaDe, agendaAte, status);
        Query query =  entityManager.createNativeQuery(sql);
        Map<String, Object> params = filtrarParamsValidos(idCliente, idEspecialista, agendaDe, agendaAte, status);
        for(var entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
         return (List<ResponseAgendamento>) query.getResultList().stream()
                 .map(ConverteAgendamento::paraResponseAtendimento)
                 .collect(Collectors.toList());
    }

    public ResponseAgendamento buscarPorId(Long idAgendamento) {
        Query query =  entityManager.createNativeQuery(AgendamentoQueries.BUSCAR_POR_ID);
        query.setParameter("idAgendamento", idAgendamento);
        var singleResult = query.getSingleResult();
        if(singleResult == null) {
            return null;
        }
        return ConverteAgendamento.paraResponseAtendimento(singleResult);
    }

    private Map<String, Object> filtrarParamsValidos(Long idCliente, Long idEspecialista, String agendaDe, String agendaAte, String status) {
        Map<String, Object> params = new HashMap<>();
        if(idCliente != null) {
            params.put("idCliente", idCliente);
        }
        if(idEspecialista != null) {
            params.put("idEspecialista", idEspecialista);
        }
        if(StringUtils.isNotBlank(agendaDe)) {
           params.put("agendaDe", LocalDate.parse(agendaDe));
        }
        if(StringUtils.isNotBlank(agendaAte)) {
           params.put("agendaAte", LocalDate.parse(agendaAte));
        }
        if(StringUtils.isNotBlank(status)) {
            params.put("status", status);
        } else {
            params.put("statusAtendido", "Atendido");
            params.put("statusCancelado", "Cancelado");
        }
        return params;
    }

    public boolean temAgendaHoje(Long idEspecialista) {
        var sql = AgendamentoQueries.TEM_AGENDA_HOJE;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idEspecialista", idEspecialista);
        return (boolean) query.getSingleResult();
    }

    private String
    montarQuery(Long idCliente, Long idEspecialista, String agendaDe, String agendaAte, String status) {
        String sql = AgendamentoQueries.LISTAR_AGENDAMENTOS;
        Map<String, Object> params = new HashMap<>();
        if(idCliente != null) {
            sql += AND_AGENDAMENTO.concat("id_cliente = :idCliente ");
            params.put("idCliente", idCliente);
        }
        if(idEspecialista != null) {
            sql += AND_AGENDAMENTO.concat("id_especialista = :idEspecialista ");
            params.put("idEspecialista", idEspecialista);
        }
        if(StringUtils.isNotBlank(agendaDe)) {
            sql += AND_AGENDAMENTO.concat("agendado_para >= :agendaDe ");
            params.put("agendaDe", agendaDe);
        }
        if(StringUtils.isNotBlank(agendaAte)) {
            sql += AND_AGENDAMENTO.concat("agendado_para <= :agendaAte ");
            params.put("agendaAte", agendaAte);
        }
        if(StringUtils.isNotBlank(status)) {
            sql += AND_AGENDAMENTO.concat("status = :status ");
            params.put("status", status);
        } else {
            sql += AND_AGENDAMENTO.concat("status <> :statusAtendido ");
            sql += AND_AGENDAMENTO.concat("status <> :statusCancelado ");
        }
        return sql += "order by agenda.agendado_para,  agenda.marcado_para";
    }

    public DadosParaProntuario gerarDocumentoClinico(Long idAgendamento) {
        var sql = AgendamentoQueries.DADOS_PARA_PDF;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idAgendamento", idAgendamento);
        return ConverteAgendamento.paraDadosParaProntuario(query.getSingleResult());
    }
    public ResponseDadosParaPagamento carregarDadosParaPagamento(Long idAgenda) {
        var sql = AgendamentoQueries.CARREGAR_DADOS_PARA_EFETIVAR_PAGAMENTO;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idAgenda", idAgenda);
        return ConverteAgendamento.paraResponseDadosParaPagamento(query.getSingleResult());
    }

    public String buscarPaciente(Long idAgendamento) {
        var sql = AgendamentoQueries.BUSCAR_NOME_PACIENTE;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idAgendamento", idAgendamento);
        return (String) query.getSingleResult();
    }

    public boolean temAgendaParaMedico(Long idEspecialista) {
        var sql = AgendamentoQueries.TEM_AGENDA_HOJE;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idEspecialista", idEspecialista);
        return (boolean) query.getSingleResult();
    }

    public List<AgendaMedica> buscarAgendaMedica() {
        var sql = AgendamentoQueries.AGENDAS_DE_AMANHA_DO_MEDICO;
        Query query = entityManager.createNativeQuery(sql);
        return (List<AgendaMedica>) query.getResultList()
                .stream()
                .map(ConverteAgendamento::paraAgendaMedica)
                .collect(Collectors.toList());
    }

    public List<AgendaDoPaciente> buscarPacientesAgendadoDeAmanha() {
        var sql = AgendamentoQueries.AGENDA_DO_DIA_SEGUINTE_DOS_PACIENTES;
        Query query = entityManager.createNativeQuery(sql);
        return (List<AgendaDoPaciente>) query.getResultList()
                .stream()
                .map(ConverteAgendamento::paraAgendaDoPaciente)
                .collect(Collectors.toList());
    }

    public List<BaseDeCalculo> gerarFechamentoCaixa(Long idEspecialista) {
        var sql = AgendamentoQueries.CARREGAR_DADOS_PARA_FECHAMENTO_E_PAGAMENTO_DO_ATENDENDIMENTO;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idEspecialista", idEspecialista);
        return BaseDeCalculoConverter.paraBaseDeCalculo(query.getResultList());
    }
}
