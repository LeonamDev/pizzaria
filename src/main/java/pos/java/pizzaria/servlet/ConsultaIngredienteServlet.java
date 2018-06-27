/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/consulta-ingredientes")
public class ConsultaIngredienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager manager = JpaUtil.getEntityManager();

        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {

            List<Ingrediente> todosIngredientes = ingredienteRepository.listar();

            request.setAttribute("ingredientes", todosIngredientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/ingrediente/consulta-ingredientes.jsp");
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

        IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);

        try {
            Long ingredienteId = new Long(request.getParameter("ingredienteId"));

            Ingrediente ingrediente = ingredienteRepository.encontrar(Ingrediente.class, ingredienteId);

            ingredienteRepository.beginTransatcion();
            ingredienteRepository.remover(ingrediente);
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
