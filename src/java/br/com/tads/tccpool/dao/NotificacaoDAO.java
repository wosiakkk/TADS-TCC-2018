/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.beans.Notificacao;
import br.com.tads.tccpool.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class NotificacaoDAO {

    private static final String QUERY_INSERIR_NOTIFICACAO = "insert into tb_notificacao \n"
            + "(tb_notificacao.ID_GERADOR,tb_notificacao.tb_usuario_NR_SEQ,"
            + " tb_notificacao.tb_status_notificacao_NR_SEQ,tb_notificacao.tb_tipo_notificacao_NR_SEQ)\n"
            + " values (?,?,?,?)";
    
    private static final String QUERY_BUSCAR_TODAS_NOTIFICACOES = "SELECT tb_notificacao.ID_GERADOR, "
            + "tb_notificacao.tb_tipo_notificacao_NR_SEQ, tb_notificacao.tb_status_notificacao_NR_SEQ \n" +
            " FROM tb_notificacao WHERE tb_notificacao.tb_usuario_NR_SEQ = ?";
    
    private static final String QUERY_BUSCAR_NOTIFICACOES_N_LIDAS = "SELECT tb_notificacao.ID_GERADOR,"
            + " tb_notificacao.tb_tipo_notificacao_NR_SEQ \n" +
            " FROM tb_notificacao WHERE (tb_notificacao.tb_usuario_NR_SEQ = ?) AND\n" +
            " (tb_notificacao.tb_status_notificacao_NR_SEQ = 2)";
    
    private static final String QUERY_LER_NOTIFICACOES = "UPDATE tcc1.tb_notificacao "
            + "SET tb_status_notificacao_NR_SEQ = 1 WHERE tb_notificacao.tb_usuario_NR_SEQ = ?";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public NotificacaoDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    //método para fechar a conexão do bd
    public void close() throws SQLException {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {

            } finally {
                rs = null;
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {

            } finally {
                stmt = null;
            }
        }
        con.close();
        con = null;
    }

    public void inserirNoticacao(int gerador, int receptor, int tipoNotificacao) throws SQLException {
        switch (tipoNotificacao) {
            //pedido de amizade
            case 1: {
                try {
                    stmt = con.prepareStatement(QUERY_INSERIR_NOTIFICACAO);
                    stmt.setInt(1, gerador);
                    stmt.setInt(2, receptor);
                    stmt.setInt(3, 2);//status não lida
                    stmt.setInt(4, tipoNotificacao);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally {
                    stmt.close();
                    con.close();
                }
                break;
            }
            //amizade aceita
            case 2: {
                try {
                    stmt = con.prepareStatement(QUERY_INSERIR_NOTIFICACAO);
                    stmt.setInt(1, gerador);
                    stmt.setInt(2, receptor);
                    stmt.setInt(3, 2);//status não lida
                    stmt.setInt(4, tipoNotificacao);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally {
                    stmt.close();
                    con.close();
                }
                break;
            }
        }
    }
    
    public List<Notificacao> buscarTodasNotificacoes(int idUser) throws SQLException{
        ArrayList<Notificacao> listaNoti = new ArrayList<>();
        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_TODAS_NOTIFICACOES);
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();
            while(rs.next()){
                Notificacao n = new Notificacao();
                n.setIdGerador(rs.getInt("ID_GERADOR"));
                n.setTipoNot(rs.getInt("tb_tipo_notificacao_NR_SEQ"));
                n.setStatusNot(rs.getInt("tb_status_notificacao_NR_SEQ"));
                listaNoti.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            stmt.close();
            con.close();
            rs.close();
        }
        return listaNoti;
    }
    
    public List<Notificacao> buscarNovasNotificacoes(int idUser) throws SQLException{
        ArrayList<Notificacao> listaNoti = new ArrayList<>();
        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_NOTIFICACOES_N_LIDAS);
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();
            while(rs.next()){
                Notificacao n = new Notificacao();
                n.setIdGerador(rs.getInt("ID_GERADOR"));
                n.setTipoNot(rs.getInt("tb_tipo_notificacao_NR_SEQ"));               
                listaNoti.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            stmt.close();
            con.close();
            rs.close();
        }
        return listaNoti;
    }
    
    public void lerNotificacoes(int idUsr) throws SQLException{
        try { 
            stmt = con.prepareStatement(QUERY_LER_NOTIFICACOES);
            stmt.setInt(1, idUsr);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            stmt.close();
            con.close();
        }
    }
}
