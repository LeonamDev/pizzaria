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
import pos.java.pizzaria.form.EnderecoForm;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.repository.ClienteRepository;
import pos.java.pizzaria.repository.EnderecoRepository;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/edita-endereco")
public class EditaEnderecoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long enderecoId = new Long(request.getParameter("enderecoId"));

        EntityManager manager = JpaUtil.getEntityManager();
        EnderecoRepository enderecoRepository = new EnderecoRepository(manager);
        Endereco endereco = enderecoRepository.encontrar(Endereco.class, enderecoId);
        
        request.setAttribute("action", "edita-endereco");
        request.setAttribute("clienteId", endereco.getCliente().getId());
        request.setAttribute("form", endereco);
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/paginas/endereco/cadastra-endereco.jsp");
        dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         EntityManager manager = JpaUtil.getEntityManager();
        try {

            EnderecoForm form = EnderecoForm.fromRequest(request);
            Endereco endereco = form.toEndereco();

            EnderecoRepository enderecoRepository = new EnderecoRepository(manager);


            enderecoRepository.beginTransatcion();
            enderecoRepository.editar(endereco);
            enderecoRepository.commitTransaction();

           response.sendRedirect("/pizzaria/consulta-clientes");
        }catch(Exception e){
            
        }finally{
            manager.close();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
