/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Notificacao;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.facade.NotificacaoFacade;
import br.com.tads.tccpool.facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
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
@WebServlet(name = "NotificacaoServlet", urlPatterns = {"/NotificacaoServlet"})
public class NotificacaoServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            int idUsr = 0;
            if (!(action.equalsIgnoreCase("EXCLUIR") || action.equalsIgnoreCase("EXCLUIRTODAS"))) {
                idUsr = Integer.parseInt(request.getParameter("idAjax"));
            }

            switch (action) {
                //conta o numero de notificações novas para exibir no contador
                case "CONTAGEM": {
                    List<Notificacao> listNoti;
                    try {
                        listNoti = NotificacaoFacade.buscarNovas(idUsr);
                        int numNotificacoes = listNoti.size();
                        if (numNotificacoes > 0) {
                            response.setContentType("text/plain");
                            response.setCharacterEncoding("UTF-8");
                            String resp = "<span id=\"notification_count\">" + numNotificacoes + "</span>";
                            response.getWriter().write(resp);
                        }
                        //*  session.setAttribute("notificaca", listNoti);
                    } catch (SQLException ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                //muda o status das notifiações para lido ao exbir o popup com a lista
                case "LER": {
                    NotificacaoFacade.lerNotificacao(idUsr);
                    break;
                }
                //lista as notificações e envia para ser exibida no popup de notificação
                case "LISTAR": {

                    String retorno = "";
                    List<Notificacao> todas;
                    todas = NotificacaoFacade.buscar(idUsr);
                    if (todas.size() > 0) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        for (Notificacao n : todas) {
                            String descNoti = "";
                            String link = "";
                            if (n.getTipoNot() == 1) {
                                descNoti = "Você recebeu um pedeido de amizade de: ";
                                link = "UserServlet?action=AMIZADE&acao=LISTARPEDIDOS&idUser=" + idUsr + "";
                            } else if (n.getTipoNot() == 2) {
                                descNoti = "Seu pedido de amizade foi aceito por: ";
                                link = "UserServlet?action=AMIZADE&acao=LISTARACEITOS&idUser=" + idUsr + "";
                            } else if (n.getTipoNot() == 3) {
                                descNoti = "Seu anúncio recebeu um comentário de: ";
                                link = "AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr="+idUsr +"&status=2";
                            }else if (n.getTipoNot() == 4) {
                                descNoti = "O anúncio que você estava seguindo foi vendido por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 5) {
                                descNoti = "O seu Anúncio foi aprovado por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 6) {
                                descNoti = "O seu Anúncio foi rejeitado por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 7) {
                                descNoti = "Você recebeu uma mensagem de: ";
                                link = "mensagem.jsp";
                            }
                            User u = new User();
                            u = UserFacade.buscarUsuario(n.getIdGerador());
                            //retorno += "<span>Pedido de amizade de "+u.getNome()+"</span> <br> ";
                            retorno += "<div class=\"card\">  "
                                    + "<div class=\"card-body\">\n"
                                    + " <div class=\"d-inline-block\">\n"
                                    + " <img src=\"" + u.getFoto() + "\"  class=\"img-thumbnail\" width=\"50\" height=\"50\">\n"
                                    + " </div>\n"
                                    + " <div class=\"d-inline-block\">\n"
                                    + "  <h5 class=\"card-title center\">" + descNoti + u.getNome() + "</h5> <a id=\"linkNoti\" href=" + link + ">Ver</a>"
                                    + "</div>"
                                    + "<div> <small><span>" + format.format(n.getData().getTime()) + "</span></small></div>"
                                    + "</div>"
                                    + "</div>";
                        }
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(retorno);
                    }
                    break;
                }
                //case para listar todas as notificações na página de notificações
                case "LISTARPAGINA": {
                    String retorno = "";
                    List<Notificacao> listaN;
                    listaN = NotificacaoFacade.buscar(idUsr);
                    if (listaN.size() > 0) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        for (Notificacao n : listaN) {
                            String descNoti = "";
                            String link = "";
                            if (n.getTipoNot() == 1) {
                                descNoti = "Você recebeu um pedeido de amizade de: ";
                                link = "UserServlet?action=AMIZADE&acao=LISTARPEDIDOS&idUser=" + idUsr + "";
                            } else if (n.getTipoNot() == 2) {
                                descNoti = "Seu pedido de amizade foi aceito por: ";
                            }else if (n.getTipoNot() == 3) {
                                descNoti = "Seu anúncio recebeu um comentário de: ";
                                link = "AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr="+idUsr +"&status=2";
                            }else if (n.getTipoNot() == 4) {
                                descNoti = "O anúncio que você estava seguindo foi vendido por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 5) {
                                descNoti = "O seu Anúncio foi aprovado por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 6) {
                                descNoti = "O seu Anúncio foi rejeitado por: ";
                                link = "escolhaMeusAnuncios.jsp";
                            }else if (n.getTipoNot() == 7) {
                                descNoti = "Você recebeu uma mensagem de: ";
                                link = "mensagem.jsp";
                            }
                            User u = new User();
                            u = UserFacade.buscarUsuario(n.getIdGerador());
                            //retorno += "<span>Pedido de amizade de "+u.getNome()+"</span> <br> ";
                            retorno += "<div class=\"card\">  "
                                    + "<div class=\"card-body\">\n"
                                    + " <div class=\"d-inline-block\">\n"
                                    + " <img src=\"" + u.getFoto() + "\"  class=\"img-thumbnail\" width=\"50\" height=\"50\">\n"
                                    + " </div>\n"
                                    + " <div class=\"d-inline-block\">\n"
                                    + "  <h5 class=\"card-title center\">" + descNoti + u.getNome() + "</h5> "    
                                    + "</div>"
                                    + "<div>"
                                    + "<small><span>" + format.format(n.getData().getTime()) + "</span></small>"
                                    + "</div>"
                                    + "<form action=\"NotificacaoServlet?action=EXCLUIR\"  method=\"POST\" role=\"form\"><input name=\"idNoti\" value=" + n.getId() + " hidden>"
                                    + "<hr><button class=\"btn btn-outline-dark\" type=\"submit\">Excluir</button></form><hr>"
                                    + "</div>"                                  
                                    + "</div>";
                        }
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(retorno);
                    }

                    break;
                }

                case "EXCLUIR": {
                    int idNoti = Integer.parseInt(request.getParameter("idNoti"));
                    try {
                        NotificacaoFacade.excluir(idNoti);
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "A sua notificação foi excluída!");
                        session.setAttribute("mensagemAcaoTipo", 10);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Problemas ao excluir sua notificação!");
                        session.setAttribute("mensagemAcaoTipo", 10);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                
                case "EXCLUIRTODAS": {
                    int idSessao = Integer.parseInt(session.getAttribute("idUserSessao").toString());
                    try{
                        NotificacaoFacade.excluirTodas(idSessao);
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Todas as suas notificações foram excluídas!");
                        session.setAttribute("mensagemAcaoTipo", 10);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    }catch(SQLException ex){
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Erro ao excluir todas as notificações!");
                        session.setAttribute("mensagemAcaoTipo", 10);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NotificacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
