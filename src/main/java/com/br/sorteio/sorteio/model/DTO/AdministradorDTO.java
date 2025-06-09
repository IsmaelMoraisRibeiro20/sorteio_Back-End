package com.br.sorteio.sorteio.model.DTO;

import com.br.sorteio.sorteio.model.AdministradorModel;

public record AdministradorDTO(
		String email,
		String senha) 
{
	public AdministradorModel mapearParaAdmministrador() {
		AdministradorModel adm = new AdministradorModel();
		adm.setEmail(email);
		adm.setSenha(senha);
	
	
		return adm;
	}

}
