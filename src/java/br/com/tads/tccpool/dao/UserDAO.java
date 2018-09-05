/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.dao;

import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class UserDAO {
    private static final String QUERY_LOGIN = "SELECT NR_SEQ, DS_EMAIL, NM_NOME, TP_USUARIO, DS_FOTO FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
    private static final String QUERY_LOGIN_GOOGLE = "SELECT NR_SEQ, NM_NOME, DS_EMAIL, DS_FOTO,TP_USUARIO FROM TB_USUARIO WHERE DS_EMAIL = ?";
    private static final String QUERY_SIMPLE_INSERT_USR = "INSERT INTO TB_USUARIO"
            + " (NM_NOME,DS_EMAIL,DS_SENHA,TP_USUARIO)"
            + " VALUES (?,?,?,?)";
    private static final String QUERY_SIMPLE_INSERT_GOOGLE = "INSERT INTO TB_USUARIO"
            + " (NM_NOME,DS_EMAIL,DS_FOTO,TP_USUARIO)"
            + " VALUES (?,?,?,?)";
    private static final String QUERY_INSERT_END = "INSERT INTO TB_ENDERECO (NM_RUA,NM_ESTADO,NR_RUA,NR_CEP,DS_COMPLEMENTO,NM_CIDADE)"
            + " values(?,?,?,?,?,?)";
    private static final String QUERY_SELECT_USR = "SELECT\n" +
                                                        "user.NR_SEQ,\n" +
                                                        "user.NM_NOME,\n" +
                                                        "user.DS_EMAIL,\n" +
                                                        "user.NR_TELEFONE, \n" +
                                                        "user.NR_CELULAR,\n" +
                                                        "user.DS_SENHA, \n" +
                                                        "user.CD_ENDERECO, \n" +
                                                        "endr.NM_RUA,\n" +
                                                        "endr.NM_ESTADO,\n" +
                                                        "endr.NR_RUA,\n" +
                                                        "endr.NR_CEP,\n" +
                                                        "endr.DS_COMPLEMENTO,\n" +
                                                        "endr.NM_CIDADE\n" +
                                                    "FROM tcc1.tb_usuario AS user \n" +
                                                    "LEFT JOIN tcc1.tb_endereco AS endr ON user.CD_ENDERECO = endr.NR_SEQ \n" +
                                                    "WHERE \n" +
                                                        "user.NR_SEQ = ? AND user.TP_USUARIO = 2";
    
    private static final String QUERY_EDIT_PERFIL = "UPDATE tb_usuario SET\n"
                                                        + " NM_NOME = ?,"
                                                        + " DS_DESCRICAO_USER = ?,"
                                                        + " DS_INTERESSES= ?"
                                                  + " WHERE"
                                                        + " NR_SEQ = ?";
    
    private static final String QUERY_GERAR_PERFIL =" SELECT tb_usuario.NM_NOME, "
                                                        + "tb_usuario.DS_FOTO, tb_usuario.DS_DESCRICAO_USER,\n" +
                                                        " tb_usuario.DS_INTERESSES FROM tcc1.tb_usuario "
                                                        + "WHERE tb_usuario.NR_SEQ = ?";
    
    private static final String QUERY_EDIT_USR = "UPDATE tb_usuario SET\n" +
                                                      "DS_FOTO = ?" +
                                                 "WHERE\n" +
                                                          "NR_SEQ = ?";
    
    private static final String QUERY_EDIT_END = "UPDATE tb_endereco SET\n" +
                                                    "NM_RUA = ?," +
                                                    "NM_ESTADO = ?," +
                                                    "NR_RUA = ?," +
                                                    "NR_CEP = ?," +
                                                    "DS_COMPLEMENTO = ?," +
                                                    "NM_CIDADE = ?\n" +
                                                "WHERE NR_SEQ = ?";
    
    private static final String QUERY_SOLICITAR_AMIZADE ="INSERT into tb_amizade (tb_amizade.id_solicitante, "
                                              + "tb_amizade.id_solicitado, tb_status_amizade_NR_STATUS_AMIGO, "
                                              + "tb_amizade.tb_usuario_NR_SEQ) VALUES (?,?,?,?)";
    
    private static final String QUERY_ACEITAR_AMIZADE ="UPDATE tb_amizade SET "
            + "tb_status_amizade_NR_STATUS_AMIGO = 2 WHERE "
            + "id_solicitado = ? AND id_solicitante = ? "
            + "and tb_status_amizade_NR_STATUS_AMIGO = 1";
    
    private static final String QUERY_BUSCAR_USUARIOS ="SELECT * FROM tb_usuario WHERE NM_NOME LIKE ?";
    
    private static final String QUERY_SELECIONAR_IDS_AMIZADE ="SELECT tb_amizade.id_solicitado, tb_amizade.id_solicitante,"
            + " tb_amizade.tb_status_amizade_NR_STATUS_AMIGO "
            + "from tb_amizade where tb_amizade.tb_usuario_NR_SEQ = ?";
    
    //******************************
     // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
     // futuramente a busca será aprimorada
    private static final String QUERY_BUSCAR_ID_POR_NOME ="select tb_usuario.NR_SEQ FROM tb_usuario where tb_usuario.NM_NOME = ?";
    //***************************************************
    //***************************************************
    
    private Connection con = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public UserDAO(){
        ConnectionFactory  cf = new ConnectionFactory();
        con = cf.getConnection(); 
    }
    //método para fechar a conexão do bd
    public void close() throws SQLException {
        if (rs!=null) {
            try { rs.close(); }
            catch (Exception e) {}
            finally { rs = null; }
        }
        if (stmt!=null) {
            try { stmt.close(); }
            catch (Exception e) {}
            finally { stmt = null; }
        }
        con.close();
        con = null;
    }
    
    public void inserirUser(User u) throws SQLException{
        try{
                stmt = con.prepareStatement(QUERY_SIMPLE_INSERT_USR);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getEmail());
                stmt.setString(3, u.getSenha());
                stmt.setInt(4, u.getTipoUsuario());
                stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            stmt.close();
            con.close();
        }
    }
    
    public void inserirUserGoogle(User u) throws SQLException{
        try{
                stmt = con.prepareStatement(QUERY_SIMPLE_INSERT_GOOGLE);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getEmail());
                stmt.setString(3, u.getFoto());
                stmt.setInt(4, u.getTipoUsuario());
                stmt.executeUpdate();
                con.close();
                stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            stmt.close();
            con.close();
        }
    }
    
    
    public User verificaEmail(String email) throws SQLException{
        User u = null;
        try{
            stmt = con.prepareStatement(QUERY_LOGIN_GOOGLE);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while(rs.next()){
                u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setNome(rs.getString("NM_NOME"));
                u.setFoto(rs.getString("DS_FOTO"));
                u.setTipoUsuario(rs.getInt("TP_USUARIO"));
            }
        }catch(SQLException e){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            u = null;
        }finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return u;
    }
    
    /**
     * 
     * @param login
     * @param senha
     * @return
     * @throws SQLException 
     */
    public User verificaLogin(String login, String senha) throws SQLException{
        User u = null;
        try{
            stmt = con.prepareStatement(QUERY_LOGIN);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setNome(rs.getString("NM_NOME"));
                u.setFoto(rs.getString("DS_FOTO"));
                u.setTipoUsuario(rs.getInt("TP_USUARIO"));
            }
            
        }catch(SQLException e){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            u = null;
        }finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return u;
    }
    
    public User buscarUser(int idUser) {
        try {
            stmt = con.prepareStatement(QUERY_SELECT_USR);
            stmt.setInt(1, idUser);
            rs = stmt.executeQuery();
            if(rs.next()) {
                User u = new User();
                u.setId(idUser);
             //   u.setCpf(rs.getString("NR_CPF"));
             if(rs.getString("NM_NOME") != null){
                u.setNome(rs.getString("NM_NOME"));
             }
             if(rs.getString("DS_EMAIL") != null){
                u.setEmail(rs.getString("DS_EMAIL"));
             }
             if(rs.getString("DS_SENHA") != null){
                u.setSenha(rs.getString("DS_SENHA"));
             }
             if(rs.getString("NR_TELEFONE") != null){
                u.setTel(rs.getString("NR_TELEFONE"));
             }
             if(rs.getString("NR_CELULAR") != null){
                u.setCel(rs.getString("NR_CELULAR"));
             }
             if(rs.getString("NR_CEP") != null){
                u.setCep(rs.getString("NR_CEP"));
             }
                u.setNumero(rs.getInt("NR_RUA"));
             if(rs.getString("NM_RUA") != null){
                u.setLogradouro(rs.getString("NM_RUA"));
             }
             if(rs.getString("DS_COMPLEMENTO") != null){
                u.setComplemento(rs.getString("DS_COMPLEMENTO"));
             }
             if(rs.getString("NM_ESTADO") != null){
                u.setEstado(rs.getString("NM_ESTADO"));
             }
             if(rs.getString("NM_CIDADE") != null){
                u.setCidade(rs.getString("NM_CIDADE"));
             }
             //   u.setInstituicao(rs.getInt("CD_INST"));
                u.setCdEndereco(rs.getInt("CD_ENDERECO"));
             if(rs.getString("DS_FOTO") != null){
                u.setFoto(rs.getString("DS_FOTO"));
             }
                
                return u;
            }
            else {
                throw new SQLException();
            }
        }
        catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
    public void editarPerfil(User u) throws SQLException{
       stmt = con.prepareStatement(QUERY_EDIT_PERFIL);
       stmt.setString(1, u.getNome());
       stmt.setString(2, u.getDescricao());
       stmt.setString(3, u.getInteresses());
       stmt.setInt(4, u.getId());
       stmt.executeUpdate();
       stmt.close();
       rs.close();
       con.close();
    }
    
    public User gerarPerfil(int idUser) throws SQLException{
        User u = new User();
        stmt = con.prepareStatement(QUERY_GERAR_PERFIL);
        stmt.setInt(1, idUser);
        rs = stmt.executeQuery();
        if(rs.next()){
            u.setId(idUser);
            u.setNome(rs.getString("NM_NOME"));
            u.setFoto(rs.getString("DS_FOTO"));
            u.setDescricao(rs.getString("DS_DESCRICAO_USER"));
            u.setInteresses(rs.getString("DS_INTERESSES"));
            stmt.close();
            rs.close();
            con.close();
            return u;
        }else{
            stmt.close();
            rs.close();
            con.close();
            return u;
        }
    }
    



    public void editarUser(User u) {

        try{
            /*stmt = con.prepareStatement(QUERY_EDIT_END);
            stmt.setString(1, u.getLogradouro());
            stmt.setString(2, u.getEstado());
            stmt.setInt(3, u.getNumero());
            stmt.setString(4, u.getCep());
            stmt.setString(5, u.getComplemento());
            stmt.setString(6, u.getCidade());
            stmt.setInt(7, u.getCdEndereco());
            stmt.executeUpdate();
            
            int editEndOK = stmt.executeUpdate();*/
            
            stmt = con.prepareStatement(QUERY_EDIT_USR);
           // stmt.setString(1, u.getCpf());
            //stmt.setString(2, u.getNome());
            //stmt.setString(3, u.getEmail());
            //stmt.setString(4, u.getTel());
            //stmt.setString(5, u.getCel());
          //  stmt.setInt(6, u.getInstituicao());
            //stmt.setString(7, u.getSenha());
            //stmt.setInt(8, u.getId());
            //stmt.setString(9, CPFUser);
            stmt.setString(1, u.getFoto());
            stmt.setInt(2, u.getId());
            int editUsrOK = stmt.executeUpdate();            
            
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Boolean solicitarAmizade(int idSolicitante, int idSolicitado){
        //alterando para o solicitante:
        try {           
            stmt= con.prepareStatement(QUERY_SOLICITAR_AMIZADE);
            stmt.setInt(1, idSolicitante);
            stmt.setInt(2, idSolicitado);
            stmt.setInt(3, 1);
            stmt.setInt(4, idSolicitante);
            stmt.executeUpdate();
            
            stmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }       
        return true;
    }
    
     public Boolean solicitarAmizade2(int idSolicitante, int idSolicitado){             
        //ALTERANDO PARA O SOLICITADO
        try {           
            stmt= con.prepareStatement(QUERY_SOLICITAR_AMIZADE);
            stmt.setInt(1, idSolicitante);
            stmt.setInt(2, idSolicitado);
            stmt.setInt(3, 1);
            stmt.setInt(4, idSolicitado);
            stmt.executeUpdate();
          
            stmt.close();
            con.close();
        } catch (SQLException ex) {          
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
     
     public ArrayList buscarUsuariosDinamicamente(String s){
         ArrayList usuariosAchados = new ArrayList();
         
        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_USUARIOS);
            stmt.setString(1, "%"+s+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("NR_SEQ"));
                u.setNome(rs.getString("NM_NOME"));
                usuariosAchados.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return usuariosAchados;
     }
     
     public int checarAmizade(int idSessao, int idPerfil){
         
        try {
         int statusAmizade;
         int idSolicitante;
         int idSolicitado;
            stmt = con.prepareStatement(QUERY_SELECIONAR_IDS_AMIZADE);
            stmt.setInt(1, idSessao);
            rs = stmt.executeQuery();
            while(rs.next()){                
                    idSolicitante = rs.getInt("id_solicitante");
                    idSolicitado = rs.getInt("id_solicitado");
                    statusAmizade= rs.getInt("tb_status_amizade_NR_STATUS_AMIGO");
                    if(idPerfil==idSolicitante || idPerfil == idSolicitado){
                        if(idSessao == idSolicitante && idPerfil == idSolicitado && statusAmizade == 1){
                            return 1; //solicitação enviada
                        }else if(idSessao == idSolicitado && idPerfil== idSolicitante && statusAmizade == 1){
                            return 2;//aceitar solicitação
                        }else if( statusAmizade == 2){
                            return 3;//vocês já são amigos
                        }
                    }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return 0; //nenhuma ação de amizade
     }
     
     
     public void aceitarAmizade(int idSolicitante,int idSolicitado ){
         
        try {
            stmt = con.prepareStatement(QUERY_ACEITAR_AMIZADE);
            stmt.setInt(1, idSolicitado);
            stmt.setInt(2, idSolicitante);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
     
     
     //******************************
     // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
     // futuramente a busca será aprimorada
     public int buscarIdPorNome(String nome){
         int id=-1;
        try {
            stmt = con.prepareStatement(QUERY_BUSCAR_ID_POR_NOME);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            if(rs.next()){
                id = rs.getInt("NR_SEQ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return id;
     }
     //***************************************
     //***************************************
            
}
