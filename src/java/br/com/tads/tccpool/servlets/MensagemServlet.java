/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Mensagem;
import br.com.tads.tccpool.facade.MensagemFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marcos
 */
@WebServlet(name = "MensagemServlet", urlPatterns = {"/MensagemServlet"})
public class MensagemServlet extends HttpServlet {

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
            String action = (String) request.getParameter("action").trim();
            
            switch(action) {
                case "ADD_MENSAGEM":
                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdOrigem(Integer.parseInt(request.getParameter("ID_ORIGEM")));
                    mensagem.setIdDestino(Integer.parseInt(request.getParameter("ID_DESTINO")));
                    mensagem.setConteudo((String) request.getParameter("DS_MSG"));
                    
                    String retornoInserir = MensagemFacade.inserir(mensagem);
                    
                    out.write(retornoInserir);
                    out.flush();                    
                    break;
                    
                case "LIST_MENSAGEM":
                    int idOrigem = Integer.parseInt(request.getParameter("ID_ORIGEM"));
                    int idDestino = Integer.parseInt(request.getParameter("ID_DESTINO"));
                    String retornoListar = MensagemFacade.listar(idOrigem, idDestino);
                    
                    out.write(retornoListar);
                    out.flush();
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
