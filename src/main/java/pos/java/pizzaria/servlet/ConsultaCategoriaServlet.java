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
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.ProdutoRepository;
import pos.java.pizzaria.service.ServiceException;
import pos.java.pizzaria.util.JpaUtil;

/**
 *
 * @author leonam
 */
@WebServlet("/consulta-categorias")
public class ConsultaCategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager manager = JpaUtil.getEntityManager();

        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);

        try {

            List<Categoria> todasCategorias = categoriaRepository.listar();

            request.setAttribute("categorias", todasCategorias);
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "/WEB-INF/paginas/categoria/consulta-categorias.jsp");
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
        
        ProdutoRepository produtoRepository = new ProdutoRepository(manager); 
        CategoriaRepository categoriaRepository = new CategoriaRepository(manager);
        

        try {
            Long categoriaId = new Long(request.getParameter("categoriaId"));
            
            
            List<Produto> todosProdutos = produtoRepository.listar();
            
           if(todosProdutos.stream()
                      .anyMatch(p -> p.getCategoria().getId() == categoriaId))
            throw new ServiceException("A categoria já está em uso e portanto não pode ser excluida.");        

            Categoria categoria = categoriaRepository.encontrar(Categoria.class, categoriaId);

            categoriaRepository.beginTransatcion();
            categoriaRepository.remover(categoria);
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
