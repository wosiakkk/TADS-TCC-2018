/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.beans;
 
import br.com.tads.tccpool.interfaces.Anunciavel;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Movel implements Serializable {
    private String titulo;
    private String descricao;
    private Float preco;
    private int tipo;
    private int id;
    private List<String> fotos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
    
    
    

    public Movel() {}
    
    public void setDescricao(String s) {
        this.descricao = s;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPreco() {
        return preco;
    }

    public String getTitulo() {
        return titulo;
    }

 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
