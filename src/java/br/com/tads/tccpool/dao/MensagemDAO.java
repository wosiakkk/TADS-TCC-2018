/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.beans.Conversa;
import br.com.tads.tccpool.utils.ConnectionFactory;
import br.com.tads.tccpool.beans.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Marcos
 */
public class MensagemDAO {
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private final String QUERY_INSERT = "{CALL INSERIR_MSG(?, ?, ?)}";
    private final String QUERY_SELECT = "SELECT\n" +
                                        "  TB_MENSAGEM.*,\n" +
                                        "  ORIGEM.NM_NOME AS NM_ORIGEM,\n" +
                                        "  ORIGEM.DS_FOTO AS DS_FOTO_ORIGEM,\n" +
                                        "  DESTINO.NM_NOME AS NM_DESTINO,\n" +
                                        "  DESTINO.DS_FOTO AS DS_FOTO_DESTINO\n" +
                                        "FROM\n" +
                                        "  TB_MENSAGEM\n" +
                                        "  INNER JOIN TB_CONVERSA \n" +
                                        "  ON TB_CONVERSA.ID_CONVERSA = TB_MENSAGEM.ID_CONVERSA\n" +
                                        "  LEFT JOIN TB_USUARIO AS ORIGEM\n" +
                                        "  ON ORIGEM.NR_SEQ = TB_MENSAGEM.ID_ORIGEM\n" +
                                        "  LEFT JOIN TB_USUARIO AS DESTINO\n" +
                                        "  ON DESTINO.NR_SEQ = TB_CONVERSA.ID_USR_DESTINO\n" +
                                        "WHERE\n" +
                                        "  ID_ORIGEM IN (?, ?)\n" +
                                        "  AND ID_DESTINO IN (?, ?)\n" +
                                        "ORDER BY DT_MSG ASC";
    private final String QUERY_LISTAR_CONVERSAS = "SELECT \n" +
                                                  "  TB_CONVERSA.*,\n" +
                                                  "  ORIGEM.NR_SEQ AS ID_ORIGEM,\n" +
                                                  "  ORIGEM.NM_NOME AS NM_ORIGEM,\n" +
                                                  "  ORIGEM.DS_FOTO AS FOTO_ORIGEM,\n" +
                                                  "  DESTINO.NR_SEQ AS ID_DESTINO,\n" +
                                                  "  DESTINO.NM_NOME AS NM_DESTINO,\n" +
                                                  "  DESTINO.DS_FOTO AS FOTO_DESTINO\n" +
                                                  "FROM\n" +
                                                  "  TB_CONVERSA\n" +
                                                  "  LEFT JOIN TB_USUARIO AS ORIGEM ON ORIGEM.NR_SEQ = TB_CONVERSA.ID_USR_ORIGEM AND ORIGEM.NR_SEQ != TB_CONVERSA.ID_USR_DESTINO\n" +
                                                  "  LEFT JOIN TB_USUARIO AS DESTINO ON DESTINO.NR_SEQ = TB_CONVERSA.ID_USR_DESTINO AND DESTINO.NR_SEQ != TB_CONVERSA.ID_USR_ORIGEM\n" +
                                                  "WHERE\n" +
                                                  "   TB_CONVERSA.ID_USR_ORIGEM = ?\n" +
                                                  "OR TB_CONVERSA.ID_USR_DESTINO = ?";
    
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
        stmt.setInt(3, idOrigem);
        stmt.setInt(4, idDestino);
        //stmt.setInt(3, idConversa);
        rs = stmt.executeQuery();
        
        while(rs.next()) {
            Mensagem m = new Mensagem();
            m.setIdConversa(rs.getInt("ID_CONVERSA"));
            m.setIdOrigem(rs.getInt("ID_ORIGEM"));
            m.setIdDestino(rs.getInt("ID_DESTINO"));
            m.setConteudo(rs.getString("DS_MSG"));
            m.setNmOrigem(rs.getString("NM_ORIGEM"));
            m.setNmDestino(rs.getString("NM_DESTINO"));
            m.setFotoDestino(rs.getString("DS_FOTO_DESTINO"));
            m.setFotoOrigem(rs.getString("DS_FOTO_ORIGEM"));
            m.getData().setTime(rs.getDate("DT_MSG"));
            
            mensagens.add(m);
        }
        con.close();
        stmt.close();
        rs.close();
        
        return mensagens;
    }
    
    public ArrayList<Conversa> listarConversas(Integer userLogado) throws SQLException {
        ArrayList<Conversa> lConversas = new ArrayList<>();
        stmt = con.prepareStatement(QUERY_LISTAR_CONVERSAS);
        stmt.setInt(1, userLogado);
        stmt.setInt(2, userLogado);
        
        rs = stmt.executeQuery();
        
        while(rs.next()) {
            Conversa c = new Conversa();
            c.setIdConversa(rs.getInt("ID_CONVERSA"));
            c.setIdOrigem(rs.getInt("ID_USR_ORIGEM"));
            c.setNmOrigem(rs.getString("NM_ORIGEM"));
            c.setFotoOrigem(rs.getString("FOTO_ORIGEM"));
            c.setIdDestino(rs.getInt("ID_USR_DESTINO"));
            c.setNmDestino(rs.getString("NM_DESTINO"));
            c.setFotoDestino(rs.getString("FOTO_DESTINO"));
            c.getDtInicio().setTime(rs.getDate("DT_INICIO"));
            
            lConversas.add(c);
        }
        
        con.close();
        stmt.close();
        rs.close();
        
        return lConversas;
    }
    
    
}
