/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.utils.ConnectionFactory;
import br.com.tads.tccpool.beans.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Marcos
 */
public class ComentarioDAO {
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private final String QUERY_SELECT = "SELECT\n"
                                      + "  TB_COMENTARIO.*,\n"
                                      + "  TB_USUARIO.NM_NOME\n"
                                      + "FROM\n"
                                      + "  TB_COMENTARIO\n"
                                      + "  INNER JOIN TB_USUARIO\n"
                                      + "  ON TB_COMENTARIO.ID_ORIGEM = TB_USUARIO.NR_SEQ\n"
                                      + "WHERE\n"
                                      + "      TB_ANUNCIO_ID_ANUNCIO = ?\n"
                                      + "  AND ID_PAI = 0\n"
                                      + "ORDER BY\n"
                                      + "  VL_LIKE DESC";
    private final String QUERY_INSERT = "INSERT INTO TB_COMENTARIO (\n" +
                                        "   TB_ANUNCIO_ID_ANUNCIO,\n" +
                                        "   DS_CONTEUDO,\n" +
                                        "   ID_ORIGEM,\n" +
                                        "   ID_PAI\n" +
                                        ") VALUES (?, ?, ?, ?)";
    private final String QUERY_LIKE = "UPDATE tb_comentario SET VL_LIKE = (SELECT VL_LIKE + 1) WHERE NR_SEQ = ?";
    private final String QUERY_UNLIKE = "UPDATE tb_comentario SET VL_UNLIKE = (SELECT VL_UNLIKE + 1) WHERE NR_SEQ = ?";
    private final String QUERY_VALIDA_REPLY = "SELECT 1 FROM TB_COMENTARIO WHERE ID_PAI = ?";
    private final String QUERY_SELECT_REPLY = "SELECT\n"
                                            + "  TB_COMENTARIO.*,\n"
                                            + "  TB_USUARIO.NM_NOME\n"
                                            + "FROM\n"
                                            + "  TB_COMENTARIO\n"
                                            + "  INNER JOIN TB_USUARIO\n"
                                            + "  ON TB_COMENTARIO.ID_ORIGEM = TB_USUARIO.NR_SEQ\n"
                                            + "WHERE\n"
                                            + "      TB_ANUNCIO_ID_ANUNCIO = ?\n"
                                            + "  AND ID_PAI = ?\n"
                                            + "ORDER BY\n"
                                            + "  VL_LIKE DESC";

    public ComentarioDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }
    
    public int inserir(Comentario comentario) throws SQLException {
        stmt = con.prepareStatement(QUERY_INSERT);
        stmt.setInt(1, comentario.getIdAnuncio());
        stmt.setString(2, comentario.getConteudo());
        stmt.setInt(3, comentario.getIdOrigem());
        stmt.setInt(4, comentario.getIdPai());
        
        return stmt.executeUpdate();
    }
    
    public ArrayList<Comentario> listar(int idAnuncio) throws SQLException, ParseException {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        stmt = con.prepareStatement(QUERY_SELECT);
        stmt.setInt(1, idAnuncio);
        rs = stmt.executeQuery();
        
        while(rs.next()) {
            Comentario c = new Comentario();
            c.setIdComentario(rs.getInt("NR_SEQ"));
            c.setIdAnuncio(rs.getInt("TB_ANUNCIO_ID_ANUNCIO"));
            c.setConteudo(rs.getString("DS_CONTEUDO"));
            c.setNmUser(rs.getString("NM_NOME"));
            c.setIdOrigem(rs.getInt("ID_ORIGEM"));
            c.setQtdeLikes(rs.getInt("VL_LIKE"));
            c.setQtdeUnlikes(rs.getInt("VL_UNLIKE"));
            c.getData().setTime(rs.getDate("DT_DATA"));
            
            comentarios.add(c);
        }
        
        return comentarios;
    }
    
    public boolean verificaReply(int idComentario) throws SQLException {
        stmt = con.prepareStatement(QUERY_VALIDA_REPLY);
        stmt.setInt(1, idComentario);
        rs = stmt.executeQuery();
        
        //Se houver replys para o id informado, retorna TRUE senão retorna FALSE
        return rs.next();
    }
    
    public ArrayList<Comentario> listarReply(int idAnuncio, int idComentario) throws SQLException{
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        stmt = con.prepareStatement(QUERY_SELECT_REPLY);
        stmt.setInt(1, idAnuncio);
        stmt.setInt(2, idComentario);
        
        rs = stmt.executeQuery();
        
        while(rs.next()) {
            Comentario c = new Comentario();
            c.setIdComentario(rs.getInt("NR_SEQ"));
            c.setIdAnuncio(rs.getInt("TB_ANUNCIO_ID_ANUNCIO"));
            c.setConteudo(rs.getString("DS_CONTEUDO"));
            c.setNmUser(rs.getString("NM_NOME"));
            c.setIdOrigem(rs.getInt("ID_ORIGEM"));
            c.setQtdeLikes(rs.getInt("VL_LIKE"));
            c.setQtdeUnlikes(rs.getInt("VL_UNLIKE"));
            c.getData().setTime(rs.getDate("DT_DATA"));
            
            comentarios.add(c);
        }
        
        return comentarios;
    }
    
    public int setLike(int idComentario) throws SQLException {
        stmt = con.prepareStatement(QUERY_LIKE);
        stmt.setInt(1, idComentario);
        return stmt.executeUpdate();
    }
    
    public int setUnlike(int idComentario) throws SQLException {
        stmt = con.prepareStatement(QUERY_UNLIKE);
        stmt.setInt(1, idComentario);
        return stmt.executeUpdate();
    }
    
}
