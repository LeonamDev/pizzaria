/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.form;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import pos.java.pizzaria.model.Categoria;
import pos.java.pizzaria.model.Ingrediente;
import pos.java.pizzaria.model.Produto;
import pos.java.pizzaria.repository.CategoriaRepository;
import pos.java.pizzaria.repository.IngredienteRepository;
import pos.java.pizzaria.service.ServiceException;

/**
 *
 * @author leonam
 */
public class ProdutoForm {

    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(
                new Locale("pt", "BR"));
        DECIMAL_FORMAT.applyPattern("#0.00");
    }
    
    private String id;
    private String codigo;
    private String nome;
    private String preco;
    private String categoriaId;
    private String[] ingredientesIDs;

    List<Ingrediente> ingredientes = new ArrayList<>();

    public static ProdutoForm fromRequest(
            HttpServletRequest request) {
        ProdutoForm form = new ProdutoForm();

        form.setCodigo(request.getParameter("codigo"));
        form.setNome(request.getParameter("nome"));
        form.setPreco(request.getParameter("preco"));
        form.setCategoriaId(request.getParameter("categoriaId"));
        form.setIngredientesIDs(request.getParameterValues("ingredienteId"));
        form.setId(request.getParameter("produtoId"));

        return form;
    }

    public Produto toProduto(EntityManager manager) throws ServiceException {
        Produto produto = new Produto();
        
         if (this.getId() != null
                && !this.getId().equals("")) {
            produto.setId(new Long((this.getId())));
        }


        try {
            if (this.getCodigo() != null
                    && !this.getCodigo().equals("")) {
                produto.setCodigo(new Integer(this.getCodigo()));
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o Código!");
        }

        try {
            if (this.getNome() != null
                    && !this.getNome().equals("")) {
                produto.setNome(this.getNome());
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o nome do produto!");
        }

        try {
            if (this.getPreco() != null
                    && !this.getPreco().equals("")) {
                produto.setPreco(DECIMAL_FORMAT.parse(
                        this.getPreco()).doubleValue());
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o preço corretamente!");
        }

        try {
            if (this.getCategoriaId() != null
                    && !this.getCategoriaId().equals("")) {
                CategoriaRepository categoriaRepository = new CategoriaRepository(manager);
                Categoria categoria = categoriaRepository.encontrar(Categoria.class, new Long(this.categoriaId));
                produto.setCategoria(categoria);
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe a categoria!");
        }

        try {
            if (this.getIngredientesIDs() != null
                    && !(this.getIngredientesIDs().length == 0)) {
                IngredienteRepository ingredienteRepository = new IngredienteRepository(manager);
                for (String id : this.ingredientesIDs) {
                    ingredientes.add(ingredienteRepository.encontrar(Ingrediente.class, new Long(id)));
                }
                produto.getIngredientes().addAll(ingredientes);
            }
        } catch (Exception e) {
            throw new ServiceException(
                    "Informe o(s) ingrediente(s)!");
        }

        return produto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String[] getIngredientesIDs() {
        return ingredientesIDs;
    }

    public void setIngredientesIDs(String[] ingredientesIDs) {
        this.ingredientesIDs = ingredientesIDs;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
