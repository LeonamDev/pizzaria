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
import pos.java.pizzaria.form.EnderecoForm;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.EnderecoRepository;
import pos.java.pizzaria.service.ServiceException;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/cadastra-endereco")
public class CadastraEnderecoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("action", "cadastra-endereco");
        request.setAttribute("clienteId", request.getParameter("clienteId"));

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/endereco/cadastra-endereco.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String enderecoId = request.getParameter("enderecoId");

        EntityManager manager = JpaUtil.getEntityManager();

        EnderecoRepository enderecoRepository = new EnderecoRepository(manager);
        

        try {
            enderecoRepository.beginTransatcion();
            if (enderecoId != null && !enderecoId.equals("")) {
                Endereco endereco = enderecoRepository.encontrar(Endereco.class, new Long(enderecoId));
                enderecoRepository.remover(endereco);
            } else {
                EnderecoForm form = EnderecoForm.fromRequest(request);
                Endereco endereco = form.toEndereco();
                enderecoRepository.adicionar(endereco);
            }
            enderecoRepository.commitTransaction();

           response.sendRedirect("/pizzaria/consulta-clientes");

        } catch (ParseException ex) {
            Logger.getLogger(CadastraEnderecoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(CadastraEnderecoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            manager.close();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
