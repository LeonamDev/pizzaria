/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import pos.java.pizzaria.repository.PedidoRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.service.CategoriaService;
import pos.java.pizzaria.service.ClienteService;
import pos.java.pizzaria.service.EnderecoService;
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

        List<Categoria> categorias = new ArrayList();
        Categoria c1 = new Categoria("Empanados");
        Categoria c2 = new Categoria("Massas");
        categorias.add(c1);
        categorias.add(c2);

        List<Produto> produtos = new ArrayList();
        Produto p1 = new Produto(01, "Frango empanado", c1, 27);
        Produto p2 = new Produto(01, "Pizza de 6 queijos", c2, 20);
        produtos.add(p1);
        produtos.add(p2);

        Ingrediente i1 = new Ingrediente("Frango");
        Ingrediente i2 = new Ingrediente("Farinha de rosca");

        Ingrediente i3 = new Ingrediente("Queijo");
        Ingrediente i4 = new Ingrediente("Oregano");

        Cliente cliente = new Cliente(997979784, "Ricardo");
        Endereco endereco = new Endereco("Rua oliveiros", 17, "Perto do Shopping", cliente);

        p1.getIngredientes().add(i1);
        p1.getIngredientes().add(i2);

        p2.getIngredientes().add(i3);
        p2.getIngredientes().add(i4);
        

        List<Pedido> pedidos = new ArrayList();
        Pedido pedido = new Pedido(cliente, endereco, true, 23, 5, 70, 10,
                new java.sql.Date(System.currentTimeMillis()),  new java.sql.Date(System.currentTimeMillis()), 1);
        Pedido pedido2 = new Pedido(cliente, endereco, true, 23, 5, 70, 10,
                 new java.sql.Date(System.currentTimeMillis()),  new java.sql.Date(System.currentTimeMillis()), 1);
        pedidos.add(pedido);
        pedidos.add(pedido2);

        ProdutoPedido produtoPedido = new ProdutoPedido(p1, pedido, 5, "Bem quente, por favor.");
        ProdutoPedido produtoPedido2 = new ProdutoPedido(p2, pedido2, 5, "Com bastante queijo!");

        p1.getProdutoPedidos().add(produtoPedido);
        p2.getProdutoPedidos().add(produtoPedido2);

        try {
            trx.begin();

            ClienteService clienteService
                    = new ClienteService(new ClienteRepository(manager));
            ProdutoService produtoService
                    = new ProdutoService(new ProdutoRepository(manager));
            CategoriaService categoriaService
                    = new CategoriaService(new CategoriaRepository(manager));
            PedidoService pedidoService
                    = new PedidoService(new PedidoRepository(manager));
            EnderecoService enderecoService
                    = new EnderecoService(new EnderecoRepository(manager));

            for (Categoria categoria : categorias) {
                categoriaService.cadastrar(categoria);
            }

            for (Pedido pedid : pedidos) {
                pedidoService.cadastrar(pedid);
            }
            for (Produto produto : produtos) {
                produtoService.cadastrar(produto);
            }

            enderecoService.cadastrar(endereco);

            clienteService.cadastrar(cliente);

            trx.commit();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }

        }

        PedidoRepository pedidoRepository = new PedidoRepository(manager);

        List<Pedido> pedids = pedidoRepository.listar();
        

        for (Pedido pd : pedids) {
            System.out.println("ID:" + pd.getId() + " - " + "Valor:" + pd.getValor() + "Endereco: " + pd.getEndereco().getRua());
            
           for(ProdutoPedido pdp: pd.getProdutoPedidos()){
               System.out.println(pdp.getProduto().getNome());
           }
               
        }
        manager.close();

    }

}
