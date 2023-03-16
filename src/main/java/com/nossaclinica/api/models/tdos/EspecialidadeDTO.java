package com.nossaclinica.api.models.tdos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEspecialidade;

	private String descricao;

	private String crm;

}
