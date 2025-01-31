package com.nossaclinica_api.repositories.custom;


import com.nossaclinica_api.models.entities.Agendamento;
import com.nossaclinica_api.repositories.queries.NumeroAtendimentoQueries;
import com.nossaclinica_api.utils.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;

@Repository
public class NumeroAtendimentoRepsitoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public Integer obterUltimoNumeroAtendimento(Agendamento agenda) {
        var sql = NumeroAtendimentoQueries.PEGAR_ULTIMO_NUMERO_ATENDIMENTO;
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("idEspecialista", agenda.getEspecialista().getIdEspecialista());
        query.setParameter("idEspecialidade", agenda.getEspecialidade().getIdEspecialidade());
        query.setParameter("tipoAtendimento", agenda.getServico().getNome());
        var countConsulta = String.valueOf(query.getSingleResult());
        return Integer.parseInt(countConsulta);
    }

    public String buscarNumeroAtendimento(Agendamento agenda) {
        Query query = entityManager.createNativeQuery(NumeroAtendimentoQueries.BUSCAR_NUMERO_ATENDIMENTO);
        query.setParameter("idCliente", agenda.getCliente().getIdCliente());
        query.setParameter("idEspecialista", agenda.getEspecialista().getIdEspecialista());
        var numero = query.getSingleResult();
        return Utils.formatarNumeroAtendimento(numero.toString()) + "/" +
                String.valueOf(LocalDate.now().getYear()).substring(2);
    }
}
