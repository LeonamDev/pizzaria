/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.model.ProdutoPedido;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.EnderecoRepository;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.repository.PedidoRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.service.CategoriaService;
import pos.java.pizzaria.service.ClienteService;
import pos.java.pizzaria.service.EnderecoService;
import pos.java.pizzaria.service.IngredienteService;
import pos.java.pizzaria.service.PedidoService;
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
        Cliente cliente = new Cliente(997979784, "Ricardo");
        Endereco endereco = new Endereco("Rua oliveiros", 17, "Perto do Shopping", cliente);

        p1.getIngredientes().add(i1);
        p1.getIngredientes().add(i2);

        Pedido pedido = new Pedido(cliente, endereco, true, 23, 5, 70, 10,
                LocalDate.now(), LocalDateTime.now(), 1);

        ProdutoPedido produtoPedido = new ProdutoPedido(p1, pedido, 5, "Bem quente, por favor.");

        p1.getProdutoPedidos().add(produtoPedido);

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
            PedidoService pedidoService
                    = new PedidoService(new PedidoRepository(manager));
            EnderecoService enderecoService
                    = new EnderecoService(new EnderecoRepository(manager));

            categoriaService.cadastrar(c1);
            ingredienteService.cadastrar(i1);
            ingredienteService.cadastrar(i2);
            pedidoService.cadastrar(pedido);
            produtoService.cadastrar(p1);
            enderecoService.cadastrar(endereco);
            clienteService.cadastrar(cliente);

            trx.commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            manager.close();

        }

    }

}
