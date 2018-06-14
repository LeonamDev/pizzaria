/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.repository;

import javax.persistence.EntityManager;

/**
 *
 * @author leonam
 */
public class CommonRepository<T> {

    private EntityManager manager;

    public CommonRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void adicionar(T t) {
        manager.persist(t);
    }

    public void editar(T t) {
        manager.merge(t);
    }

    public void excluir(T t) {
        manager.remove(t);
    }

    public T encontrar(Class<T> c, long id) {
        return manager.find(c, id);
    }

}
