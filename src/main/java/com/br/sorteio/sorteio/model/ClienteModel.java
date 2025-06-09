package com.br.sorteio.sorteio.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class ClienteModel {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	
	@Column(name = "Name", length = 100, nullable = false)
	private String nome;
	
	
	@Column(name = "Telefone", nullable = false)
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name = "administrador_id")
	@JsonBackReference
	private AdministradorModel administrador;

	public String getNome() {
		return nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdministradorModel getAdministrador() {
		return administrador;
	}

	public void setAdministrador(AdministradorModel administrador) {
		this.administrador = administrador;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	
	
	
	
}
