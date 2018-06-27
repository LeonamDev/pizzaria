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
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/edita-categoria")
public class EditaCategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long categoriaId = new Long(request.getParameter("categoriaId"));

        EntityManager manager = JpaUtil.getEntityManager();

        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);

        try {
            Categoria categoria = categoriaRepository.encontrar(Categoria.class, categoriaId);

            request.setAttribute("action", "edita-categoria");
            request.setAttribute("form", categoria);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/categoria/cadastra-categoria.jsp");
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

        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);

        try {
            CategoriaForm form = CategoriaForm.fromRequest(request);
            Categoria categoria = form.toCategoria();

            categoriaRepository.beginTransatcion();
            categoriaRepository.editar(categoria);
            categoriaRepository.commitTransaction();

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            manager.close();
        }

        response.sendRedirect("/pizzaria/consulta-categorias");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
