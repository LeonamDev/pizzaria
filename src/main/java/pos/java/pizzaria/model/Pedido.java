/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.model;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leonam
 */
@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProdutoPedido> produtos;
    
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private boolean entrega;
    private double desconto;
    private double taxa_entrega;
    private double valor;
    private double troco;
    private Date data;
    private Date hora;
    private int status;
    
    public Pedido(){
        
    }

    public Pedido(long id, Cliente cliente, Endereco endereco, boolean entrega, double desconto, double taxa_entrega, double valor, double troco, Date data, Date hora, int status) {
        this.id = id;
        this.cliente = cliente;
        this.endereco = endereco;
        this.entrega = entrega;
        this.desconto = desconto;
        this.taxa_entrega = taxa_entrega;
        this.valor = valor;
        this.troco = troco;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(double taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
    
    
    
    
            
    
    
}
