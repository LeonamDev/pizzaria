/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Endereco;

/**
 *
 * @author leonam
 */
public class EnderecoRepository {
    
    private EntityManager manager;

    public EnderecoRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Endereco endereco) {
        this.manager.persist(endereco);
    }
    
}
