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
import pos.java.pizzaria.form.IngredienteForm;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/edita-ingrediente")
public class EditaIngredienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long ingredienteId = new Long(request.getParameter("ingredienteId"));

        EntityManager manager = JpaUtil.getEntityManager();
        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {
            Ingrediente ingrediente = ingredienteRepository.encontrar(Ingrediente.class, ingredienteId);

            request.setAttribute("action", "edita-ingrediente");
            request.setAttribute("form", ingrediente);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/ingrediente/cadastra-ingrediente.jsp");
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

        try {

            IngredienteForm form = IngredienteForm.fromRequest(request);
            Ingrediente ingrediente = form.toIngrediente();

            IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

            ingredienteRepository.beginTransatcion();
            ingredienteRepository.editar(ingrediente);
            ingredienteRepository.commitTransaction();

            response.sendRedirect("/pizzaria/consulta-ingredientes");
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
