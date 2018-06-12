/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.repository.ClienteRepository;

/**
 *
 * @author leonam
 */
public class ClienteService {
    
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrar(Cliente cliente) {
        this.clienteRepository.adicionar(cliente);
    }

    
}
