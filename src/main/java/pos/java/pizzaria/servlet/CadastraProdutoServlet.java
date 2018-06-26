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
import pos.java.pizzaria.form.ProdutoForm;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/cadastra-produto")
public class CadastraProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);
        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {
            List<Categoria> todasCategorias = categoriaRepository.listar();
            List<Ingrediente> todosIngredientes = ingredienteRepository.listar();

            request.setAttribute("action", "cadastra-produto");
            request.setAttribute("categorias", todasCategorias);
            request.setAttribute("ingredientes", todosIngredientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/produto/cadastra-produto.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            manager.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();
        ProdutoRepository produtoRepository = new ProdutoRepository(manager);

        try {

            ProdutoForm form = ProdutoForm.fromRequest(request);
            Produto produto = form.toProduto(manager);

            produtoRepository.beginTransatcion();
            produtoRepository.adicionar(produto);
            produtoRepository.commitTransaction();

            response.sendRedirect("/pizzaria/consulta-produtos");

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            manager.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
