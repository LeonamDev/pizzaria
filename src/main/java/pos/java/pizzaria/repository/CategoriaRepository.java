/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.pizzaria.model.Categoria;

/**
 *
 * @author leonam
 */
public class CategoriaRepository extends CommonRepository<Categoria> {

    public CategoriaRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Categoria> listar() {
        TypedQuery<Categoria> query = manager.createQuery("select p from Categoria p", Categoria.class);
        return query.getResultList();
    }

}
