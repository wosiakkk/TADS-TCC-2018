/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.beans;

import br.com.tads.tccpool.interfaces.Anunciavel;
import java.io.Serializable;


/**
 *
 * @author onurb
 */
public class Anuncio implements Serializable {
    private Anunciavel obj;
    private int idAnuncio;
    private int idUsuario;
    private int idCategoria;
    private int IdImovel;
    private int IdMovel;
    private int IdMaterial;
    private String titulo;
    private float valor;
    private String descricao;
    private String categoria;
    private String caminhoFoto;
    private String statusAnuncio;
    

    public Anuncio() {}

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Anunciavel getObj() {
        return obj;
    }

    public void setObj(Anunciavel obj) {
        this.obj = obj;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdImovel() {
        return IdImovel;
    }

    public void setIdImovel(int IdImovel) {
        this.IdImovel = IdImovel;
    }

    public int getIdMovel() {
        return IdMovel;
    }

    public void setIdMovel(int IdMovel) {
        this.IdMovel = IdMovel;
    }

    public int getIdMaterial() {
        return IdMaterial;
    }

    public void setIdMaterial(int IdMaterial) {
        this.IdMaterial = IdMaterial;
    }

    public String getStatusAnuncio() {
        return statusAnuncio;
    }

    public void setStatusAnuncio(String statusAnuncio) {
        this.statusAnuncio = statusAnuncio;
    }
    
    
}
