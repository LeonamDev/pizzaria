/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Produto;

/**
 *
 * @author leonam
 */
public class ProdutoRepository {
    
    private EntityManager manager;

    public ProdutoRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Produto produto) {
        this.manager.persist(produto);
    }
    
}
