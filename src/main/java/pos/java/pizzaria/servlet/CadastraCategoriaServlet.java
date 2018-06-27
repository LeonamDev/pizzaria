/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.servlet;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.form.CategoriaForm;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/cadastra-categoria")
public class CadastraCategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("action", "cadastra-categoria");
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/categoria/cadastra-categoria.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);

        try {

            CategoriaForm form = CategoriaForm.fromRequest(request);
            Categoria categoria = form.toCategoria();

            categoriaRepository.beginTransatcion();
            categoriaRepository.adicionar(categoria);
            categoriaRepository.commitTransaction();

            response.sendRedirect("/pizzaria/consulta-categorias");

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
