/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pos.java.pizzaria.model.Ingrediente;

/**
 *
 * @author leonam
 */
public class IngredienteRepository extends CommonRepository<Ingrediente> {

    public IngredienteRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public List<Ingrediente> listar() {
        TypedQuery<Ingrediente> query = manager.createQuery("select p from Ingrediente p", Ingrediente.class);
        return query.getResultList();
    }

}
