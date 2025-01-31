package com.nossaclinica_api.services;

import com.nossaclinica_api.controllers.common.CheckConflito;
import com.nossaclinica_api.converters.ConverteResponseId;
import com.nossaclinica_api.converters.EspecialistaConverte;
import com.nossaclinica_api.converters.UserConverte;
import com.nossaclinica_api.models.dtos.requests.especialistas.RequestEspecialista;
import com.nossaclinica_api.models.dtos.responses.ResponseIdNome;
import com.nossaclinica_api.models.entities.Especialista;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.dtos.responses.especialistas.ResponseEspecialista;
import com.nossaclinica_api.repositories.EspecialistaRepository;
import com.nossaclinica_api.repositories.custom.RepositoryEspecialistaCustom;
import com.nossaclinica_api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialistaServices {

    private final EspecialistaRepository repository;
    private final RepositoryEspecialistaCustom repositoryCustom;
    private final UserServices services;

    public void registrarNovoEspecialista(User user) {
        var especialistaDTO = EspecialistaConverte.deUserParaEspecialistaDTO(user);
        repository.save(EspecialistaConverte.paraEntity(especialistaDTO));
    }

    @Transactional
    public ResponseId registrarNovoEspecialista(RequestEspecialista dto) {
        var entity = EspecialistaConverte.paraEntity(dto);
        if(dto.getUser() == null) {
            var userDTO = UserConverte.deEspecialistaParaUserDTO(dto);
            var userEntity = services.registrarUsuario(userDTO);
            entity.setUser(userEntity);
            entity.setAvatar(userEntity.getAvatar());
        }
        entity.setAtivo(true);
        return ConverteResponseId.doEspecialista(repository.save(entity));
    }

    public CheckConflito checarConflito(RequestEspecialista especialista) {
        var checarConflito = new CheckConflito();
        checarConflito.setExiste(false);
        if(existsClienteCadastrado(especialista)) {
            checarConflito.setExiste(true);
            checarConflito.setConflito("o Especialista já consta em nossa base de dados");
            checarConflito.setMsgLog("o Especialista já consta em nossa base de dados");
        }
        return checarConflito;
    }

    public boolean existsClienteCadastrado(RequestEspecialista dto) {
        return repository.existsEspecialista(Utils.fullLike(dto.getNome()), dto.getTelefone(),
                dto.getNascidoEm(), dto.getEmail());
    }

    public boolean existsEspecialista(Long idEspecialista) {
        return repository.existsById(idEspecialista);
    }

    public ResponseEspecialista atualizarEspecialista(RequestEspecialista especialista, Long idEspecialista) {

        var entity = getEspecialistaById(idEspecialista);
        EspecialistaConverte.setarCamposAtualizados(entity, especialista);

        return EspecialistaConverte.paraResponseEspecialistaDTO(repository.save(entity));

    }

    public ResponseId reativarEspecialista(Long idEspecialista) {
        var entity = getEspecialistaById(idEspecialista);
        entity.setAtivo(true);
        return ConverteResponseId.doEspecialista(repository.save(entity));
    }

    public Especialista getEspecialistaById(Long idCliente) {
        return repository.getById(idCliente);
    }

    public ResponseEspecialista buscarEspecialistaPorId(Long idEspecialista) {
        var entity = getEspecialistaById(idEspecialista);
        return EspecialistaConverte.paraResponseEspecialistaDTO(entity);
    }

    public List<ResponseEspecialista> listarTodosDoParametro(String dadosDaPesquisa, boolean ativo) {
        return repositoryCustom.findAllAtivos(dadosDaPesquisa, ativo)
                .stream()
                .map(EspecialistaConverte::paraResponseEspecialistaDTO)
                .collect(Collectors.toList());
    }

    public void desativarEspecialista(Long idEspecialista) {
        var entity = getEspecialistaById(idEspecialista);
        entity.setAtivo(false);
        repository.save(entity);
    }

    public ResponseIdNome buscarEspecialistaResumido(Long idEspecialista) {
        var especialista = getEspecialistaById(idEspecialista);
        return EspecialistaConverte.paraEspecialistaResumido(especialista);
    }

}
