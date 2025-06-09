package com.br.sorteio.sorteio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.sorteio.sorteio.model.AdministradorModel;
import com.br.sorteio.sorteio.model.ClienteModel;
import com.br.sorteio.sorteio.model.DTO.AdministradorDTO;
import com.br.sorteio.sorteio.model.DTO.ClienteDTO;
import com.br.sorteio.sorteio.service.AdministradorService;
import com.br.sorteio.sorteio.service.SorteioService;

@Controller
@RequestMapping("/administrador")
@CrossOrigin(origins = "http://localhost:3000")
public class Administradorcontroller {
	
	@Autowired
	private AdministradorService admService;
	
	@Autowired
	private SorteioService sorteioService;
	
	
	@GetMapping("/{administradorId}/clientes")
    public ResponseEntity<List<ClienteModel>> listarClientesPorAdministrador(
            @PathVariable Long administradorId,
            @ModelAttribute ClienteDTO clienteDTO) {
		
		ClienteModel filtro = clienteDTO.mapearParaCliente();
        
        List<ClienteModel> clientes = admService.findByAll(filtro, administradorId);
        return ResponseEntity.ok(clientes);
    }
	
	@GetMapping("/obterPorID")
	public ResponseEntity<AdministradorModel> findById(@PathVariable Long id) {
		
		AdministradorModel adm =admService.findById(id);
		return ResponseEntity.ok().body(adm);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<AdministradorModel> salvar(@RequestBody AdministradorDTO admDTO) {
		AdministradorModel admModel = admDTO.mapearParaAdmministrador();
		
		AdministradorModel admNew = admService.salvar(admModel);
		return ResponseEntity.ok().body(admNew);
	}
	
	
	@DeleteMapping("/delete")
	public  ResponseEntity<Boolean> deletar(@PathVariable AdministradorDTO admDTO) {
		AdministradorModel admModel = admDTO.mapearParaAdmministrador();
		
		boolean admM = admService.deletar(admModel.getId());
		return ResponseEntity.ok().body(admM);

	}
	
	
	  @GetMapping("/sorteio")
	  public ResponseEntity<?> sortearCliente() {
	     ClienteModel sorteado = sorteioService.realizarSorteio();

	     if (sorteado == null) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum cliente cadastrado.");
	     }

	     // Retorna apenas nome e telefone
	     Map<String, String> resultado = new HashMap<>();
	     resultado.put("nome", sorteado.getNome());
	     resultado.put("telefone", sorteado.getTelefone());

	     return ResponseEntity.ok(resultado);
	    }
	
	

}
