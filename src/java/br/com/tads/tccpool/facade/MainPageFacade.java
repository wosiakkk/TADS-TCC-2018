/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.Categoria;
import br.com.tads.tccpool.beans.Instituicao;
import br.com.tads.tccpool.dao.CategoriaDAO;
import br.com.tads.tccpool.exception.AcessoBdException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author onurb
 */
public class MainPageFacade {
    
    public static List<Categoria> listaCategorias() throws AcessoBdException, SQLException{
       CategoriaDAO dao = new CategoriaDAO();
        try{ 
            return dao.getCategorias();
       }finally{
           dao.close();
       }
    } 
    
    public static List<Categoria> listaCategoriasImovel() throws AcessoBdException, SQLException{
       CategoriaDAO dao = new CategoriaDAO();
        try{ 
            return dao.getCategoriasImovel();
       }finally{
           dao.close();
       }    
    } 
    public static List<Categoria> listaCategoriasMovel() throws AcessoBdException, SQLException{
       CategoriaDAO dao = new CategoriaDAO();
        try{ 
            return dao.getCategoriasMovel();
       }finally{
           dao.close();
       }    
    } 
    public static List<Categoria> listaCategoriasMaterial() throws AcessoBdException, SQLException{
       CategoriaDAO dao = new CategoriaDAO();
        try{ 
            return dao.getCategoriasMaterial();
       }finally{
           dao.close();
       }    
    } 
}
