/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Comentario;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.facade.ComentarioFacade;
import br.com.tads.tccpool.facade.NotificacaoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcos
 */
@WebServlet(name = "ComentarioServlet", urlPatterns = {"/ComentarioServlet"})
public class ComentarioServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            //Validação de acesso
            if(session == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("title", "Inicio");
                request.setAttribute("msg", "Faça login para acessar esta página!");
                rd.forward(request, response);
            }
            
            //Salva usuário logado para verificações
            User userLogado = (User) session.getAttribute("user");
            
            String action = (String) request.getParameter("action").trim();
            int idComentario = 0;
            
            switch(action) {
                case "ADD":
                    String msg = (String) request.getParameter("DS_MSG").trim();
                    int idUsario = Integer.parseInt(request.getParameter("ID_USUARIO"));
                    int idAnuncio = Integer.parseInt(request.getParameter("ID_ANUNCIO"));
                    Comentario comentario = new Comentario();
                    comentario.setIdAnuncio(idAnuncio);
                    comentario.setIdOrigem(idUsario);
                    comentario.setConteudo(msg);
                    try{
                        int idReply = Integer.parseInt(request.getParameter("ID_REPLY"));
                        comentario.setIdPai(idReply);
                    } catch(NullPointerException | NumberFormatException ex) {
                        Logger.getLogger(ComentarioFacade.class.getName()).log(Level.INFO, null, ex);
                    }
                                                     
                    String retorno = ComentarioFacade.insereComentario(comentario);
                    
                    //notificação
                    int idUsrAnuncio = NotificacaoFacade.buscarIdUsrAnun(idAnuncio);
                    NotificacaoFacade.inserirnotificacao(idUsario, idUsrAnuncio, 3);
                    
                    
                    //Escreve o retorno da execução na resposta da requisição e limpa o stream
                    out.write(retorno);
                    out.flush();
                    break;
                    
                case "LIST_COMENTARIOS":
                    String comentarios;
                    int anuncio = Integer.parseInt(request.getParameter("anuncio"));
                    
                    //Se o tipo de usuário for diferente de 2 isManager recebe true, senão recebe false
                    boolean isManager = (userLogado.getTipoUsuario() != 2);
                    comentarios = ComentarioFacade.listarComentarios(anuncio, isManager);
                    out.write(comentarios);
                    out.flush();
                    break;
                    
                case "LIKE_COMENTARIO":
                case "UNLIKE_COMENTARIO":
                    idComentario = Integer.parseInt(request.getParameter("comentario"));
                    if("LIKE_COMENTARIO".equals(action)) {
                        ComentarioFacade.setLike(idComentario);
                    }
                    else if("UNLIKE_COMENTARIO".equals(action)) {
                        ComentarioFacade.setUnlike(idComentario);
                    }
                    break;
                
                case "EXCLUIR_COMENTARIO":
                    idComentario = Integer.parseInt(request.getParameter("comentario"));
                    ComentarioFacade.excluirComentario(idComentario);
                    break;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
