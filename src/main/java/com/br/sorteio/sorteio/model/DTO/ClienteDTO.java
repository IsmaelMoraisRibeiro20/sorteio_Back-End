package com.br.sorteio.sorteio.model.DTO;

import com.br.sorteio.sorteio.model.AdministradorModel;
import com.br.sorteio.sorteio.model.ClienteModel;

public record ClienteDTO(
		String nome,
		String telefone,
		AdministradorModel adm) {
	
	public ClienteModel mapearParaCliente() {
		ClienteModel cliente = new ClienteModel();
		
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		
		if(adm != null && adm.getId() != null) {
			AdministradorModel admModel = new AdministradorModel();
			admModel.setId(adm.getId());
			cliente.setAdministrador(admModel);
		}
		
	
		return cliente;
	}
	
}

