/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.Imovel;
import br.com.tads.tccpool.beans.Material;
import br.com.tads.tccpool.beans.Movel;
import br.com.tads.tccpool.beans.Anuncio;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.dao.AnuncioDAO;
import br.com.tads.tccpool.exception.AcessoBdException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class AnuncioFacade {
    public static Imovel insereImovel(Imovel i, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirImovel(i, categoria, caminho, u);
        dao.close();
        return i;
    }
    
    public static Movel insereMovel(Movel m, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirMovel(m, categoria, caminho, u);
        dao.close();
        return m;
    }
    
    public static Material insereMaterial(Material m, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirMaterial(m, categoria, caminho, u);
        dao.close();
        return m;
    }
    
    public static int getId() throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        int id = dao.getIdFoto();
        return id;
    }
    
    public static List<Imovel> buscarPendente() throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarPendente();
    }
    public static Imovel buscarImovelPorId(int id) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarImovelPorId(id);
    }
    public static Movel buscarMovelPorId(int id) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarMovelPorId(id);
    }
    public static Material buscarMaterialPorId(int id) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarMaterialPorId(id);
    }
    public static void alterarStatus(String status, int id) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.aprovarAnuncio(status, id);
    }
    
    public static String buscarAnuncioAprovado() {
        AnuncioDAO dao = new AnuncioDAO();
        String HTMLResponse = "";
        List<Anuncio> anunciosAprovados = null;
        
        try {
            anunciosAprovados = dao.buscarAnuncioAprovado();
            if(anunciosAprovados != null) {
                for (Anuncio anuncio : anunciosAprovados) {
                    HTMLResponse += "<div class=\"col-lg-4 col-md-6 mb-4\">\n" +
                                    "    <div class=\"card h-100\">\n" +
                                    "        <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\"><img class=\"card-img-top\" src=\"" + anuncio.getCaminhoFoto() + "\" alt=\"\"></a>\n" +
                                    "        <div class=\"card-body\">\n" +
                                    "            <h4 class=\"card-title\">\n" +
                                    "                <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\">" + anuncio.getTitulo() + "</a>\n" +
                                    "            </h4>\n" +
                                    "            <h5>$" + String.valueOf(anuncio.getValor()) + "</h5>\n" +
                                    "            <p class=\"card-text\">" + anuncio.getDescricao() + "</p>\n" +
                                    "        </div>\n" +
                                    "        <div class=\"card-footer\">\n" +
                                    "            <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\">veja mais</a>\n" +
                                    "        </div>\n" +
                                    "    </div>\n" +
                                    "</div>";
                }
                
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(AnuncioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return HTMLResponse;
    }
    
    public static List<Anuncio> buscarAnuncioDoUsuario(int id) throws SQLException{
       AnuncioDAO dao = new AnuncioDAO();
       List<Anuncio> anunciosDoUsuario = null;
       anunciosDoUsuario = dao.buscarAnunciosDoUsuario(id);
       return anunciosDoUsuario;
   }
   
   public static int verifcaTipoAnuncio(int idAnuncio) throws SQLException{
       AnuncioDAO dao = new AnuncioDAO();
       return dao.verificaTipoAnuncio(idAnuncio);
   }
   
   public static Imovel exibirImovel(int idAnuncio) throws SQLException{
       AnuncioDAO dao = new AnuncioDAO();
       return dao.exibirImovel(idAnuncio);
   }
   public static int retornoIdImovel(int id) throws SQLException{
       AnuncioDAO dao = new AnuncioDAO();
       return dao.retornoIdImovelPorIdAnuncio(id);
   }
   
   public static void deletarAnuncioImovel(int id, Imovel i) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.deletarAnuncioImovel(id, i);
    }
   public static void deletarAnuncio(int id) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.deletarAnuncio(id);
    }
   public static void updateImovel(Imovel i, int idAnuncio) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateImovel(i, idAnuncio);
        dao.close();
        
    }
   public static void updateMovel(Movel m, int idAnuncio) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateMovel(m, idAnuncio);
        dao.close();
        
    }
   public static void updateMaterial(Material m, int idAnuncio) throws AcessoBdException, SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateMaterial(m, idAnuncio);
        dao.close();
        
    }
   
}
