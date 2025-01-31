package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.MedicoDTO;
import com.nossaclinica_api.models.dtos.requests.especialistas.RequestEspecialista;
import com.nossaclinica_api.models.dtos.responses.ResponseIdNome;
import com.nossaclinica_api.models.entities.Especialista;
import com.nossaclinica_api.models.entities.User;
import com.nossaclinica_api.models.dtos.responses.especialistas.ResponseEspecialista;
import com.nossaclinica_api.utils.Utils;
import org.modelmapper.ModelMapper;

public class EspecialistaConverte {
    public static MedicoDTO deUserParaEspecialistaDTO(User user) {
        var dto = UserConverte.paraUserDTO(user);
        return MedicoDTO.builder()
                .user(dto)
                .ativo(dto.isAtivo())
                .email(dto.getEmail())
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .whatsapp(dto.isWhatsapp())
                .build();
    }


    public static Especialista paraEntity(RequestEspecialista dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, Especialista.class);
    }

    public static Especialista paraEntity(MedicoDTO dto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(dto, Especialista.class);
    }

    public static MedicoDTO paraDTO(Especialista entity) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(entity, MedicoDTO.class);
    }

    public static ResponseIdNome paraEspecialistaResumido(Especialista entity) {
        return ResponseIdNome.builder()
                .idMedico(entity.getIdEspecialista())
                .nome(entity.getNome())
                .nomeSocial(entity.getNomeSocial())
                .build();
    }

    public static Especialista setarCamposAtualizados(Especialista entity, RequestEspecialista dto) {
        entity.setUf(Utils.verificarString(entity.getUf(), dto.getUf()));
        entity.setConselho(Utils.verificarString(entity.getConselho(), dto.getConselho()));
        entity.setRegistro(Utils.verificarString(entity.getRegistro(), dto.getRegistro()));
        entity.setSexo(Utils.verificarString(entity.getSexo(), dto.getSexo()));
        entity.setNome(Utils.verificarString(entity.getNome(), dto.getNome()));
        entity.setAtivo(dto.isAtivo());
        entity.setEmail(Utils.verificarString(entity.getEmail(), dto.getEmail()));
        entity.setAvatar(Utils.verificarString(entity.getAvatar(), dto.getAvatar()));
        entity.setWhatsapp(dto.isWhatsapp());
        entity.setTelefone(Utils.verificarString(entity.getTelefone(), dto.getTelefone()));
        return entity;
    }

    public static ResponseEspecialista paraResponseEspecialistaDTO(Especialista entity) {
        return ResponseEspecialista.builder()
                .idEspecialista(entity.getIdEspecialista())
                .nome(entity.getNome())
                .nomeSocial(entity.getNomeSocial())
                .sexo(entity.getSexo())
                .nascidoEm(entity.getNascidoEm())
                .conselho(entity.getConselho())
                .registro(entity.getRegistro())
                .uf(entity.getUf())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .isWhatsapp(entity.isWhatsapp())
                .ativo(entity.isAtivo())
                .avatar(entity.getAvatar())
                .build();
    }
}
