package com.nossaclinica_api.services;

import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.ClienteConverte;
import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.EnderecoConverte;
import com.nossaclinica_api.models.dtos.requests.clientes.RequestCliente;
import com.nossaclinica_api.models.dtos.requests.clientes.RequestClienteDependente;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseCliente;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseClienteResumido;
import com.nossaclinica_api.models.entities.*;
import com.nossaclinica_api.repositories.ClienteRepository;
import com.nossaclinica_api.repositories.EnderecoRepository;
import com.nossaclinica_api.repositories.UserClienteRepository;
import com.nossaclinica_api.repositories.custom.RepositoryClienteCustom;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServices {

    private final ClienteRepository repository;
    private final RepositoryDependente dependenteRepository;
    private final EnderecoRepository enderecoRepository;
    private final RepositoryClienteCustom repositoryCustom;
    private final UserClienteRepository userClienteRepository;


    @Transactional
    public void registrarNovoCliente(User user) {
        var clienteDTO = ClienteConverte.deUserParaClientDTO(user);
        if(existsClienteCadastrado(clienteDTO.getNome(), clienteDTO.getTelefone(),
                clienteDTO.getNascidoEm(), clienteDTO.getEmail())) {
            return;
        }
        var cliente = repository.save(ClienteConverte.paraEntity(clienteDTO));
        criarRelacionamento(user, cliente, true);
    }

    @Transactional
    private void criarRelacionamento(User user, Cliente cliente, boolean responsavel) {
        if(user == null) {
            return;
        }
        var relacionamento = new UsuarioCliente();
        relacionamento.setCliente(cliente);
        relacionamento.setUser(user);
        relacionamento.setCobertado(false);
        relacionamento.setResponsavel(responsavel);
        userClienteRepository.save(relacionamento);
    }

    @Transactional
    public ResponseId registrarNovoCliente(RequestCliente clienteDTO) {

        var cliente = ClienteConverte.paraEntity(clienteDTO);
        if(cliente.getEndereco() != null) {
            var endereco = enderecoRepository.save(cliente.getEndereco());
            cliente.setEndereco(endereco);
        }
        cliente.setAtivo(true);
        return ConverteResponseId.doCliente(repository.save(cliente));
    }

    public CheckConflito checarConflito(String nome, String telefone,
                                        LocalDate nascidoEm, String email) {
        var checarConflito = new CheckConflito();
        checarConflito.setExiste(false);
        if(existsClienteCadastrado(nome, telefone,
                nascidoEm, email)) {
            checarConflito.setExiste(true);
            checarConflito.setConflito("Cliente já consta em nossa base de dados!");
            checarConflito.setMsgLog("Cliente já consta em nossa base de dados!");
        }
        return checarConflito;
    }

    public boolean existsClienteCadastrado(String nome, String telefone,
                                           LocalDate nascidoEm, String email) {
        return repository.existsCliente(Utils.fullLike(nome), telefone,
                nascidoEm, email);
    }

    @Transactional
    public RequestCliente atualizarCliente(RequestCliente cliente, Long idCliente) {
        var entity = repository.buscarPorId(idCliente);

        ClienteConverte.setarCamposAtualizados(entity, cliente);

        if(entity.getEndereco() != null) {
            enderecoRepository.save(entity.getEndereco());
        }

        return ClienteConverte.paraDTO(repository.save(entity));
    }

    public boolean existsCliente(Long idCliente) {
        return repository.existsClienteComIdCliente(idCliente);
    }

    public ResponseCliente findById(Long idCliente) {
        var cliente = repository.buscarPorId(idCliente);
        return ClienteConverte.paraResponseClienteResumido(cliente);
    }

    public void desativarCleinte(Long idCliente) {
        var cliente = buscarClientePorId(idCliente);
        cliente.setAtivo(false);
        repository.save(cliente);
    }

    public void reativarCliente(Long idCliente) {
        var cliente = buscarClientePorId(idCliente);
        cliente.setAtivo(true);
        repository.save(cliente);
    }

    public Cliente buscarClientePorId(Long idCliente) {
        return repository.buscarPorId(idCliente);
    }

    public List<ResponseClienteResumido> consultarClientePorParametros(String dadosDaPesquisa, boolean ativo) {
        return repositoryCustom.listarClientesPorParametros(dadosDaPesquisa, ativo);
//                .stream().map(ClienteConverte::paraDTO)
//                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseId registrarNovoDependente(Long idTitular, RequestClienteDependente request) {
        var dependente = ClienteConverte.paraEntity(request.getDependente());
        var titular = buscarClientePorId(idTitular);
        if(titular != null && titular.getUser() != null) {
            dependente.setUser(titular.getUser());
        }
        var endereco = buscarEndereco(request);
        dependente.setEndereco(endereco);
        dependente.setAtivo(true);
        dependente = repository.save(dependente);
        criarRelacionamento(titular.getUser(), dependente, false);
        criarAdicionarDependente(titular, dependente, request.getGrauParentesco());
        return ConverteResponseId.doCliente(dependente);
    }

    private Endereco buscarEndereco(RequestClienteDependente request) {
        Endereco endereco = null;
        if(request.getDependente().getEndereco() != null) {
            endereco = EnderecoConverte.paraEntity(request.getDependente().getEndereco());
        }
        var enderecoSalvo = enderecoRepository.buscarEnderecoPor(endereco.getCidade(),
                endereco.getBairro(), endereco.getTipoLogradouro(),
                endereco.getLogradouro(), endereco.getNumero());
        if(enderecoSalvo == null) {
            return enderecoRepository.save(endereco);
        }
        return enderecoSalvo;
    }

    @Transactional
    private void criarAdicionarDependente(Cliente titular, Cliente dependente, String grauParentesco) {
        var relacaoTitularDepentente = Dependente.builder()
                .titular(titular)
                .dependente(dependente)
                .grauParetesco(grauParentesco)
                .build();
        dependenteRepository.save(relacaoTitularDepentente);
    }

    public boolean exists(Long idTitular) {
        return repository.existsById(idTitular);
    }

    public boolean verificarTitularidade(Long idCliente) {
        return dependenteRepository.verificarTitularidade(idCliente);
    }
}
