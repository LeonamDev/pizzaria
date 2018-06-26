/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.servlet;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/consulta-produtos")
public class ConsultaProdutosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();
        ProdutoRepository produtoRepository = new ProdutoRepository(manager);

        List<Produto> todosProdutos = produtoRepository.listar();

        request.setAttribute("produtos", todosProdutos);

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/produto/consulta-produtos.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();
        ProdutoRepository produtoRepository = new ProdutoRepository(manager);
        Long produtoId = new Long(request.getParameter("produto_id"));

        Produto produto = produtoRepository.encontrar(Produto.class, produtoId);

        try {
            produtoRepository.beginTransatcion();
            produtoRepository.remover(produto);
            produtoRepository.commitTransaction();

        } catch (Exception e) {

        } finally {
            manager.close();

        }

        response.sendRedirect("/pizzaria/consulta-produtos");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
