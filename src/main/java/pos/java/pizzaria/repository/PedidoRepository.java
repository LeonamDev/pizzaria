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
public class PedidoRepository {

    private EntityManager manager;

    public PedidoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void adicionar(Pedido pedido) {
        this.manager.persist(pedido);
    }

    public List<Pedido> listar() {
        TypedQuery<Pedido> query = manager.createQuery("select p from Pedido p", Pedido.class);
        return query.getResultList();
    }

    public void remover(Pedido pedido) {
        manager.remove(pedido);
    }

    public Pedido encontrar(long id) {
        return manager.find(Pedido.class, id);

    }

    public void editar(Pedido pedido) {
        manager.merge(pedido);
    }

}
