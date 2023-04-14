package com.nossaclinica.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossaclinica.api.enums.ModoDeRecuperacao;
import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.models.entities.Usuario;
import com.nossaclinica.api.models.summaries.dtos.DadosMascaradosParaRecuperacaoDaSenhaDTO;
import com.nossaclinica.api.models.summaries.dtos.IdUsuarioModoDeRecuperacaoDTO;
import com.nossaclinica.api.models.summaries.dtos.MsgDeEnvioDaSenhaProvisoriaDTO;
import com.nossaclinica.api.models.summaries.dtos.UserNameRecuperarSenha;
import com.nossaclinica.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class) ;

	@Autowired
	private UsuarioRepository repository;
	
	
	public ResponseEntity<List<DadosMascaradosParaRecuperacaoDaSenhaDTO>> recuperarSenha(UserNameRecuperarSenha dados) {
		List<Usuario> usuarios = repository.buscarPorUserName(dados.getUsername());
				
		if (usuarios.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		
		if (!dados.getRecuperarSenha()) {
			ResponseEntity.badRequest().build();
		}

		List<DadosMascaradosParaRecuperacaoDaSenhaDTO> listDados = 
				usuarios.stream().map(u -> mascararDados(u))
				.collect(Collectors.toList());
			
		return ResponseEntity.ok().body(listDados);
	}

	private DadosMascaradosParaRecuperacaoDaSenhaDTO mascararDados(Usuario usuario) {
		DadosMascaradosParaRecuperacaoDaSenhaDTO dto = new DadosMascaradosParaRecuperacaoDaSenhaDTO();
		dto.setIdUsuario(usuario.getIdUsuario());
		dto.setNome(mascararUsuario(usuario.getUserName()));
		if (usuario.getContato() != null) {
			
			if (usuario.getContato().getIsWhatsApp().equals(NaoSim.S)) {
				dto.setWhatsApp(mascararCelular(usuario.getContato().getCelular()));
			}
			
			dto.setSms(mascararCelular(usuario.getContato().getCelular()));
			dto.setEmail(mascararEmail(usuario.getContato().getEmail()));
		}
		
		
		return dto;
	}

	private String mascararUsuario(String userName) {
//		String nomes [] = userName.split(".");
//		int lenght = nomes[1].length();
		System.out.println("Tamanho -> "+ userName);
		return userName;
	}

	private String mascararCelular(String celular) {
		return celular.substring(0,5)+"*".repeat(2)+"-"+"*".repeat(2)+celular.substring(9);		
	}


	private String mascararEmail(String email) {
		String[] slipt = email.split("@");
		int length = slipt[0].length();
		return slipt[0].substring(0,2) + 
				"*".repeat((length - 2))
				+ slipt[0].substring(length - 2)
				+"@"+slipt[1];
	}


	public ResponseEntity<MsgDeEnvioDaSenhaProvisoriaDTO> senhaProvisoria(IdUsuarioModoDeRecuperacaoDTO dados) {
		String senhaProvisoria = (String) UUID.randomUUID()
				.toString().subSequence(0, 6);
		
		Usuario usuario = repository.findById(dados.getIdUsuario()).orElse(null);
		
		if (usuario == null) {
			return ResponseEntity.noContent().build();
		}
		
		
		MsgDeEnvioDaSenhaProvisoriaDTO msg = new MsgDeEnvioDaSenhaProvisoriaDTO();
		msg.setLinkDeRecuperacao("https:www.google.com.br");
		
		if (dados.getRecuperarPor().equals(ModoDeRecuperacao.EMAIL)) {
			msg.setMsg("Foi encaminhado uma nova senha para o seu email, clique no link e acesse com a senha informada");
			LOGGER.info(String.format("Email enviado para o email %s a senha provisória %s", dados.getRecuperarComEsseDado(), senhaProvisoria));
		}
		
		if (dados.getRecuperarPor().equals(ModoDeRecuperacao.WHATSAPP)) {
			msg.setMsg("Foi encaminhado uma nova senha para o seu whatsapp, clique no link e acesse com a senha informada");
			LOGGER.info(String.format("Email enviado para o whatsapp %s a senha provisória %s", dados.getRecuperarComEsseDado(), senhaProvisoria));
		}
		
		if (dados.getRecuperarPor().equals(ModoDeRecuperacao.SMS)) {
			msg.setMsg("Foi encaminhado uma nova senha para o seu msm, clique no link e acesse com a senha informada");
			LOGGER.info(String.format("Email enviado para o SMS %s a senha provisória %s", dados.getRecuperarComEsseDado(), senhaProvisoria));
		}
		return ResponseEntity.ok().body(msg);
	}
}
