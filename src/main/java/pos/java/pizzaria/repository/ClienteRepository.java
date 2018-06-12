/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Cliente;

/**
 *
 * @author leonam
 */
public class ClienteRepository {
    
    private EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Cliente cliente) {
        this.manager.persist(cliente);
    }
    
}
