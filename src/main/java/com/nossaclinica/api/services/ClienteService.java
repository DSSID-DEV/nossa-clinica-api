package com.nossaclinica.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.nossaclinica.api.enums.NaoSim;
import com.nossaclinica.api.enums.Permissao;
import com.nossaclinica.api.models.dtos.CidadeDTO;
import com.nossaclinica.api.models.dtos.ClienteDTO;
import com.nossaclinica.api.models.dtos.ContatoDTO;
import com.nossaclinica.api.models.dtos.EnderecoDTO;
import com.nossaclinica.api.models.dtos.UsuarioDTO;
import com.nossaclinica.api.models.entities.Cidade;
import com.nossaclinica.api.models.entities.Cliente;
import com.nossaclinica.api.models.entities.Contato;
import com.nossaclinica.api.models.entities.Endereco;
import com.nossaclinica.api.models.entities.Usuario;
import com.nossaclinica.api.models.filters.ClienteFilter;
import com.nossaclinica.api.models.summaries.dtos.IdNomeDTO;
import com.nossaclinica.api.repositories.CidadeRepository;
import com.nossaclinica.api.repositories.ClienteRepository;
import com.nossaclinica.api.repositories.UsuarioRepository;
import com.nossaclinica.api.repositories.impl.ClienteRepositoryImpl;

@Service
@Component
public class ClienteService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteRepositoryImpl repositoryImpl;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private ModelMapper modelMapper;
	
	private ClienteDTO toDTO(Cliente entity) {
		modelMapper = new ModelMapper();
		return modelMapper.map(entity, ClienteDTO.class);
	}
	
	private IdNomeDTO toIdNomeDTO(Cliente entity) {
		modelMapper = new ModelMapper();
		return modelMapper.map(entity, IdNomeDTO.class);
	}
	
	private Cliente toEntity(ClienteDTO dto) {
		modelMapper = new ModelMapper();
		return modelMapper.map(dto, Cliente.class);
	}
	
	private Endereco toEnderecoEntity(EnderecoDTO dto) {
		modelMapper = new ModelMapper();
		return modelMapper.map(dto, Endereco.class);
	}

	private Contato toContatoEntity(ContatoDTO dto) {
		modelMapper = new ModelMapper();
		return modelMapper.map(dto, Contato.class);
	}

	private Usuario toUsuarioEntity(UsuarioDTO dto) {
		modelMapper = new ModelMapper();
		return modelMapper.map(dto, Usuario.class);
	}

	public ResponseEntity<ClienteDTO> salvar(ClienteDTO dto) {
		
		dto.setUsuario(carregarUsuarioDefault(dto));
		dto.getEndereco().setCidade(
				buscarCidade(dto.getEndereco().getCidade()));
		return ResponseEntity.status(HttpStatus.CREATED)
					.body(toDTO(repository.save(toEntity(dto))));
	}

	public ResponseEntity<Boolean> atualizar(ClienteDTO cliente) {
		
		try{
			repository.save(toEntity(cliente));
			return ResponseEntity.ok(Boolean.TRUE);
		}catch (BadRequest e) {
			return ResponseEntity.badRequest().build();
		}catch (Exception e) {
			logger.error(String.format("Erro ao tentar atualizar novo cliente", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}

	public ResponseEntity<Boolean> atualizarEndereco(Long id, EnderecoDTO dto) {
		Cliente cliente = repository.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.noContent().build();
		}
		
		Endereco endereco = toEnderecoEntity(dto);
		cliente.setEndereco(endereco);
		try {
			repository.save(cliente);
			return ResponseEntity.ok().body(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(String.format("Erro ao tentar atualizar endereço do cliente", e.getMessage()));
			return ResponseEntity.badRequest().body(Boolean.FALSE);
		}
		
	}

	
	public ResponseEntity<Boolean> atualizarContato(Long id, ContatoDTO dto) {
		Cliente cliente = repository.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.noContent().build();
		}
				
		Contato contato = toContatoEntity(dto);
		cliente.setContato(contato);

		try {
			repository.save(cliente);
			return ResponseEntity.ok().body(Boolean.TRUE);
		} catch (Exception e) {
			logger.error(String.format("Erro ao tentar atualizar contato do cliente", e.getMessage()));
			return ResponseEntity.badRequest().body(Boolean.FALSE);
		}
	}
	
	@Transactional
	public ResponseEntity<Boolean> atualizarUsuario(Long id, UsuarioDTO dto) {
		Cliente cliente = repository.findById(id).orElse(null);
		if (cliente == null) {
			return ResponseEntity.noContent().build();
		}
				
		Usuario usuario = toUsuarioEntity(dto);
		usuario.setContato(cliente.getContato());
		

		try {
			usuarioRepository.save(usuario);
			return ResponseEntity.ok().body(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("Erro ao tentar atualizar contato do cliente", e.getMessage()));
			return ResponseEntity.badRequest().body(Boolean.FALSE);
		}
	}



	public ResponseEntity<List<ClienteDTO>> listarTodos() {
		List<Cliente> clientes = repository.findAll();				
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		} 
		return ResponseEntity.ok(clientes.stream().map(c -> toDTO(c))
				.collect(Collectors.toList()));		
	}
	
	
	public ResponseEntity<List<ClienteDTO>> buscarPorFiltro(ClienteFilter filtro) {
		
		List<Cliente> clientes = repositoryImpl.buscarPorFiltro(filtro);
	
		if(clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok()
				.body(clientes.stream().map(c -> toDTO(c))
						.collect(Collectors.toList()));
	}
	
	public ResponseEntity<List<IdNomeDTO>> buscarIdNomeDoCliente(ClienteFilter filter) {
		List<Cliente> clientes = repositoryImpl.buscarPorFiltro(filter);
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok()
				.body(clientes.stream().map(c -> toIdNomeDTO(c))
						.collect(Collectors.toList()));
	}

	public ResponseEntity<?> remover(Long id) {
		try{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}catch (BadRequest e) {
			return ResponseEntity.badRequest().build();
		}catch (Exception e) {	
			logger.error(String.format("Erro ao tentar excluir cliente", e.getMessage()));
			return ResponseEntity.internalServerError().build();
		}
	}

	
	private UsuarioDTO carregarUsuarioDefault(ClienteDTO dto) {
		return UsuarioDTO.builder()
				.userName(gerarUserName(dto))
				.senha(gerarSenha())
				.contato(dto.getContato())
				.permissao(Permissao.CLIENTE)
				.ativo(NaoSim.N)
				.build();		
	}
	
	
	private String gerarSenha() {
		String password = UUID.randomUUID().toString();
		return password.length() > 8 ? password.substring(0, 8) : password;
	}


	private String gerarUserName(ClienteDTO cliente) {
		String[] nomes = cliente.getNome().split(" ");
		int qtdNomes = nomes.length;
		String firstName = nomes[0];
		String next = (qtdNomes > 1) ? nomes[qtdNomes - 1] : cliente.getCpf().substring(0, 2) + cliente.getCpf().substring(8);
		return firstName + "." + next;
	}

	private CidadeDTO buscarCidade(CidadeDTO dto) {
		this.modelMapper = new ModelMapper();
		Cidade cidade = cidadeRepository.findById(dto.getIdCidade()).get();
		return this.modelMapper.map(cidade, CidadeDTO.class);
	}




}
