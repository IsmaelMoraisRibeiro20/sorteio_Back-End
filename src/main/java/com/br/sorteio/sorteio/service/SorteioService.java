package com.br.sorteio.sorteio.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.br.sorteio.sorteio.model.ClienteModel;
import com.br.sorteio.sorteio.repository.ClienteRepository;

@Service
public class SorteioService {
	
	private final ClienteRepository clienteRepository;
    private final Random random = new Random();

    public SorteioService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteModel realizarSorteio() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        
        if (clientes.isEmpty()) {
            return null; // Ou lançar exceção se preferir
        }

        int indiceSorteado = random.nextInt(clientes.size());
        return clientes.get(indiceSorteado);
    }

}
