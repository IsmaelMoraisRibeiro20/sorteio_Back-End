package com.br.sorteio.sorteio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.br.sorteio.sorteio.model.AdministradorModel;
import com.br.sorteio.sorteio.model.ClienteModel;
import com.br.sorteio.sorteio.repository.AdministradorRepository;
import com.br.sorteio.sorteio.repository.ClienteRepository;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository admRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public List<ClienteModel> findByAll(ClienteModel cliente, Long administradorId) {
	    ExampleMatcher matcher = ExampleMatcher.matching()
	            .withIgnoreNullValues()
	            .withIgnoreCase()
	            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

	    cliente.setAdministrador(new AdministradorModel());
	    cliente.getAdministrador().setId(administradorId);

	    Example<ClienteModel> example = Example.of(cliente, matcher);

	    return clienteRepository.findAll(example);
	}
	
	public AdministradorModel findById(Long id) {
		return admRepository.findById(id).orElse(null);
	}
	
	
	
	public AdministradorModel salvar(AdministradorModel administrador) {
		 // Se você estiver criando administrador com clientes juntos:
        if (administrador.getClienteModel() != null) {
            for (ClienteModel cliente : administrador.getClienteModel()) {
                cliente.setAdministrador(administrador);
            }
        }

        return admRepository.save(administrador);
	}
	
	public AdministradorModel update(Long id, AdministradorModel administradorAlterado) {
		AdministradorModel admAtual = findById(id);
		admAtual.setEmail(administradorAlterado.getEmail());
		admAtual.setSenha(administradorAlterado.getSenha());
		return admRepository.save(admAtual);
	}
	
	public boolean deletar(Long id) {
		AdministradorModel adm = findById(id);
		if(adm == null) {
			return false;
		}else {
			admRepository.delete(adm);
			return true;	
		}
		
		
		
	}
	
	
	
}
