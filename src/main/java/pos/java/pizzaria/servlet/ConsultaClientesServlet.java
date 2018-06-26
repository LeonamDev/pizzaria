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
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.service.ClienteService;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/consulta-clientes")
public class ConsultaClientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();
        ClienteService clienteService = new ClienteService(new ClienteRepository(manager));
        try {
            List<Cliente> todosClientes = clienteService.listar();

            request.setAttribute("clientes", todosClientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/cliente/consulta-clientes.jsp");
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
        ClienteService clienteService = new ClienteService(new ClienteRepository(manager));

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        try {
            Cliente cliente = clienteRepository.encontrar(Cliente.class, new Long(request.getParameter("clienteId")));

            clienteRepository.beginTransatcion();
            clienteRepository.remover(cliente);
            clienteRepository.commitTransaction();

            List<Cliente> todosClientes = clienteService.listar();
            request.setAttribute("clientes", todosClientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/cliente/consulta-clientes.jsp");
            dispatcher.forward(request, response);
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
