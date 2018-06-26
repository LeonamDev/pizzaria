/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet("/edita-produto")
public class EditaProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long produtoId = new Long(request.getParameter("produto_id"));

        EntityManager manager = JpaUtil.getEntityManager();
        ProdutoRepository produtoRepository = new ProdutoRepository(manager);
        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);
        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {

            Produto produto = produtoRepository.encontrar(Produto.class, produtoId);
            List<Categoria> todasCategorias = categoriaRepository.listar();
            List<Ingrediente> todosIngredientes = ingredienteRepository.listar();

            request.setAttribute("action", "edita-produto");
            request.setAttribute("form", produto);
            request.setAttribute("categorias", todasCategorias);
            request.setAttribute("ingredientes", todosIngredientes);
            request.setAttribute("categoria_id", produto.getCategoria().getId());

            List<Long> ingredienteIDs = new ArrayList<>();
            for (Ingrediente ingrediente : produto.getIngredientes()) {
                ingredienteIDs.add(ingrediente.getId());
            }
            request.setAttribute("ingrediente_ids", ingredienteIDs);

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
            produtoRepository.editar(produto);
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
