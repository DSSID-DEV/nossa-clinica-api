package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.EnderecoDTO;
import com.nossaclinica_api.models.dtos.requests.clientes.RequestCliente;
import com.nossaclinica_api.models.dtos.requests.clientes.RequestClienteDependente;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseCliente;
import com.nossaclinica_api.models.dtos.responses.clientes.ResponseClienteResumido;
import com.nossaclinica_api.models.entities.Cliente;
import com.nossaclinica_api.models.entities.Endereco;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.utils.Utils;
import com.nossaclinica_api.utils.Verificador;
import org.modelmapper.ModelMapper;

public class ClienteConverte {
    public static RequestCliente deUserParaClientDTO(User usuario) {
        return RequestCliente.builder()
                .user(UserConverte.paraUserDTO(usuario))
                .nome(usuario.getNome())
                .nascidoEm(usuario.getNascidoEm())
                .telefone(usuario.getTelefone())
                .whatsapp(usuario.isWhatsapp())
                .email(usuario.getEmail())
                .ativo(true)
                .build();
    }

    private static boolean isWhatsapp(String whatsapp, String telefone) {
        return Verificador.isNotBlank(whatsapp) && whatsapp.equals(telefone);
    }

    public static Cliente paraEntity(RequestCliente clienteDTO) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public static Cliente paraEntity(RequestClienteDependente clienteDTO) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(clienteDTO, Cliente.class);
    }
    public static ResponseClienteResumido paraResponseClienteResumido(Object cliente) {
        return ResponseClienteResumido.builder()
                .idCliente(Utils.parseLogn((Object[]) cliente, ResponseClienteResumido.ID_CLIENTE))
                .nome(Utils.parseString((Object[]) cliente, ResponseClienteResumido.NOME))
                .cpf(Utils.parseString((Object[]) cliente, ResponseClienteResumido.CPF))
                .telefone(Utils.parseString((Object[]) cliente, ResponseClienteResumido.TELEFONE))
                .whatsapp((Utils.parseBoolean((Object[]) cliente, ResponseClienteResumido.IS_WHATSAPP)))
                .nascidoEm(Utils.parseLocalDate((Object[]) cliente, ResponseClienteResumido.NASCIDO_EM))
                .ativo(Utils.parseBoolean((Object[]) cliente, ResponseClienteResumido.ATIVO))
                .build();
    }
    public static ResponseCliente paraResponseClienteResumido(Cliente cliente) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(cliente, ResponseCliente.class);
    }

    public static RequestCliente paraDTO(Cliente cliente) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(cliente, RequestCliente.class);
    }

    public static Cliente setarCamposAtualizados(Cliente entity, RequestCliente dto) {
        entity.setEmail(Utils.verificarString(entity.getEmail(), dto.getEmail()));
        entity.setTelefone(Utils.verificarString(entity.getTelefone(), dto.getTelefone()));
        entity.setCpf(Utils.verificarString(entity.getCpf(), dto.getCpf()));
        entity.setDocumento(Utils.verificarString(entity.getDocumento(), dto.getDocumento()));
        entity.setNome(Utils.verificarString(entity.getNome(), dto.getNome()));
        entity.setOrgaoEmissor(Utils.verificarString(entity.getOrgaoEmissor(), dto.getOrgaoEmissor()));
        entity.setWhatsapp(dto.isWhatsapp());
        entity.setNascidoEm(Utils.verificarLocalDate(entity.getNascidoEm(), dto.getNascidoEm()));
        if(dto.getEndereco() != null) {
            entity.setEndereco(setarCamposAtualizadosDoEndereco(entity.getEndereco(), dto.getEndereco()));
        }
        entity.setAtivo(true);
        return entity;
    }

    private static Endereco setarCamposAtualizadosDoEndereco(Endereco entity, EnderecoDTO dto) {
       if(entity == null) {
            entity = new Endereco();
        }
        entity.setUf(Utils.verificarString(entity.getUf(), dto.getUf()));
        entity.setCep(Utils.verificarString(entity.getCep(), dto.getCep()));
        entity.setNumero(Utils.verificarString(entity.getNumero(), dto.getNumero()));
        entity.setCidade(Utils.verificarString(entity.getCidade(), dto.getCidade()));
        entity.setBairro(Utils.verificarString(entity.getBairro(), dto.getBairro()));
        entity.setLogradouro(Utils.verificarString(entity.getLogradouro(), dto.getLogradouro()));
        entity.setComplemento(Utils.verificarString(entity.getComplemento(), dto.getComplemento()));
        entity.setTipoLogradouro(Utils.verificarString(entity.getTipoLogradouro(), dto.getTipoLogradouro()));
        return entity;
    }

}
