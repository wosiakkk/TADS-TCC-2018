/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.utils.ConnectionFactory;
import br.com.tads.tccpool.beans.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
/**
 *
 * @author Marcos
 */
public class MensagemDAO {
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private final String QUERY_INSERT = "INSERT INTO\n" +
                                        "  TB_MENSAGEM (\n" +
                                        "    ID_ORIGEM,\n" +
                                        "    ID_DESTINO,\n" +
                                        "    DS_MSG\n" +
                                        "  )\n" +
                                        "VALUES (?, ?, ?);";
    private final String QUERY_SELECT = "SELECT\n" +
                                        "  TB_MENSAGEM.*,\n" +
                                        "  (SELECT NM_NOME FROM TB_USUARIO WHERE TB_USUARIO.NR_SEQ = TB_MENSAGEM.ID_ORIGEM) AS NM_ORIGEM,\n" +
                                        "  (SELECT NM_NOME FROM TB_USUARIO WHERE TB_USUARIO.NR_SEQ = TB_MENSAGEM.ID_DESTINO) AS NM_DESTINO\n" +
                                        "FROM\n" +
                                        "  TB_MENSAGEM\n" +
                                        "WHERE\n" +
                                        "      ID_ORIGEM IN (?, ?)\n" +
                                        //"  AND ID_DESTINO = ?\n" +
                                        "ORDER BY DT_MSG ASC";
    
    public MensagemDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }
    
    public int inserir(Mensagem mensagem) throws SQLException {
        stmt = con.prepareStatement(QUERY_INSERT);
        stmt.setInt(1, mensagem.getIdOrigem());
        stmt.setInt(2, mensagem.getIdDestino());
        stmt.setString(3, mensagem.getConteudo());
        
        return stmt.executeUpdate();
    }
    
    public ArrayList<Mensagem> listar(int idOrigem, int idDestino) throws SQLException {
        ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
        stmt = con.prepareStatement(QUERY_SELECT);
        stmt.setInt(1, idOrigem);
        stmt.setInt(2, idDestino);
        rs = stmt.executeQuery();
        
        while(rs.next()) {
            Mensagem m = new Mensagem();
            m.setIdOrigem(rs.getInt("ID_ORIGEM"));
            m.setIdDestino(rs.getInt("ID_DESTINO"));
            m.setConteudo(rs.getString("DS_MSG"));
            m.setNmOrigem(rs.getString("NM_ORIGEM"));
            m.setNmDestino(rs.getString("NM_DESTINO"));
            m.getData().setTime(rs.getDate("DT_MSG"));
            
            mensagens.add(m);
        }
        
        return mensagens;
    }
    
    
}
