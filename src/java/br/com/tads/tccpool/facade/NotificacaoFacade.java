/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.Notificacao;
import br.com.tads.tccpool.dao.NotificacaoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author onurb
 */
public class NotificacaoFacade {
    public static void inserirnotificacao(int gerador, int receptor, int tipoNoti) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        dao.inserirNoticacao(gerador, receptor, tipoNoti);
    }    
    public static List<Notificacao> buscar(int idUsr) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        return dao.buscarTodasNotificacoes(idUsr);
    }
    public static List<Notificacao> buscarNovas(int idUsr) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        return dao.buscarNovasNotificacoes(idUsr);
    }  
    public static void lerNotificacao(int idUsr) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        dao.lerNotificacoes(idUsr);
    }
    public static void excluir(int idNoti) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        dao.excluirNotificacao(idNoti);
    }
    public static void excluirTodas(int idUsr) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        dao.excluirTodasNotificacoes(idUsr);
    }
    public static int buscarIdUsrAnun(int id) throws SQLException{
        NotificacaoDAO dao = new NotificacaoDAO();
        return dao.buscarIdUsrAnuncioNoti(id);
    }
}
