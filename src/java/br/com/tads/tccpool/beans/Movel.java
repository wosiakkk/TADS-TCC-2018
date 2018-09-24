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
    private String tipoDesc;
    private float preco;
    private int tipo;
    private int status;
    private int id;
    private List<String> fotos;
    private int idAnunciante;
    private String nomeAnunciante;
    private String rua;
    private int numero;
    private int enderecoId;
    private String estado;
    private String cidade;
    private String cep;
    private String complemento = "";

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

    public int getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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
    

    
    public String getTipoDesc() {
        return tipoDesc;
    }

    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    

    
    public String getNomeAnunciante() {
        return nomeAnunciante;
    }

    public void setNomeAnunciante(String nomeAnunciante) {
        this.nomeAnunciante = nomeAnunciante;
    }
    
    

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }
    
    
    

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
