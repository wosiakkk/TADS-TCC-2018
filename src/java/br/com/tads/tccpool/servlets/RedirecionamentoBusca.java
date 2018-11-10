/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Privacidade;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author onurb
 */
@WebServlet(name = "RedirecionamentoBusca", urlPatterns = {"/RedirecionamentoBusca"})
public class RedirecionamentoBusca extends HttpServlet {

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
            //Validação de acesso
            HttpSession session = request.getSession();
            if(session == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("title", "Inicio");
                request.setAttribute("msg", "Faça login para acessar esta página!");
                rd.forward(request, response);
            }
            
            //Tratamento para parâmetro vazio
            int idSearch = Integer.parseInt(
                    "".equals((String)request.getParameter("idSearch")) ? "0" : request.getParameter("idSearch")
            );
            
            if(idSearch > 0) {
                int idSessao =(int) session.getAttribute("idUserSessao");
                //Recupera o perfil do uusário buscado
                User perfilBusca = new User();
                perfilBusca = UserFacade.buscarUsuario(idSearch);
                
                //Verifica amizade entre usuário buscado e usuário logado
                int amizade = UserFacade.checandoAmizade(idSessao, idSearch);
                
                //Grava os dados na sessão
                session.setAttribute("perfil", perfilBusca);
                session.setAttribute("amizade", amizade);
                
                //Verifica preferências de privacidade do usuário buscado e grava na resquisição
                Privacidade pri = UserFacade.buscarPrivacidade(idSearch);
                request.setAttribute("privacidade", pri);
                
                //Redireciona para página de perfil
                RequestDispatcher rd1 = request.getRequestDispatcher("perfil.jsp");
                rd1.forward(request, response);
            }
            else {
                RequestDispatcher rd1 = request.getRequestDispatcher("home.jsp");
                request.setAttribute("msg", "Nenhum usuário para exibir.");
                rd1.forward(request, response);
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
            Logger.getLogger(RedirecionamentoBusca.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RedirecionamentoBusca.class.getName()).log(Level.SEVERE, null, ex);
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
