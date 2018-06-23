/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pos.java.pizzaria.form.ClienteForm;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.service.ClienteService;
import pos.java.pizzaria.service.ServiceException;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/cadastra-cliente")
public class CadastraClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "cadastra-cliente");
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/cliente/cadastra-cliente.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        ClienteService clienteService = new ClienteService(clienteRepository);

        try {

            ClienteForm form = ClienteForm.fromRequest(request);
            Cliente cliente = form.toCliente();

            clienteRepository.beginTransatcion();
            clienteRepository.adicionar(cliente);
            clienteRepository.commitTransaction();

         
            request.setAttribute("action", "cadastra-endereco");
            request.setAttribute("clienteId", cliente.getId());
            

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/endereco/cadastra-endereco.jsp");
            dispatcher.forward(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(CadastraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(CadastraClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            manager.close();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
