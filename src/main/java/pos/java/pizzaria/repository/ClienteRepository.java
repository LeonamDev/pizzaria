/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.pizzaria.model.Cliente;

/**
 *
 * @author leonam
 */
public class ClienteRepository extends CommonRepository<Cliente> {

    public ClienteRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Cliente> listar() {
        TypedQuery<Cliente> query = manager.createQuery("select p from Cliente p", Cliente.class);
        return query.getResultList();
    }

}
