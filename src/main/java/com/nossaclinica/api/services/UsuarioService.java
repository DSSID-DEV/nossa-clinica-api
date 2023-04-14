package com.nossaclinica.api.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.models.entities.Usuario;
import com.nossaclinica.api.models.summaries.dtos.UserNameNascidoEm;
import com.nossaclinica.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	public ResponseEntity<UserNameNascidoEm> recuperarSenha(UserNameNascidoEm dados) {
		Usuario usuario = repository.buscarPorUserNameEDataDeNascimento(dados.getUsername(), 
				dados.getNascidoEm());
		
		if (usuario == null) {
			ResponseEntity.noContent().build();
		}
		
		String emailMascarado = mascararEmail(usuario.getContato().getEmail());
		dados.setEmail(emailMascarado);
		
		if (usuario.getContato().getIsWhatsApp().equals(NaoSim.S)) {
			String whatsAppMascarado = mascararWhatsApp(usuario.getContato().getCelular());
			dados.setWhatsApp(whatsAppMascarado);
		}
		
		return ResponseEntity.ok().body(dados);
	}


	private String mascararWhatsApp(String celular) {
		return celular.substring(0,4)+"*".repeat(2)+"-"+"*".repeat(2)+celular.substring(8);		
	}


	private String mascararEmail(String email) {
		String[] slipt = email.split("@");
		int length = slipt[0].length();
		return slipt[0].substring(0,1) + "*".repeat((length - 2))+"@"+slipt[1];
	}


	public ResponseEntity<UserNameNascidoEm> senhaProvisoria(UserNameNascidoEm dados) {
		String senhaProvisoria = (String) UUID.randomUUID()
				.toString().subSequence(0, 6);
		dados.setSenhaProvisoria(senhaProvisoria);
		if (dados.isViaEmail()) {
			
		}
		
		if (dados.isViaWhatsApp()) {
			
		}
		return ResponseEntity.ok().body(dados);
	}
}
