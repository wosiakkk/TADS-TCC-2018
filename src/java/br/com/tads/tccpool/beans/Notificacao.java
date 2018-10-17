/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.beans;

import java.util.Calendar;

/**
 *
 * @author onurb
 */
public class Notificacao {
    private int id;
    private int idGerador;
    private int idUser;   
    private int statusNot;
    private int tipoNot;
    private Calendar data;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGerador() {
        return idGerador;
    }

    public void setIdGerador(int idGerador) {
        this.idGerador = idGerador;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getStatusNot() {
        return statusNot;
    }

    public void setStatusNot(int statusNot) {
        this.statusNot = statusNot;
    }

    public int getTipoNot() {
        return tipoNot;
    }

    public void setTipoNot(int tipoNot) {
        this.tipoNot = tipoNot;
    }
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

}
