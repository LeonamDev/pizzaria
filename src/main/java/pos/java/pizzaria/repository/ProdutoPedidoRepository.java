/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import pos.java.pizzaria.model.ProdutoPedido;

/**
 *
 * @author leonam
 */
public class ProdutoPedidoRepository extends CommonRepository<ProdutoPedido> {

    public ProdutoPedidoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<ProdutoPedido> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
