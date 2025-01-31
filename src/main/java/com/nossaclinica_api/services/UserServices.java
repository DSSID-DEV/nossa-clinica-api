package com.nossaclinica_api.services;

import com.nossaclinica_api.config.security.utils.ConstantsValues;
import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.UserConverte;
import com.nossaclinica_api.models.dtos.DadosDoUsuario;
import com.nossaclinica_api.models.dtos.UserDTO;
import com.nossaclinica_api.models.dtos.requests.autenticacao.RequestSenha;
import com.nossaclinica_api.models.dtos.requests.users.ResponseUser;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.autenticacao.ResponseUserAutenticado;
import com.nossaclinica_api.models.dtos.responses.autenticacao.ResponseVerificacao;
import com.nossaclinica_api.models.dtos.responses.users.RequestUserProfile;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.models.enums.Permisao;
import com.nossaclinica_api.repositories.RepositoryUser;
import com.nossaclinica_api.utils.ConstantsValue;
import com.nossaclinica_api.utils.Utils;
import com.nossaclinica_api.utils.Verificador;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final RepositoryUser repository;
    private final ClienteServices serviceClientes;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public ResponseUserAutenticado signup(UserDTO user) {
        var usuario = UserConverte.paraEntity(user);
        usuario.setSenha(passwordEncoder.encode(user.getSenha()));
        usuario.setUsername(criarUsername(user.getNome()));
        usuario.setPermissao(Permisao.CLIENTE.getDescricao());
        usuario.setAtivo(true);
        usuario.setAvatar(Utils.criarNomeDoAvatar(usuario.getUsername(), usuario.getPermissao()));
        repository.save(usuario);
        if(usuario.getIdUser() !=null) {
            criarNovoClienteOuMedico(usuario);
        }
        return UserConverte.paraUserLogado(repository.save(usuario));
    }
    public ResponseId registrarNovoUsuario(UserDTO user) {
        var usuario = registrarUsuario(user);
        return ConverteResponseId.doUsuario(usuario);
    }

    public User registrarUsuario(UserDTO user) {
        var senha = gerarSenha(user.getSenha());
        var usuario = UserConverte.paraEntity(user);
        usuario.setUltimaSenha(senha);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setUsername(criarUsername(user.getNome()));
        usuario.setAtivo(true);
        usuario.setAvatar(Utils.criarNomeDoAvatar(usuario.getUsername(), usuario.getPermissao()));

        if(repository.existsUser(usuario.getUsername(), usuario.getEmail(), usuario.getTelefone())) {
            return repository.buscarUsuarioPorUsername(usuario.getUsername());
        }
        return repository.save(usuario);
    }

    private String gerarSenha(String senha) {
        return Verificador.isNotBlank(senha) ? senha :
                Utils.criarSenha();
    }

    private void criarNovoClienteOuMedico(User usuario) {
        if(Verificar.isCliente(usuario.getPermissao())) {
            serviceClientes.registrarNovoCliente(usuario);
        }
    }

    public UserDTO atualizarUsuario(DadosDoUsuario user, Long idUser) {
        var entity = repository.findById(idUser).orElseThrow(() -> {
            throw new RuntimeException("Usuário não encontrado!");
        });
        UserConverte.atualizarCamposAlterados(entity, user);
        repository.save(entity);
        return UserConverte.paraUserDTO(entity);
    }

    public ResponseUser alterarSenha(RequestSenha senha, Long idUser) {
        var entity = repository.findById(idUser)
                .orElseThrow(() -> {
            throw new RuntimeException("Usuário não encontrado na nossa base de dados!");
        });
        entity.setSenha(passwordEncoder
                .encode(senha.getNova()));
        return UserConverte.paraResponseUser(repository.save(entity));
    }

    public UserDTO buscarUsuario(String dados) {
        var user = repository.buscarUsuario(dados).orElseThrow(() -> {
            throw new RuntimeException("Usuário não encontrado na nossa base de dados!");
        });
        return UserConverte.paraUserDTO(user);
    }

    public List<ResponseUser> listarUsuarios() {
        return repository.findAll()
                .stream().map(UserConverte::paraResponseUser).collect(Collectors.toList());
    }

    public ResponseUser buscarPorId(Long idUser) {
        var user = repository.findById(idUser)
                .orElseThrow(() -> {
                    throw new RuntimeException("Não consta em nossa base de dades esse usuário");
                });
        return UserConverte.paraResponseUser(user);
    }

    public RequestUserProfile buscarProfile(Long idUser) {
        var userProfile = repository.findById(idUser)
                .orElseThrow(() -> {
                    throw new RuntimeException("Usuário não encontrado");
                });
        return UserConverte.paraProfile(userProfile);
    }

    public void desativarUsuarios(Long idUser) {
        var user = repository.findById(idUser)
                .orElseThrow(() -> {
                    throw new RuntimeException("Usuário não encontrado");
                });
        user.setAtivo(false);
        repository.save(user);
    }

    public void ativarUsuarios(Long idUser) {
        var user = repository.findById(idUser)
                .orElseThrow(() -> {
                    throw new RuntimeException("Usuário não encontrado");
                });
        user.setAtivo(true);
        repository.save(user);
    }

    public CheckConflito checarConflito(UserDTO user) {
        var checarConflito = new CheckConflito();
        checarConflito.setExiste(false);
        if(repository.existsUser(gerarUsername(user.getNome(), false)
                        .replace(ConstantsValues.DOMINIO_NOSSA_CLINICA, "%"),
                user.getEmail(), user.getTelefone())) {
            checarConflito.setExiste(true);
            checarConflito.setConflito("Usuário já cadastrado em nossa base de dados, tente recuperar sua senha!");
            checarConflito.setMsgLog("Usuário já cadastrado em nossa base de dados");
        }
        return checarConflito;
    }

    private String criarUsername(String nome) {
        var defaultUsername = gerarUsername(removerAcentos(nome), false);
        if(!repository.existsUsername(defaultUsername)) {
            return defaultUsername;
        };
        return gerarUsername(nome, true);
    }

    private String removerAcentos(String nome) {
        String normalizada = Normalizer.normalize(nome, Normalizer.Form.NFD);
        return normalizada.replaceAll(ConstantsValue.ASCII, "");
    }

    private int obterLengthDoArray(String[] string) {
        return string.length -1;
    }

    private String gerarUsername(String nome, boolean addId) {
        String[] values = nome.split(" ");
        int sobrenome = obterLengthDoArray(values);
        return Utils.criarUsernameDefault(values[0].toLowerCase(),
                values[sobrenome].toLowerCase(), addId);
    }

    public boolean notExists(String login) {
        return repository.existsUser(login);
    }

    public ResponseVerificacao criarVerificacao(String login) {
        var verificacao = new ResponseVerificacao();
        var users = repository.buscarAleatoriaComLogin(login);
        for(var user : users){
            if (user.getUsername().equals(login) ||
                    user.getTelefone().equals(login) ||
                    user.getEmail().equals(login)) {
                verificacao.setIdUser(user.getIdUser());
            }
        };
        Collections.shuffle(users);
        AtomicInteger key = new AtomicInteger(0);
        verificacao.setTelefones(users.stream()
                .map(u -> Utils.ocultarCaracteresDoTelefone(u.getTelefone(), key.getAndIncrement())).collect(Collectors.toList()));

        return verificacao;
    }

    public boolean exists(Long idUsuario) {
        return repository.existsById(idUsuario);
    }
}
