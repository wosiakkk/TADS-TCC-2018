/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.dao.UserDAO;
import br.com.tads.tccpool.exception.AcessoBdException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onurb
 */
public class UserFacade {
    public static User insereUsuario(User u) throws AcessoBdException, SQLException{
        UserDAO dao = new UserDAO();
        try{
            dao.inserirUser(u);
        }catch(Exception e){
            return null;
        }
        dao.close();
        return u;
    }
    public static User insereUsuarioGoogle(User u) throws AcessoBdException, SQLException{
        UserDAO dao = new UserDAO();
        try{
            dao.inserirUserGoogle(u);
        }catch(Exception e){
            return null;
        }
        dao.close();
        return u;
    }
    
    public static User buscarUsuario(int idUser) {
        UserDAO dao = new UserDAO();
        try{
            return dao.buscarUser(idUser);
        }catch(Exception e){
            return null;
        }
        finally {
            try {            
                dao.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static User buscarUsuariogGoogle(String email) {
        UserDAO dao = new UserDAO();
        try{
            return dao.verificaEmail(email);
        }catch(Exception e){
            return null;
        }
        finally {
            try {            
                dao.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * Editar ifnormações do usuário no banco de dados
     * @param u Bean User com as informações a serem inseridas no banco de dados
     * @param CPFUser CPF do usuário para identificar o registro no banco de dados
     * @return 
     */
    public static Boolean editarUsuario(User u) {
        UserDAO dao = new UserDAO();
        try {
            dao.editarUser(u);
            return true;
        }
        catch (Exception e) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public static Boolean editarPerfil(User u){
        UserDAO dao = new UserDAO();
        try {
            dao.editarPerfil(u);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static User geraPerfilUser(int idUser){
        UserDAO dao = new  UserDAO();
        User u = new User();
        try {
            u = dao.gerarPerfil(idUser);
        } catch (SQLException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public static Boolean solicitarAmizade(int idSolicitante, int idSolicitado){
        UserDAO dao = new UserDAO();
        if(dao.solicitarAmizade(idSolicitante, idSolicitado)){
            return true;
        }else{
             return false;
        }
    }
    public static Boolean solicitarAmizade2(int idSolicitante, int idSolicitado){
        UserDAO dao = new UserDAO();
        if(dao.solicitarAmizade2(idSolicitante, idSolicitado)){
            return true;
        }else{
             return false;
        }
    }
    
    public static int checandoAmizade(int idSessao, int idPerfil){
        UserDAO dao = new UserDAO();
        return dao.checarAmizade(idSessao, idPerfil);
    }
    
    public static void aceitarAmizade(int idSolicitante, int idSolicitado){
        UserDAO dao = new UserDAO();
        dao.aceitarAmizade(idSolicitado, idSolicitante);
    }
    
    
     //******************************
            // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
            // futuramente a busca será aprimorada
    public static int buscarIdPorNomeDoUsuario(String nome){
        UserDAO dao = new UserDAO();
        return dao.buscarIdPorNome(nome);
    }
     //******************************
     //******************************
            
}
