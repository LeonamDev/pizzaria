/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.service.ServiceException;

/**
 *
 * @author leonam
 */
public class IngredienteForm {

    private String id;
    private String nome;

    public static IngredienteForm fromRequest(
            HttpServletRequest request) {
        IngredienteForm form = new IngredienteForm();
        form.setId(request.getParameter("ingredienteId"));
        form.setNome(request.getParameter("nome"));

        return form;
    }

    public Ingrediente toIngrediente() throws ParseException, ServiceException {
        Ingrediente ingrediente = new Ingrediente();

        if (this.getId() != null
                && !this.getId().equals("")) {
            ingrediente.setId(new Long((this.getId())));
        }

        try {
            if (this.getNome() != null
                    && !this.getNome().equals("")) {
                ingrediente.setNome((this.getNome()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o nome da categoria!");
        }

        return ingrediente;
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
