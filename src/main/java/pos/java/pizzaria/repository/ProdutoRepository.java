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
import pos.java.pizzaria.model.Produto;

/**
 *
 * @author leonam
 */
public class ProdutoRepository extends CommonRepository<Produto> {

    public ProdutoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Produto> listar() {
        TypedQuery<Produto> query = manager.createQuery("select p from Produto p", Produto.class);
        return query.getResultList();
    }

}
