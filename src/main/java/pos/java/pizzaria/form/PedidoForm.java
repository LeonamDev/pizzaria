/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Pedido;

/**
 *
 * @author leonam
 */
public class PedidoForm {

    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(
                new Locale("pt", "BR"));
        DECIMAL_FORMAT.applyPattern("#0.00");
    }

    private String entrega;
    private String desconto;
    private String taxa_entrega;
    private String valor;
    private String troco;
    private String data;
    private String hora;
    private String status;

    public static PedidoForm fromRequest(
            HttpServletRequest request) {
        PedidoForm form = new PedidoForm();
        form.setEntrega(request.getParameter("entrega"));
        form.setDesconto(request.getParameter("desconto"));
        form.setTaxa_entrega(request.getParameter("taxa_entrega"));
        form.setValor(request.getParameter("valor"));
        form.setTroco(request.getParameter("troco"));
        form.setStatus(request.getParameter("status"));
        return form;
    }

    public Pedido toPedido() throws ParseException {
        Pedido pedido = new Pedido();

        pedido.setData(new java.sql.Date(System.currentTimeMillis()));
        pedido.setHora( new java.sql.Timestamp(System.currentTimeMillis()));
        pedido.setStatus(this.status);

        if (this.getEntrega() != null
                && !this.getEntrega().equals("")) {
            pedido.setEntrega(
                    Boolean.valueOf(this.entrega));
        }

        if (this.getDesconto() != null
                && !this.getDesconto().equals("")) {
            pedido.setDesconto(
                    DECIMAL_FORMAT.parse(
                            this.getDesconto()).doubleValue());
        }

        if (this.getTaxa_entrega() != null
                && !this.getTaxa_entrega().equals("")) {
            pedido.setTaxa_entrega(
                    DECIMAL_FORMAT.parse(
                            this.getTaxa_entrega()).doubleValue());
        }

        if (this.getValor() != null
                && !this.getValor().equals("")) {
            pedido.setValor(
                    DECIMAL_FORMAT.parse(
                            this.getValor()).doubleValue());
        }

        if (this.getTroco() != null
                && !this.getTroco().equals("")) {
            pedido.setTroco(
                    DECIMAL_FORMAT.parse(
                            this.getTroco()).doubleValue());
        }

        return pedido;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(String taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
