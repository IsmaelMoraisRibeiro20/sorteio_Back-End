package com.br.sorteio.sorteio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sorteio.sorteio.model.AdministradorModel;
import com.br.sorteio.sorteio.model.ClienteModel;
import com.br.sorteio.sorteio.repository.AdministradorRepository;
import com.br.sorteio.sorteio.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	
	
	//lista produto
	public List<ClienteModel> listarCliente(){
		return clienteRepository.findAll();
	}
		
		
	//Obter por id 
	public ClienteModel obterPorID(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	
	//salvar
	public ClienteModel salvar(ClienteModel cliente) {
		// Garante que o administrador já exista ou esteja salvo
	    AdministradorModel adm = administradorRepository.findById(cliente.getAdministrador().getId())
	        .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
	    
	    cliente.setAdministrador(adm);
		
		return clienteRepository.save(cliente);
	}
	
	//Editar
	public ClienteModel editar(Long id, ClienteModel clienteAlterado) {
		ClienteModel clienteAtual = obterPorID(id);
		clienteAtual.setTelefone(clienteAlterado.getTelefone());
		clienteAtual.setNome(clienteAlterado.getNome());
		return clienteRepository.save(clienteAtual);
	}
	
	
	//deletar
	public Boolean deletar(Long id) {
		ClienteModel cliente = obterPorID(id);
		if(cliente == null) {
			return false;
		}else {
			clienteRepository.deleteById(id);
			return true;
		}
	}
	
	
	
			
}
