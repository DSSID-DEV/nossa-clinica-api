package com.nossaclinica_api.models.dtos.requests.clientes;

import com.nossaclinica_api.models.dtos.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestClienteDependente implements Serializable {

    @Serial
    private static final long serialVersionUID = -3405104575652576074L;

    private Long idDependente;

    private Long idTitular;

    @NotNull(message = "O campo Nome é obrigatório!")
    private RequestCliente dependente;

    private String grauParentesco;

}
