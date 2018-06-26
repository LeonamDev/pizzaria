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
import pos.java.pizzaria.form.ClienteForm;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.EnderecoRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/edita-cliente")
public class EditaClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        try {
            Cliente cliente = clienteRepository.encontrar(Cliente.class, new Long(request.getParameter("clienteId")));

            request.setAttribute("form", cliente);
            request.setAttribute("action", "edita-cliente");

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/cliente/cadastra-cliente.jsp");
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
        Long clienteId = new Long(request.getParameter("clienteId"));

        try {

            ClienteForm form = ClienteForm.fromRequest(request);
            Cliente cliente = form.toCliente();
            cliente.setId(clienteId);

            ClienteRepository clienteRepository = new ClienteRepository(manager);

            Cliente clienteEnderecos = clienteRepository.encontrar(Cliente.class, clienteId);
            cliente.getEnderecos().addAll(clienteEnderecos.getEnderecos());

            clienteRepository.beginTransatcion();
            clienteRepository.editar(cliente);
            clienteRepository.commitTransaction();

            response.sendRedirect("/pizzaria/consulta-clientes");
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
