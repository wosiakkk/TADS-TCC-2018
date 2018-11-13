/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;


import br.com.tads.tccpool.beans.Privacidade;

import br.com.tads.tccpool.beans.Notificacao;

import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.dao.NotificacaoDAO;
import br.com.tads.tccpool.exception.AcessoBdException;
import br.com.tads.tccpool.facade.NotificacaoFacade;
import br.com.tads.tccpool.facade.UserFacade;
import br.com.tads.tccpool.utils.MD5;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                                request.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            //Validação de acesso
            if (session == null && !("ADD".equals(action))) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("title", "Inicio");
                request.setAttribute("msg", "Faça login para acessar esta página!");
                rd.forward(request, response);
            }
            String caminho = (String)session.getAttribute("caminho");
            User u = new User();
            switch (action) {
                case "ADD": {
                    u.setNome(request.getParameter("nome"));
                    u.setEmail(request.getParameter("email"));
                    u.setSenha(MD5.criptografar(request.getParameter("senha")));
                    try {
                        if (u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    } catch (NullPointerException ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                        String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                        response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                    }
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
                    try {
                        if (u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    } catch (NullPointerException ex) {
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
                            session.removeAttribute("mensagemAcao");
                            session.removeAttribute("mensagemAcaoTipo");
                            session.setAttribute("mensagemAcao", "O novo Moderador foi cadastrado!");
                            session.setAttribute("mensagemAcaoTipo", 2);
                            RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                            rd.forward(request, response);
                        }
                    } catch (AcessoBdException ex) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Erro ao cadastrar um novo moderador(AcessoBdException)!");
                        session.setAttribute("mensagemAcaoTipo", 2);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Erro ao cadastrar um novo moderador(SQLException)!");
                        session.setAttribute("mensagemAcaoTipo", 2);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    }
                    break;
                }
                case "ADDADM": {
                    u.setNome(request.getParameter("nome"));
                    u.setEmail(request.getParameter("email"));
                    u.setSenha(MD5.criptografar(request.getParameter("senha")));
                    u.setFoto("img\\fotosPerfil\\adm.png");
                    try {
                        if (u.getNome().isEmpty() || u.getEmail().isEmpty() || u.getSenha().isEmpty()) {
                            String param = URLEncoder.encode("Todos os campos de cadastro são obrigatórios. Por favor, tente novamente.");
                            response.sendRedirect("erro.jsp?title=Erro&msg=" + param);
                        }
                    } catch (NullPointerException ex) {
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
                            session.removeAttribute("mensagemAcao");
                            session.removeAttribute("mensagemAcaoTipo");
                            session.setAttribute("mensagemAcao", "O novo Administrador foi cadastrado!");
                            session.setAttribute("mensagemAcaoTipo", 3);
                            RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                            rd.forward(request, response);
                        }
                    } catch (AcessoBdException ex) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Erro ao cadastrar novo Administrador(AcessoBdException)!");
                        session.setAttribute("mensagemAcaoTipo", 3);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Erro ao cadastrar novo Administrador(SQLException)!");
                        session.setAttribute("mensagemAcaoTipo", 3);
                        RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                        rd.forward(request, response);
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
                                if (item.getFieldName().equals("codEndereco")) {
                                    alterar.setCdEndereco(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("nome")) {
                                    alterar.setNome(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("telefone")) {
                                    alterar.setTel(item.getString());
                                }
                                if (item.getFieldName().equals("celular")) {
                                    alterar.setCel(item.getString());
                                }
                                if (item.getFieldName().equals("logradouro")) {
                                    alterar.setLogradouro(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("numero")) {
                                    alterar.setNumero(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("complemento")) {
                                    alterar.setComplemento(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("cep")) {
                                    alterar.setCEP(item.getString());
                                }
                                if (item.getFieldName().equals("cidade")) {
                                    alterar.setCidade(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("estado")) {
                                    alterar.setEstado(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("descricao")) {
                                    alterar.setDescricao(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("interesses")) {
                                    alterar.setInteresses(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("fotoUser")) {
                                    if (!item.getString().equals("") && alterar.getFoto() == null) {
                                        alterar.setFoto(item.getString());
                                    }
                                }
                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(rand.nextInt()) + ".jpg";
                                if (!item.getName().equals("")) {
                                    item.write(new File(caminho + "img\\fotosPerfil" + File.separator + nomeString));
                                    caminhoFoto = "img\\fotosPerfil" + File.separator + nomeString;
                                    alterar.setFoto(caminhoFoto);
                                    javaxt.io.Image image = new javaxt.io.Image(caminho + "img\\fotosPerfil" + File.separator + nomeString);
                                    image.resize(200, 200);
                                    image.saveAs(caminho + "img\\fotosPerfil" + File.separator + nomeString);
                                    image.saveAs(request.getServletContext().getRealPath("img\\fotosPerfil") + File.separator + nomeString);
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
                            us.setNome(alterar.getNome());
                            session.setAttribute("user", us);
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

                case "PERFIL": {
                    User perfil = new User();
                    int id = Integer.parseInt(request.getParameter("idUser"));
                    int idSessao = (int) session.getAttribute("idUserSessao");
                    perfil = UserFacade.buscarUsuario(id);
                    int amizade = UserFacade.checandoAmizade(idSessao, id);
                    session.setAttribute("perfil", perfil);
                    session.setAttribute("amizade", amizade);
                    Privacidade pri = UserFacade.buscarPrivacidade(id);
                    request.setAttribute("privacidade", pri);
                    
                    RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
                    rd.forward(request, response);
                    break;
                }

                case "ALTSENHA": {
                    session.setAttribute("erroSenha", "");
                    String senhaAtual = request.getParameter("senhaAtual");
                    int id = (Integer) session.getAttribute("idUserSessao");
                    Boolean teste;
                    teste = UserFacade.verificaSenha(senhaAtual, id);
                    if (teste) {
                        String nsenha = request.getParameter("novaSenha");
                        if (UserFacade.alterarSenha(nsenha, id)) {
                            session.removeAttribute("mensagemAcao");
                            session.removeAttribute("mensagemAcaoTipo");
                            session.setAttribute("mensagemAcao", "Sua senha foi atualizada!");
                            session.setAttribute("mensagemAcaoTipo", 4);
                            RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                            rd.forward(request, response);
                        } else {
                            session.removeAttribute("mensagemAcao");
                            session.removeAttribute("mensagemAcaoTipo");
                            session.setAttribute("mensagemAcao", "Erro ao alterar a sua senha!");
                            session.setAttribute("mensagemAcaoTipo", 4);
                            RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                            rd.forward(request, response);
                        }
                    } else {
                        session.setAttribute("erroSenha", "Senha atual incorreta");
                        RequestDispatcher rd = request.getRequestDispatcher("alterarSenha.jsp");
                        rd.forward(request, response);
                    }
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
                                    try {
                                        //notificaÃ§Ã£o
                                        NotificacaoFacade.inserirnotificacao(idSolicitante, idSolicitado, 1);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    session.removeAttribute("mensagemAcao");
                                    session.removeAttribute("mensagemAcaoTipo");
                                    session.setAttribute("mensagemAcao", "Seu pedido de amizade foi enviado!");
                                    session.setAttribute("mensagemAcaoTipo", 1);
                                    RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                    rd.forward(request, response);
                                } else {
                                    //erro na tabela do solictado
                                    session.removeAttribute("mensagemAcao");
                                    session.removeAttribute("mensagemAcaoTipo");
                                    session.setAttribute("mensagemAcao", "Erro ao enviar pedido de amizade!(tabela do solictado)");
                                    session.setAttribute("mensagemAcaoTipo", 1);
                                    RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                    rd.forward(request, response);
                                }
                            } else {
                                //erro na tabela do solicitante
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao enviar pedido de amizade!(tabela do solicitante)");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            }
                            break;
                        }
                        case "ACEITAR": {
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            int idSolicitado = Integer.parseInt(request.getParameter("idSolicitado"));
                            if (UserFacade.aceitarAmizade(idSolicitante, idSolicitado)) {
                                try {
                                    NotificacaoFacade.inserirnotificacao(idSolicitante, idSolicitado, 2);
                                } catch (SQLException ex) {
                                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Sua amizade foi aceita!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            } else {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao aceitar a amizade!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            }

                            break;
                        }
                        case "REJEITAR": {
                            int idSessaoSolicitado = Integer.parseInt(request.getParameter("idSessao"));
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            if (UserFacade.rejeitarAmizade(idSessaoSolicitado, idSolicitante)) {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "O pedido de amizade foi rejeitado!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            } else {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao rejeitar amizade!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            }
                            break;
                        }
                        case "REJEITAREBLOQUEAR": {
                            int idSessaoSolicitado = Integer.parseInt(request.getParameter("idSessao"));
                            int idSolicitante = Integer.parseInt(request.getParameter("idSolicitante"));
                            if (UserFacade.rejeitarBloquear(idSessaoSolicitado, idSolicitante)) {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "O usuário foi bloqueado!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            } else {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao bloquear usuário!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            }
                            break;
                        }
                        case "DESBLOQUEAR": {
                            int idSessao = Integer.parseInt(request.getParameter("idSessao"));
                            int idDesbloqueio = Integer.parseInt(request.getParameter("idSolicitante"));
                            if (UserFacade.desbloquearUsuario(idSessao, idDesbloqueio)) {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "O usuário foi desbloqueado!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            } else {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao desbloquear o usuário!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            }
                            break;
                        }
                        case "EXCLUIR": {
                            int idSessao = Integer.parseInt(request.getParameter("idSessao"));
                            int idAmigo = Integer.parseInt(request.getParameter("idSolicitante"));
                            if (UserFacade.excluirAmizade(idSessao, idAmigo)) {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "O usuário foi excluido!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
                            } else {
                                session.removeAttribute("mensagemAcao");
                                session.removeAttribute("mensagemAcaoTipo");
                                session.setAttribute("mensagemAcao", "Erro ao excluir usuário!");
                                session.setAttribute("mensagemAcaoTipo", 1);
                                RequestDispatcher rd = request.getRequestDispatcher("infoAcao.jsp");
                                rd.forward(request, response);
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

                case "EDITARPRIVACIDADE":
                    try{
                        Privacidade p = new Privacidade();
                        Privacidade pret = new Privacidade();
                        int telefone = Integer.parseInt(request.getParameter("radioTelefone"));
                        int endereco = Integer.parseInt(request.getParameter("radioEndereco"));
                        int descricao = Integer.parseInt(request.getParameter("radioDescricao"));
                        int interesses = Integer.parseInt(request.getParameter("radioInteresses"));
                        int idUser = Integer.parseInt(request.getParameter("idUser"));
                        if(request.getParameter("privacidadeId") != null && !request.getParameter("privacidadeId").equals("")){
                            int idPrivacidade = Integer.parseInt(request.getParameter("privacidadeId"));
                            p.setId(idPrivacidade);
                        }
                        p.setIdUser(idUser);
                        p.setPrivacidadeTelefone(telefone);
                        p.setPrivacidadeEndereco(endereco);
                        p.setPrivacidadeDescricao(descricao);
                        p.setPrivacidadeInteresses(interesses);
                        boolean ret = UserFacade.editarPrivacidade(p);
                        if(ret){
                            session.setAttribute("privacidadeMensagem", "Informações alteradas com sucesso.");
                        }else{
                            session.setAttribute("privacidadeMensagem", "Erro ao alterar as informações, tente novamente.");
                        }
                        pret = UserFacade.buscarPrivacidade(idUser);
                        session.setAttribute("privacidade", pret);
                        response.sendRedirect("editarPrivacidade.jsp");
                    }catch(Exception e){
                       String erro =  e.toString();
                    }
                    
                case "PRIVACIDADE":
                    try{
                        int idUser = 0;
                        Privacidade pret = new Privacidade();
                        if(request.getParameter("idUser") != null && !request.getParameter("idUser").equals("")){
                         idUser = Integer.parseInt(request.getParameter("idUser"));
                        }
                        if(idUser != 0){
                        pret = UserFacade.buscarPrivacidade(idUser);
                        request.setAttribute("privacidade", pret);
                        }else{
                            request.setAttribute("privacidadeMensagem", "Erro ao buscar dados de privacidade, tente novamente.");
                        }
                        request.getRequestDispatcher("editarPrivacidade.jsp").forward(request, response);
                    }catch(Exception e){
                        String erro = e.toString();
                    }
                    break;
                
            }
                    
                    
                    
                    
                  /*  int idAjax = Integer.parseInt(request.getParameter("idAjax"));
                    List<Notificacao> listNoti;
                try {
                    listNoti = NotificacaoFacade.buscar(idAjax);
                     session.setAttribute("notificaca", listNoti);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                   
                    
                

            
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
