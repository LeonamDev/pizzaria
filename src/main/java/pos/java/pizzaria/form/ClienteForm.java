/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.service.ServiceException;

/**
 *
 * @author leonam
 */
public class ClienteForm {

    private String nome;
    private String telefone;

    public static ClienteForm fromRequest(
            HttpServletRequest request) {
        ClienteForm form = new ClienteForm();
        form.setNome(request.getParameter("nome"));
        form.setTelefone(request.getParameter("telefone"));

        return form;
    }

    public Cliente toCliente() throws ParseException, ServiceException {
        Cliente cliente = new Cliente();

        try {
            if (this.getNome() != null
                    && !this.getNome().equals("")) {
                cliente.setNome(this.getNome());
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o nome.");
        }

        try {
            if (this.getTelefone() != null
                    && !this.getTelefone().equals("")) {
                cliente.setTelefone(new Integer(this.getTelefone()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o Telefone.");
        }

        return cliente;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
