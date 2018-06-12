/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.repository.IngredienteRepository;

/**
 *
 * @author leonam
 */
public class IngredienteService {

    private IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public void cadastrar(Ingrediente ingrediente) {
        this.ingredienteRepository.adicionar(ingrediente);
    }

}
