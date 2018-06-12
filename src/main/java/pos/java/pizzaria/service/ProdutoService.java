/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.ProdutoRepository;

/**
 *
 * @author leonam
 */
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrar(Produto produto) {
        this.produtoRepository.adicionar(produto);
    }

}
