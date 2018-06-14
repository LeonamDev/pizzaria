/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.pizzaria.model.Pedido;

/**
 *
 * @author leonam
 */
public class PedidoRepository extends CommonRepository<Pedido> {

    public PedidoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Pedido> listar() {
        TypedQuery<Pedido> query = manager.createQuery("select p from Pedido p", Pedido.class);
        return query.getResultList();
    }

}
