/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.repository.CategoriaRepository;

/**
 *
 * @author leonam
 */
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void cadastrar(Categoria categoria) {
        this.categoriaRepository.adicionar(categoria);
    }

}
