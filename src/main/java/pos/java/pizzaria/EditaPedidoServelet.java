/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/edita-pedido")
public class EditaPedidoServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        PedidoRepository pedidos = new PedidoRepository(manager);
        ProdutoRepository produtoRepository = new ProdutoRepository(manager);
        ClienteRepository clienteRepository = new ClienteRepository(manager);

        try {
            Pedido pedido = pedidos.encontrar(Pedido.class, new Long(request.getParameter("pedido_id")));

            List<Produto> todosProdutos = produtoRepository.listar();
            List<Cliente> todosClientes = clienteRepository.listar();

            request.setAttribute("form", pedido);
            request.setAttribute("produtos", todosProdutos);
            request.setAttribute("clientes", todosClientes);
            request.setAttribute("action", "edita-pedido");

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
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
