/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Categoria;

/**
 *
 * @author leonam
 */
public class CategoriaRepository {
    
    private EntityManager manager;

    public CategoriaRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Categoria categoria) {
        this.manager.persist(categoria);
    }

    
}
