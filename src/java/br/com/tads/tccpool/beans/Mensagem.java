package br.com.tads.tccpool.beans;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Bean para comentários privados e públicos
 * @author Marcos
 */
public class Mensagem implements Serializable {
    private int idConversa;
    private int idMensagem;
    private int idOrigem;
    private int idDestino;
    private String conteudo;
    private String nmOrigem;
    private String nmDestino;
    private String fotoDestino;
    private String fotoOrigem;
    private Calendar data;
    
    public Mensagem(){
        this.data = Calendar.getInstance();
    }

    public int getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(int idConversa) {
        this.idConversa = idConversa;
    }
    
    public String getNmOrigem() {
        return nmOrigem;
    }

    public void setNmOrigem(String nmOrigem) {
        this.nmOrigem = nmOrigem;
    }

    public String getNmDestino() {
        return nmDestino;
    }

    public void setNmDestino(String nmDestino) {
        this.nmDestino = nmDestino;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(int idOrigem) {
        this.idOrigem = idOrigem;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
    
    public String getFotoDestino() {
        return fotoDestino;
    }

    public void setFotoDestino(String fotoDestino) {
        this.fotoDestino = fotoDestino;
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getFotoOrigem() {
        return fotoOrigem;
    }

    public void setFotoOrigem(String fotoOrigem) {
        this.fotoOrigem = fotoOrigem;
    }
        
    @Override
    public String toString(){
        return "idMensagem:" + String.valueOf(this.idMensagem) +
               ";idOrigem:"  + String.valueOf(this.idOrigem)   +
               ";idDestino:" + String.valueOf(this.idDestino)  +
               ";data:"      + this.data.getTime().toString();
    }
    
}
