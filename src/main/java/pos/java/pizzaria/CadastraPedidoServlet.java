/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.form.PedidoForm;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.model.ProdutoPedido;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.EnderecoRepository;
import pos.java.pizzaria.repository.PedidoRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.service.ServiceException;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/cadastra-pedido")
public class CadastraPedidoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();

        ProdutoRepository produtoRepository = new ProdutoRepository(manager);
        ClienteRepository clienteRepository = new ClienteRepository(manager);

        try {

            List<Produto> todosProdutos = produtoRepository.listar();
            List<Cliente> todosClientes = clienteRepository.listar();

            request.setAttribute("produtos", todosProdutos);
            request.setAttribute("clientes", todosClientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/cadastra-pedido.jsp");
            dispatcher.forward(request, response);

        } finally {
            manager.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();
        PedidoRepository pedidos = new PedidoRepository(manager);

        ClienteRepository clienteRepository = new ClienteRepository(manager);
        EnderecoRepository enderecoRepository = new EnderecoRepository(manager);

        try {

            String[] produtoIDs;

            produtoIDs = request.getParameterValues("produtoId");

            ProdutoRepository produtoRepository = new ProdutoRepository(manager);
            PedidoForm form = PedidoForm.fromRequest(request);
            Pedido pedido = form.toPedido();

            Endereco endereco = enderecoRepository.encontrar(Endereco.class, new Long(request.getParameter("enderecoId")));
            Cliente cliente = clienteRepository.encontrar(Cliente.class, endereco.getCliente().getId());

            pedido.setCliente(cliente);
            pedido.setEndereco(endereco);
            for (String produtoId : produtoIDs) {
                Produto p1 = produtoRepository.encontrar(Produto.class, new Long(produtoId));
                ProdutoPedido produtoPedido = new ProdutoPedido(p1, pedido, Integer.parseInt(request.getParameter("qtd_" + produtoId)), request.getParameter("obs_" + produtoId));

                p1.getProdutoPedidos().add(produtoPedido);

                pedidos.beginTransatcion();
                pedidos.adicionar(pedido);
                pedidos.commitTransaction();

            }

            response.sendRedirect("/pizzaria/consulta-pedidos");

        } catch (ParseException ex) {
            Logger.getLogger(CadastraPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(CadastraPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            manager.close();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
