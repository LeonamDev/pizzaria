/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Ingrediente;

/**
 *
 * @author leonam
 */
public class IngredienteRepository {
    
     private EntityManager manager;

    public IngredienteRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Ingrediente ingrediente) {
        this.manager.persist(ingrediente);
    }

    
}