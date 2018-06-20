/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import java.util.List;
import pos.java.pizzaria.model.Pedido;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.model.ProdutoPedido;
import pos.java.pizzaria.repository.PedidoRepository;

/**
 *
 * @author leonam
 */
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void cadastrar(Pedido pedido) {
        this.pedidoRepository.adicionar(pedido);
    }

    public List<Produto> removeProdutoPedidoFromProdutos(Pedido pedido, List<Produto> todosProdutos) {
        for (ProdutoPedido produtoP : pedido.getProdutoPedidos()) {
            if (todosProdutos.contains(produtoP.getProduto())) {
                todosProdutos.remove(produtoP.getProduto());
            }
        }
        return todosProdutos;
    }

}
