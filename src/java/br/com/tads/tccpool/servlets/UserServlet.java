/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.exception.AcessoBdException;
import br.com.tads.tccpool.facade.UserFacade;
import br.com.tads.tccpool.utils.MD5;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author onurb
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();

            //******************************
            // implementado apenas para finalizar a sprint da lista de amigos, pois os nomes do user podem ser iguais
            // futuramente a busca será aprimorada
            String action = request.getParameter("action");
            
            //Validação de acesso
            if(session == null && !("ADD".equals(action))) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("title", "Inicio");
                request.setAttribute("msg", "Faça login para acessar esta página!");
                rd.forward(request, response);
            }
            
            User u = new User();
            switch (action) {
                case "ADD": {
                    u.setNome(request.getParameter("nome"));
                    u.setEmail(request.getParameter("email"));
                    u.setSenha(MD5.criptografar(request.getParameter("senha")));                    
                    try{
                        if(u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    }
                    catch(NullPointerException ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                        String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                        response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                    }
                    //usuário comum
                    u.setTipoUsuario(2);
                    try {
                        User userLogado = UserFacade.insereUsuario(u);
                        if (userLogado == null) {
                            String param = URLEncoder.encode("Falha ao cadastrar usuário.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        } else {
                            response.sendRedirect("login.jsp");
                        }
                    } catch (AcessoBdException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
                case "ADDMOD": {
                    u.setNome(request.getParameter("nome"));
                    u.setEmail(request.getParameter("email"));
                    u.setSenha(MD5.criptografar(request.getParameter("senha"))); 
                    u.setFoto("img\\fotosPerfil\\mod.png");
                    try{
                        if(u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    }
                    catch(NullPointerException ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                        String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                        response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                    }
                    //usuário moderador
                    u.setTipoUsuario(1);
                    try {
                        User userLogado = UserFacade.insereUsuarioModAdm(u);
                        if (userLogado == null) {
                            String param = URLEncoder.encode("Falha ao cadastrar moderador.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        } else {
                            response.sendRedirect("modCadastrado.jsp");
                        }
                    } catch (AcessoBdException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
                case "ADDADM": {
                    u.setNome(request.getParameter("nome"));
                    u.setEmail(request.getParameter("email"));
                    u.setSenha(MD5.criptografar(request.getParameter("senha"))); 
                    u.setFoto("img\\fotosPerfil\\adm.png");
                    try{
                        if(u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    }
                    catch(NullPointerException ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                        String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                        response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                    }
                    //usuário moderador
                    u.setTipoUsuario(3);
                    try {
                        User userLogado = UserFacade.insereUsuarioModAdm(u);
                        if (userLogado == null) {
                            String param = URLEncoder.encode("Falha ao cadastrar moderador.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        } else {
                            response.sendRedirect("admCadastrado.jsp");
                        }
                    } catch (AcessoBdException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
                case "EDIT": {
                    User alterar = new User();
                    String caminhoFoto = new String();
                    try {
                        /*Faz o parse do request*/
                        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        /*Escreve a o arquivo na pasta img*/
                        for (FileItem item : multiparts) {
                            if (item.isFormField()) {
                                if (item.getFieldName().equals("idUser")) {
                                    alterar.setId(Integer.parseInt(item.getString()));
                                }
                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(rand.nextInt()) + ".jpg";
                                if (!item.getName().equals("")) {
                                    item.write(new File(request.getServletContext().getRealPath("img/fotosPerfil") + File.separator + nomeString));
                                    caminhoFoto = "img/fotosPerfil" + File.separator + nomeString;
                                    alterar.setFoto(caminhoFoto);
                                }
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        boolean edit = UserFacade.editarUsuario(alterar);
                        if (edit) {
                            alterar = UserFacade.buscarUsuario(alterar.getId());
                            session.setAttribute("userSearch", alterar);
                            User us = (User) session.getAttribute("user");
                            us.setFoto(alterar.getFoto());
                            session.setAttribute("user", us);
                        } else {
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("msg", "Falha ao Realizar Anuncio: " + ex);
                    } finally {
                        request.getRequestDispatcher("editarPerfil.jsp").forward(request, response);
                    }
                    break;
                }
                case "SEARCH": {
                    User userSession = (User) session.getAttribute("user");
                    User userSearch = UserFacade.buscarUsuario(userSession.getId());
                    session.setAttribute("userSearch", userSearch);
                    RequestDispatcher rd = request.getRequestDispatcher("MainPageServlet?action=EDITAR");
                    rd.forward(request, response);
                    break;
                }
                case "REMOVE": {
                    break;
                }
                case "PERFIL": {
                    User perfil = new User();
                    int id = Integer.parseInt(request.getParameter("idUser"));
                    int idSessao = (int) session.getAttribute("idUserSessao");
                    perfil = UserFacade.geraPerfilUser(id);
                    int amizade = UserFacade.checandoAmizade(idSessao, id);
                    session.setAttribute("perfil", perfil);
                    session.setAttribute("amizade", amizade);
                    RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
                    rd.forward(request, response);
                    break;
                }
                case "AMIZADE": {
                    String acao = request.getParameter("acao");
                    switch (acao) {
                        case "SOLICITAR": {
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            int idSolicitado = Integer.parseInt(request.getParameter("idSolicitado"));

                            if (UserFacade.solicitarAmizade(idSolicitante, idSolicitado)) {
                                if (UserFacade.solicitarAmizade2(idSolicitante, idSolicitado)) {
                                    RequestDispatcher rd = request.getRequestDispatcher("solicitarAmizade.jsp");
                                    rd.forward(request, response);
                                } else {
                                    //erro na tabela do solictado
                                }
                            } else {
                                //erro na tabela do solicitante
                            }
                            break;
                        }
                        case "ACEITAR": {
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            int idSolicitado = Integer.parseInt(request.getParameter("idSolicitado"));
                            UserFacade.aceitarAmizade(idSolicitante, idSolicitado);
                            RequestDispatcher rd = request.getRequestDispatcher("amizadeAceita.jsp");
                            rd.forward(request, response);
                            break;
                        }
                        case "REJEITAR": {
                            int idSessaoSolicitado = Integer.parseInt(request.getParameter("idSessao"));
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            if(UserFacade.rejeitarAmizade(idSessaoSolicitado, idSolicitante)){
                                RequestDispatcher rd = request.getRequestDispatcher("amizadeRejeitada.jsp");
                                rd.forward(request, response);
                            }else{
                                //erro rejeição
                            }
                            break;
                        }
                        case "REJEITAREBLOQUEAR": {
                            int idSessaoSolicitado = Integer.parseInt(request.getParameter("idSessao"));
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            if(UserFacade.rejeitarBloquear(idSessaoSolicitado, idSolicitante)){
                                RequestDispatcher rd = request.getRequestDispatcher("amizadeBloqueada.jsp");
                                rd.forward(request, response);
                            }else{
                                //erro 
                            }
                            break;
                        }
                        case "DESBLOQUEAR": {
                            int idSessao = Integer.parseInt(request.getParameter("idSessao"));
                            int idDesbloqueio = Integer.parseInt(request.getParameter("idSolicitante")); 
                            if(UserFacade.desbloquearUsuario(idSessao, idDesbloqueio)){
                                RequestDispatcher rd = request.getRequestDispatcher("amizadeDesbloqueada.jsp");
                                rd.forward(request, response);
                            }else{                               
                            }
                            break;
                        }
                        case "EXCLUIR": {
                            int idSessao = Integer.parseInt(request.getParameter("idSessao"));
                            int idAmigo = Integer.parseInt(request.getParameter("idSolicitante"));
                            if(UserFacade.excluirAmizade(idSessao, idAmigo)){
                                RequestDispatcher rd = request.getRequestDispatcher("amizadeExcluida.jsp");
                                rd.forward(request, response);
                            }else{                               
                            }
                            break;
                        }
                        case "LISTARACEITOS": {
                            int idLogado = Integer.parseInt(request.getParameter("idUser"));
                            ArrayList<User> amigos = new ArrayList<>();
                            amigos = (ArrayList<User>) UserFacade.listaDeAmigos(idLogado);
                            session.setAttribute("amigosAceitos", amigos);
                            RequestDispatcher rd = request.getRequestDispatcher("listaAmizadeAceitos.jsp");
                            rd.forward(request, response);
                            break;
                        }
                        case "LISTARPEDIDOS": {
                            int idLogado = Integer.parseInt(request.getParameter("idUser"));
                            ArrayList<User> amigos = new ArrayList<>();
                            amigos = (ArrayList<User>) UserFacade.listaDeAmigosPendentes(idLogado);
                            session.setAttribute("amigosPendentes", amigos);
                            RequestDispatcher rd = request.getRequestDispatcher("listaAmizadePendente.jsp");
                            rd.forward(request, response);
                            break;
                        }
                        case "LISTARBLOQUEADOS": {
                            int idLogado = Integer.parseInt(request.getParameter("idUser"));
                            ArrayList<User> amigos = new ArrayList<>();
                            amigos = (ArrayList<User>) UserFacade.listaDeAmigosBloqueados(idLogado);
                            session.setAttribute("amigosBloqueados", amigos);
                            RequestDispatcher rd = request.getRequestDispatcher("listaAmizadeBloqueados.jsp");
                            rd.forward(request, response);
                            break;
                        }
                    }
                    break;
                }
                case "BUSCARAMIGOS": {

                    String tipo = request.getParameter("tipo");
                    switch (tipo) {
                        case "ACEITOS": {
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
