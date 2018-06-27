/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.service.ServiceException;

/**
 *
 * @author leonam
 */
public class CategoriaForm {
    private String id; 
    private String nome;

    public static CategoriaForm fromRequest(
            HttpServletRequest request) {
        CategoriaForm form = new CategoriaForm();
        form.setId(request.getParameter("categoriaId"));
        form.setNome(request.getParameter("nome"));

        return form;
    }

    public Categoria toCategoria() throws ParseException, ServiceException {
        Categoria categoria = new Categoria();
        
          if (this.getId() != null
                && !this.getId().equals("")) {
            categoria.setId(new Long((this.getId())));
        }

        try {
            if (this.getNome() != null
                    && !this.getNome().equals("")) {
                categoria.setNome((this.getNome()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o nome da categoria!");
        }

        return categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
