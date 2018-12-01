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
import br.com.tads.tccpool.beans.FiltroAnuncio;
import br.com.tads.tccpool.beans.Foto;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.dao.AnuncioDAO;
import br.com.tads.tccpool.exception.AcessoBdException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class AnuncioFacade {

    public static Imovel insereImovel(Imovel i, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirImovel(i, categoria, caminho, u);
        dao.close();
        return i;
    }

    public static Movel insereMovel(Movel m, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirMovel(m, categoria, caminho, u);
        dao.close();
        return m;
    }

    public static Material insereMaterial(Material m, int categoria, List<String> caminho, User u) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirMaterial(m, categoria, caminho, u);
        dao.close();
        return m;
    }

    public static int getId() throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        int id = dao.getIdFoto();
        return id;
    }

    public static List<Imovel> buscarPendente() throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarPendente();
    }

    public static List<Movel> buscarPendenteMovel() throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarPendenteMovel();
    }
    public static List<Material> buscarPendenteMaterial() throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarPendenteMaterial();
    }
    
    public static Imovel buscarImovelPorId(int id) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarImovelPorId(id);
    }

    public static Movel buscarMovelPorId(int id) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarMovelPorId(id);
    }

    public static Material buscarMaterialPorId(int id) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarMaterialPorId(id);
    }

    public static void alterarStatus(String status, int id) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.aprovarAnuncio(status, id);
    }

    public static String buscarAnuncioAprovado(FiltroAnuncio filtro) {
        AnuncioDAO dao = new AnuncioDAO();
        NumberFormat nFormat = NumberFormat.getCurrencyInstance();
        String HTMLResponse = "";
        List<Anuncio> anunciosAprovados = new ArrayList<>();
        try {
            if (filtro.isFiltroAtivo()) {
                anunciosAprovados = dao.filtrarAnuncio(filtro);
            } else {
                anunciosAprovados = dao.buscarAnuncioAprovado();
            }
            if (anunciosAprovados != null) {
                
                if(anunciosAprovados.size() > 0) {
                    for (Anuncio anuncio : anunciosAprovados) {
                        HTMLResponse += "<div class=\"col-lg-4 col-md-6 mb-4\">\n"
                                + "    <div class=\"card h-100\">\n"
                                + "        <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\"><img class=\"card-img-top\" src=\"" + anuncio.getCaminhoFoto() + "\" alt=\"\"></a>\n"
                                + "        <div class=\"card-body\">\n"
                                + "            <h4 class=\"card-title\">\n"
                                + "                <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\">" + anuncio.getTitulo() + "</a>\n"
                                + "            </h4>\n"
                                + "            <h5>" + nFormat.format(anuncio.getValor()) + "</h5>\n"
                                + "            <p class=\"card-text\">" + anuncio.getDescricao() + "</p>\n"
                                + "        </div>\n"
                                + "        <div class=\"card-footer\">\n"
                                + "            <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + anuncio.getIdAnuncio() + "\">veja mais</a>\n"
                                + "        </div>\n"
                                + "    </div>\n"
                                + "</div>";
                    }
                }
                else {
                    HTMLResponse = "Não foram encontrados anúncios para esse filtro.";
                }
            }
            else {
                HTMLResponse = "Não foram encontrados anúncios para esse filtro.";
            }
        } catch (SQLException ex) {
            HTMLResponse = "Falha ao filtrar os anúncios. Pro favor, tente novamente mais tarde!";
            Logger.getLogger(AnuncioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return HTMLResponse;
    }

    public static List<Anuncio> buscarAnuncioDoUsuario(int id,int status) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        List<Anuncio> anunciosDoUsuario = null;
        anunciosDoUsuario = dao.buscarAnunciosDoUsuario(id, status);
        return anunciosDoUsuario;
    }

    public static int verifcaTipoAnuncio(int idAnuncio) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.verificaTipoAnuncio(idAnuncio);
    }

    public static Imovel exibirImovel(int idAnuncio) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.exibirImovel(idAnuncio);
    }

    public static int retornoIdImovel(int id) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        return dao.retornoIdImovelPorIdAnuncio(id);
    }

    public static void deletarAnuncioImovel(int id, Imovel i) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.deletarAnuncioImovel(id, i);
    }

    public static void deletarAnuncioMovel(int id, Movel m) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.deletarAnuncioMovel(id, m);
    }

    public static void deletarAnuncioMaterial(int id, Material m) throws SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.deletarAnuncioMaterial(id, m);
    }

    public static void updateImovel(Imovel i, int idAnuncio) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateImovel(i, idAnuncio);
        dao.close();
    }

    public static void updateMovel(Movel m, int idAnuncio) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateMovel(m, idAnuncio);
        dao.close();
    }

    public static void updateMaterial(Material m, int idAnuncio) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.updateMaterial(m, idAnuncio);
        dao.close();
    }

    public static void updateStatusAnuncio(int idAnuncio, int status) throws AcessoBdException, SQLException {
        AnuncioDAO dao = new AnuncioDAO();
        dao.alterarStatusAnuncio(idAnuncio, status);
        dao.close();

    }
    
    public static Anuncio buscaAlteraFotosAnuncio(int idAnuncio) throws InstantiationException, ClassNotFoundException, Exception{
        AnuncioDAO adao = new AnuncioDAO();
        return adao.buscaAlteraFotosAnuncio(idAnuncio);
    }
    
    public static void alteraFotosAnuncio(List<Foto> lista) throws InstantiationException, ClassNotFoundException, Exception{
        AnuncioDAO adao = new AnuncioDAO();
        adao.alteraFotosAnuncio(lista);
        adao.close();
    }
    
    public static void insereFotosAnuncio(List<Foto> lista) throws InstantiationException, ClassNotFoundException, Exception{
        AnuncioDAO adao = new AnuncioDAO();
        adao.insereFotosAnuncio(lista);
    }
    
    public static void excluiFotosAnuncio(int[] excluir) throws InstantiationException, ClassNotFoundException, Exception{
        AnuncioDAO adao = new AnuncioDAO();
        adao.excluiFotosAnuncio(excluir);
    }
    
    public static void inserirSeguidorAnuncio(int idAnuncio, int idSeguidor) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.inserirSeguidor(idAnuncio, idSeguidor);
    }
    
    public static void removerSeguidorAnuncio(int idAnuncio, int idSeguidor) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        dao.removerSeguidor(idAnuncio, idSeguidor);
    }
    
    public static Boolean verifSeguidor(int idAnuncio, int idSeguidor) throws  SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.verificarSeguidor(idAnuncio, idSeguidor);
    }
    
    public static List<Integer> buscarSeguidoresAnuncio(int idAnuncio) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarSeguidores(idAnuncio);
    }
    
    public static List<Integer> buscarIdsAnunciosSeguidos(int idUser) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.buscarIdAnunciosSeguidos(idUser);
    }
    
    public static Anuncio resumoAnuncios(int idAnuncio) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.resumoAnunciosSeguidos(idAnuncio);
    }
    
    public static int retornoIdAunciante(int idAnuncio) throws SQLException{
        AnuncioDAO dao = new AnuncioDAO();
        return dao.retornarIdDoAnunciante(idAnuncio);
    }
}
