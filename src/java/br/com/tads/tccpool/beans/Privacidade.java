/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.beans;

import java.io.Serializable;

/**
 *
 * @author diego.goncalves
 */
public class Privacidade implements Serializable {
    int id;
    int idUser;
    int privacidadeTelefone;
    int privacidadeEndereco;
    int privacidadeDescricao;
    int privacidadeInteresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPrivacidadeTelefone() {
        return privacidadeTelefone;
    }

    public void setPrivacidadeTelefone(int privacidadeTelefone) {
        this.privacidadeTelefone = privacidadeTelefone;
    }

    public int getPrivacidadeEndereco() {
        return privacidadeEndereco;
    }

    public void setPrivacidadeEndereco(int privacidadeEndereco) {
        this.privacidadeEndereco = privacidadeEndereco;
    }

    public int getPrivacidadeDescricao() {
        return privacidadeDescricao;
    }

    public void setPrivacidadeDescricao(int privacidadeDescricao) {
        this.privacidadeDescricao = privacidadeDescricao;
    }

    public int getPrivacidadeInteresses() {
        return privacidadeInteresses;
    }

    public void setPrivacidadeInteresses(int privacidadeInteresses) {
        this.privacidadeInteresses = privacidadeInteresses;
    }
    
    
}
