/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.beans;

/**
 *
 * @author Marcos
 */
public class FiltroAnuncio {
    private boolean movel;
    private boolean imovel;
    private boolean material;
    private boolean pets;
    private Double maxValor;
    private Double minValor;
    /**
     * Ordenação:
     * 1 - Maior valor
     * 2 - Menor valor
     * 3 - Título anúncio (alfabetica)
     */
    private int ordenacao;

    public FiltroAnuncio() {}

    public boolean isMovel() {
        return movel;
    }

    public void setMovel(boolean movel) {
        this.movel = movel;
    }

    public boolean isImovel() {
        return imovel;
    }

    public void setImovel(boolean imovel) {
        this.imovel = imovel;
    }

    public boolean isMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public Double getMaxValor() {
        return maxValor;
    }

    public void setMaxValor(Double maxValor) {
        this.maxValor = maxValor;
    }

    public Double getMinValor() {
        return minValor;
    }

    public void setMinValor(Double minValor) {
        this.minValor = minValor;
    }

    public int getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(int ordenacao) {
        this.ordenacao = ordenacao;
    }
            
}
