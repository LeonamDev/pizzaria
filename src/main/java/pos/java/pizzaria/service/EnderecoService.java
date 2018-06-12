/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import pos.java.pizzaria.model.Endereco;
import pos.java.pizzaria.repository.EnderecoRepository;

/**
 *
 * @author leonam
 */
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void cadastrar(Endereco endereco) {
        this.enderecoRepository.adicionar(endereco);
    }

}
