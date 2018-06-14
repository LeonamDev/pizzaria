/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.PedidoRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/consulta-pedidos")
public class ConsultaPedidosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();

        PedidoRepository pedidos = new PedidoRepository(manager);
        ProdutoRepository produtos = new ProdutoRepository(manager);

        try {
            List<Pedido> todosPedidos = pedidos.listar();
            List<Produto> todosProdutos = produtos.listar();

            request.setAttribute("pedidos", todosPedidos);
            request.setAttribute("produtos", todosProdutos);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/consulta-pedidos.jsp");
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

        Pedido pedido = pedidos.encontrar(Pedido.class, request.getParameter("pedido_id"));

        pedidos.beginTransatcion();
        pedidos.remover(pedido);
        pedidos.commitTransaction();

        List<Pedido> todosPedidos = pedidos.listar();
        request.setAttribute("pedidos", todosPedidos);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/consulta-pedidos.jsp");
        dispatcher.forward(request, response);

        manager.close();

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
