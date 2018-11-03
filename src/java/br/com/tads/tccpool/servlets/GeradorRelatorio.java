package br.com.tads.tccpool.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package br.com.tads.tccpool.beans;

import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.utils.ConnectionFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "GeradorRelatorio", urlPatterns = {"/GeradorRelatorio"})
public class GeradorRelatorio extends HttpServlet {

   protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {   
            
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Connection con = null;
        User u = (User)session.getAttribute("user");
        String idSessao = String.valueOf(u.getId());
                try {
            
         
          ConnectionFactory cf = new ConnectionFactory();
           con = cf.getConnection();
              
            String jasper = request.getContextPath()+ "/jasper/" + action + ".jasper";
            
            
            String host = "http://" + request.getServerName() + ":" + request.getServerPort();

            URL jasperURL = new URL(host + jasper);

            HashMap params = new HashMap();
            params.put("idSessao", idSessao);
             
            byte[] bytes = JasperRunManager.runReportToPdf(
                    jasperURL.openStream(), params, con);

            if (bytes != null) {

                response.setContentType("application/pdf");
                
                OutputStream ops = response.getOutputStream();
                ops.write(bytes);}
            
            
            
            
        } catch (JRException e) {
            request.setAttribute("mensagem", "Erro no jasper : " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(GeradorRelatorio.class.getName()).log(Level.SEVERE, null, e);
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
    }
}