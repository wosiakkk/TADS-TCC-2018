/* 
 * Created at: 20/10/2018
 * By: Marcos Silva
 */


package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.utils.ConnectionFactory;
import br.com.tads.tccpool.beans.Anuncio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class TimelineDAO {
    private final String TIMELINE_PADRAO = "{CALL TIMELINE_PADRAO(?)}";

    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public TimelineDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        this.con = cf.getConnection();
    }
    
    public ArrayList<Anuncio> callTimeline(Integer userLogado) throws SQLException {
        ArrayList<Anuncio> timeline = new ArrayList<>();
        stmt = con.prepareStatement(TIMELINE_PADRAO);
        stmt.setInt(1, userLogado);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Anuncio a = new Anuncio();
            a.setIdAnuncio(rs.getInt("ID_ANUNCIO"));
            a.setTitulo(rs.getString("DS_TITULO"));
            a.setDescricao(rs.getString("DS_DESCRICAO"));
            a.setValor(rs.getFloat("NR_VALOR"));
            a.getDtAnuncio().setTime(rs.getDate("DT_ANUNCIO"));
            a.setCaminhoFoto(rs.getString("DS_CAMINHO"));
            a.setIdUsuario(rs.getInt("ID_USER"));
            a.setNmUsuario(rs.getString("NM_USER"));
            a.setDsFotoUsuario(rs.getString("FOTO_USER"));
            
            timeline.add(a);
        }
        
        con.close();
        stmt.close();
        rs.close();
        
        return timeline;
        
    }
}
