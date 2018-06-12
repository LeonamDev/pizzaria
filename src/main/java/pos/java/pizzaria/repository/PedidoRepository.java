/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;
import pos.java.pizzaria.model.Pedido;

/**
 *
 * @author leonam
 */
public class PedidoRepository {
    
    private EntityManager manager;

    public PedidoRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public void adicionar(Pedido pedido) {
        this.manager.persist(pedido);
    }
    
}
