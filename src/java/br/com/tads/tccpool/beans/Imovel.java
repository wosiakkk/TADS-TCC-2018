/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.beans;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author onurb
 */
public class Imovel implements Serializable {
    
    private int idAnunciante;
    private String nomeAnunciante;
    private int id;
    private int tipo;
    private String tipoDesc;
    private int quantidade_pessoas;
    private int boolean_pet;  
    private String descricao;
    private String titulo;
    private float preco;
    private String rua;
    private int numero;
    private int enderecoId;
    private String estado;
    private String cidade;
    private String cep;
    private String complemento = "";
    private List<String> fotos;

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public String getNomeAnunciante() {
        return nomeAnunciante;
    }

    public void setNomeAnunciante(String nomeAnunciante) {
        this.nomeAnunciante = nomeAnunciante;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    
    public String getTipoDesc() {
        return tipoDesc;
    }

    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }
    
     public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade_pessoas() {
        return quantidade_pessoas;
    }

    public void setQuantidade_pessoas(int quantidade_pessoas) {
        this.quantidade_pessoas = quantidade_pessoas;
    }

    public int getBoolean_pet() {
        return boolean_pet;
    }

    public void setBoolean_pet(int boolean_pet) {
        this.boolean_pet = boolean_pet;
    }
    
    /**
     *
     * @return
     */
    
    public List<String> getFotos() {
        return fotos;
    }
    
    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
     
    public String getRua() {
        return rua;
    }
     
    public void setRua(String rua) {
        this.rua = rua;
    }
     
    public int getNumero() {
        return numero;
    }
     
    public void setNumero(int numero) {
        this.numero = numero;
    }
     
    public String getEstado() {
        return estado;
    }
     
    public void setEstado(String estado) {
        this.estado = estado;
    }
     
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

     
    public String getTitulo() {
        return titulo;
    }

     
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

     
    public float getPreco() {
        return preco;
    }

     
    public void setPreco(float preco) {
        this.preco = preco;
    }

     
    public void setDescricao(String desc) {
        this.descricao = desc;
    }

     
    public String getDescricao() {
        return this.descricao;
    }

}
