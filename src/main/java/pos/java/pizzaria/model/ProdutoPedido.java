/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author leonam
 */
@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Id
    @ManyToOne
    @JoinColumn( name = "pedido_id")
    private Pedido pedido;
    private int quantidade;
    private String obs;
    
    public ProdutoPedido(){
        
    }

    public ProdutoPedido(Produto produto, Pedido pedido, int quantidade, String obs) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.obs = obs;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    
}
