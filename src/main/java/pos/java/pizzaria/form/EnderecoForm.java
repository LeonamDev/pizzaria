/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Cliente;
import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.service.ServiceException;

/**
 *
 * @author leonam
 */
public class EnderecoForm {

    private String id;
    private String rua;
    private String numero;
    private String referencia;
    private String clienteId;

    public static EnderecoForm fromRequest(
            HttpServletRequest request) {

        EnderecoForm form = new EnderecoForm();
        form.setRua(request.getParameter("rua"));
        form.setNumero(request.getParameter("numero"));
        form.setReferencia(request.getParameter("referencia"));
        form.setClienteId(request.getParameter("clienteId"));
        form.setId(request.getParameter("enderecoId"));

        return form;
    }

    public Endereco toEndereco() throws ParseException, ServiceException {
        Cliente cliente = new Cliente();
        cliente.setId(new Long(this.clienteId));

        Endereco endereco = new Endereco();
        endereco.setCliente(cliente);

        if (this.getId() != null
                && !this.getId().equals("")) {
            endereco.setId(new Long((this.getId())));
        }

        try {
            if (this.getRua() != null
                    && !this.getRua().equals("")) {
                endereco.setRua((this.getRua()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe a Rua.");
        }

        try {
            if (this.getNumero() != null
                    && !this.getNumero().equals("")) {
                endereco.setNumero((new Integer(this.getNumero())));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o número corretamente.");
        }

        try {
            if (this.getReferencia() != null
                    && !this.getReferencia().equals("")) {
                endereco.setReferencia((this.getReferencia()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe a Referência.");
        }

        return endereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
