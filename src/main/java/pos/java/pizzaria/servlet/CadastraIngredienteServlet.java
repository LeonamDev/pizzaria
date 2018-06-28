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
@WebServlet("/cadastra-ingrediente")
public class CadastraIngredienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "cadastra-ingrediente");
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/ingrediente/cadastra-ingrediente.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();

        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {

            IngredienteForm form = IngredienteForm.fromRequest(request);
            Ingrediente ingrediente = form.toIngrediente();

            ingredienteRepository.beginTransatcion();
            ingredienteRepository.adicionar(ingrediente);
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
