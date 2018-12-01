/**
 *
 * @author Marcos
 */

package br.com.tads.tccpool.beans;

import java.io.Serializable;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;


public class FiltroAnuncio implements Serializable {
    //Flag que sinaliza se o filtro deve ser aplicado na consulta
    private boolean filtroAtivo = false;
    private boolean movel = false;
    private boolean imovel = false;
    private boolean material = false;
    private boolean pets = false;
    private Double maxValor = 1000000.00;
    private Double minValor = 0.0;
    /**
     * Ordenação:
     * 1 - Menor valor
     * 2 - Maior valor
     * 3 - Título anúncio (alfabetica)
     */
    private Integer ordenacao = 0;

    public FiltroAnuncio() {}

    public FiltroAnuncio(HttpServletRequest request) {
        this.filtroAtivo = true;
        Enumeration<String> names = request.getParameterNames();
        
        while(names.hasMoreElements()) {
            String paramName = names.nextElement();
            if("movel".equals(paramName))
                this.movel = ((String)request.getParameter("movel")).equals("1");
            
            if("imovel".equals(paramName))
                this.imovel = ((String)request.getParameter("imovel")).equals("1");
            
            if("material".equals(paramName))
                this.material = ((String)request.getParameter("material")).equals("1");
            
            if("pets".equals(paramName))
                this.pets = ((String)request.getParameter("pets")).equals("1");
            
            if("maxValor".equals(paramName))
                this.maxValor = (request.getParameter("maxValor").length() == 0 || Integer.parseInt(request.getParameter("maxValor")) == 0) ? this.maxValor : Double.parseDouble(request.getParameter("maxValor"));
            
            if("minValor".equals(paramName))
                this.minValor = request.getParameter("minValor").length() == 0 ? this.minValor : Double.parseDouble(request.getParameter("minValor"));
            
            if("ordenacao".equals(paramName))
                this.ordenacao = "0".equals(request.getParameter("ordenacao")) ? this.ordenacao : Integer.parseInt(request.getParameter("ordenacao"));
        }
    }

    public boolean isFiltroAtivo() {
        return filtroAtivo;
    }

    public void setFiltroAtivo(boolean filtroAtivo) {
        this.filtroAtivo = filtroAtivo;
    }
    
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

    public Integer getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Integer ordenacao) {
        this.ordenacao = ordenacao;
    }
            
}
