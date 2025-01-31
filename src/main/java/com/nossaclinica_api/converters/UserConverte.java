package com.nossaclinica_api.converters;

import com.nossaclinica_api.config.security.UserDetailsImpl;
import com.nossaclinica_api.models.dtos.DadosDoUsuario;
import com.nossaclinica_api.models.dtos.UserDTO;
import com.nossaclinica_api.models.dtos.requests.especialistas.RequestEspecialista;
import com.nossaclinica_api.models.dtos.requests.users.ResponseUser;
import com.nossaclinica_api.models.dtos.responses.autenticacao.ResponseUserAutenticado;
import com.nossaclinica_api.models.dtos.responses.users.RequestUserProfile;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.models.enums.Permisao;
import com.nossaclinica_api.utils.Utils;
import org.modelmapper.ModelMapper;

public class UserConverte {

    public static User paraEntity(UserDTO dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, User.class);
    }
    public static ResponseUserAutenticado paraUserLogado(User entity) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(entity, ResponseUserAutenticado.class);
    }
    public static UserDTO paraUserDTO(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, UserDTO.class);
    }

    public static ResponseUser paraResponseUser(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ResponseUser.class);
    }

    public static User atualizarCamposAlterados(User entity, DadosDoUsuario user) {
        entity.setNome(Utils.verificarString(entity.getNome(), user.getNome()));
        entity.setEmail(Utils.verificarString(entity.getEmail(), user.getEmail()));
        entity.setPermissao(Utils.verificarString(entity.getPermissao(), user.getPermissao()));
        entity.setTelefone(Utils.verificarString(entity.getTelefone(), user.getTelefone()));
        entity.setWhatsapp(user.isWhatsapp());
        entity.setAvatar(Utils.criarNomeDoAvatar(entity.getUsername(), entity.getPermissao()));

        return entity;
    }

    public static RequestUserProfile paraProfile(User userProfile) {
        var modelMapper = new ModelMapper();
        var profile = modelMapper.map(userProfile, RequestUserProfile.class);
        profile.tratarNomeSobreNome();
        return profile;
    }

    public static ResponseUserAutenticado usuarioAutenticado(UserDetailsImpl principal, String token) {
        var modelMapper = new ModelMapper();
        var logado = modelMapper.map(principal, ResponseUserAutenticado.class);
        logado.setPermissao(principal.getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority());
        logado.setToken(token);
        return logado;
    }

    public static UserDTO deEspecialistaParaUserDTO(RequestEspecialista especialista) {
        var dto = new UserDTO();
        dto.setAtivo(true);
        dto.setNome(especialista.getNome());
        dto.setEmail(especialista.getEmail());
        dto.setNascidoEm(especialista.getNascidoEm());
        dto.setTelefone(especialista.getTelefone());
        dto.setPermissao(Permisao.MEDICO.getDescricao());
        dto.setWhatsapp(especialista.isWhatsapp());
        return dto;
    }
}
