package com.br.sorteio.sorteio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.sorteio.sorteio.model.ClienteModel;
import com.br.sorteio.sorteio.model.DTO.ClienteDTO;
import com.br.sorteio.sorteio.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<ClienteModel>> findAll(){
		List<ClienteModel> clientes = clienteService.listarCliente();
		return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> findById(@PathVariable Long id){
		ClienteModel cliente = clienteService.obterPorID(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<ClienteModel> inserirCliente(@RequestBody ClienteDTO clienteDTO){
		
		ClienteModel cliente = clienteDTO.mapearParaCliente();
		ClienteModel clienteNew = clienteService.salvar(cliente);
		return ResponseEntity.ok().body(clienteNew);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteModel> editar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
		ClienteModel clienteAlterado = clienteDTO.mapearParaCliente();
		ClienteModel cliente = clienteService.editar(id, clienteAlterado);
		return ResponseEntity.ok().body(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
		Boolean flag = clienteService.deletar(id);
		return ResponseEntity.ok().body(flag);
	}
	
}

