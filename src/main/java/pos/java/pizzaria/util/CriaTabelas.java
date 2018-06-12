/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.service.CategoriaService;
import pos.java.pizzaria.service.ClienteService;
import pos.java.pizzaria.service.IngredienteService;
import pos.java.pizzaria.service.ProdutoService;

/**
 *
 * @author leonam
 */
public class CriaTabelas {

    public static void main(String[] args) {

        Persistence.createEntityManagerFactory("pizzaria");

        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction trx = manager.getTransaction();

        Categoria c1 = new Categoria("Empanados");
        Produto p1 = new Produto(01, "Frango empanado", c1, 27);
        Ingrediente i1 = new Ingrediente("Frango", p1);
        Ingrediente i2 = new Ingrediente("Farinha de rosca", p1);
        Cliente cliente = new Cliente(997979784,null, "Ricardo");

        p1.getIngredientes().add(i1);
        p1.getIngredientes().add(i2);

        try {
            trx.begin();
            
            ClienteService clienteService
                    = new ClienteService(new ClienteRepository(manager));

            ProdutoService produtoService
                    = new ProdutoService(new ProdutoRepository(manager));
            CategoriaService categoriaService
                    = new CategoriaService(new CategoriaRepository(manager));
            IngredienteService ingredienteService
                    = new IngredienteService(new IngredienteRepository(manager));

            categoriaService.cadastrar(c1);
            ingredienteService.cadastrar(i1);
            ingredienteService.cadastrar(i2);
            produtoService.cadastrar(p1);
            clienteService.cadastrar(cliente);
            
            

            trx.commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            
            Cliente clienteOne;
            clienteOne = manager.find(Cliente.class, 1L);
            System.out.println("Cliente: " + clienteOne.getNome());
            System.out.println("Telefone: " + clienteOne.getTelefone());
            manager.close();
            
        }
        
        
        

    }

}
