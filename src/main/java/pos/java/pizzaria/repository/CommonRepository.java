/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author leonam
 * @param <T>
 */
public abstract class CommonRepository<T> {

    protected EntityManager manager;

    public CommonRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void beginTransatcion() {
        manager.getTransaction().begin();
    }

    public void commitTransaction() {
        manager.getTransaction().commit();
    }

    public void adicionar(T t) {
        manager.persist(t);
    }

    public void editar(T t) {
        manager.merge(t);
    }

    public void remover(T t) {
        manager.remove(t);
    }

    public T encontrar(Class<T> c, String idd) {
        Long id = Long.parseLong(idd);
        return manager.find(c, id);
    }

    public abstract List<T> listar();

}
