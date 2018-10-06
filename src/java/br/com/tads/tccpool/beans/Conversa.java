/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class Conversa implements Serializable {
    private Integer idConversa;
    private Integer idOrigem;
    private Integer idDestino;
    private String nmDestino;
    private String fotoDestino;
    private Calendar dtInicio;
    
    public Conversa(){
        this.dtInicio = Calendar.getInstance();
    }

    public Integer getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(Integer idConversa) {
        this.idConversa = idConversa;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public String getNmDestino() {
        return nmDestino;
    }

    public void setNmDestino(String nmDestino) {
        this.nmDestino = nmDestino;
    }

    public String getFotoDestino() {
        return fotoDestino;
    }

    public void setFotoDestino(String fotoDestino) {
        this.fotoDestino = fotoDestino;
    }

    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    public Calendar getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Calendar dtInicio) {
        this.dtInicio = dtInicio;
    }
    
}
