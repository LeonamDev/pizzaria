/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.model.Pedido;

/**
 *
 * @author leonam
 */
public class EnderecoRepository extends CommonRepository<Endereco> {

    public EnderecoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Endereco> listar() {
        TypedQuery<Endereco> query = manager.createQuery("select p from Endereco p", Endereco.class);
        return query.getResultList();
    }

}
