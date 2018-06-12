/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leonam
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa{
   
   @OneToMany(mappedBy = "cliente")
   private List<Endereco> enderecos = new ArrayList();
   
   @OneToMany(mappedBy = "cliente")
   private List<Pedido> pedidos = new ArrayList();
   private int telefone;
    
    

    public Cliente() {
    }

    public Cliente(int telefone, String nome) {
        super(nome);
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    
    
    
}
