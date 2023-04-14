package com.nossaclinica.api.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.nossaclinica.api.models.entities.Cliente;
import com.nossaclinica.api.models.filters.ClienteFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl {

	private final EntityManager entityManger;

	public List<Cliente> buscarPorFiltro(ClienteFilter filtro) {

		CriteriaBuilder builder = entityManger.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);

		criteriaQuery = getClausulas(builder, criteriaQuery, filtro);

		TypedQuery<Cliente> query = entityManger.createQuery(criteriaQuery);

		return query.getResultList();
	}

	private CriteriaQuery<Cliente> getClausulas(CriteriaBuilder builder, CriteriaQuery<Cliente> criteriaQuery,
			ClienteFilter filtro) {
		Root<Cliente> root = criteriaQuery.from(Cliente.class);

		Map<String, Predicate> predicates = new HashMap<>();

		if (StringUtils.isNotBlank(filtro.getNome())) {
			Predicate nome = builder.like(root.get("nome"), "%" + filtro.getNome() + "%");
			predicates.put("nome", nome);
		}

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			Predicate cpf = builder.equal(root.get("cpf"), filtro.getCpf());
			predicates.put("cpf", cpf);
		}

		if (StringUtils.isNotBlank(filtro.getCartaoSus())) {
			Predicate cartaoDoSus = builder.equal(root.get("cartaoDoSus"), filtro.getCartaoSus());
			predicates.put("cartaoDoSus", cartaoDoSus);
		}

		if (StringUtils.isNotBlank(filtro.getRg())) {
			Predicate rg = builder.equal(root.get("rg"), filtro.getRg());
			predicates.put("rg", rg);
		}

		if (filtro.getNascidoEm() != null) {
			Predicate nascidoEm = builder.equal(root.get("dataDeNascimento"), filtro.getNascidoEm());
			predicates.put("nascidoEm", nascidoEm);
		}

		if (predicates.values().size() > 1) {
			
			criteriaQuery.where(builder
					.and(predicates.values().toArray(new Predicate[0])));
			
		} else {
			for(Entry<String, Predicate> entry : predicates.entrySet()) {
				System.out.println(entry.getKey());
				criteriaQuery.where(entry.getValue());
			}
		}
		return criteriaQuery;
	}
	
}
