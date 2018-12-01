/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Anuncio;
import br.com.tads.tccpool.beans.Categoria;
import br.com.tads.tccpool.beans.FiltroAnuncio;
import br.com.tads.tccpool.beans.Foto;
import br.com.tads.tccpool.beans.Imovel;
import br.com.tads.tccpool.beans.Material;
import br.com.tads.tccpool.beans.Movel;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.exception.AcessoBdException;
import br.com.tads.tccpool.facade.AnuncioFacade;
import br.com.tads.tccpool.facade.MainPageFacade;
import br.com.tads.tccpool.facade.NotificacaoFacade;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AnuncioServlet", urlPatterns = {"/AnuncioServlet"})
public class AnuncioServlet extends HttpServlet {

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
            throws ServletException, IOException, AcessoBdException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            //Validação de acesso
            if (session == null && !("EXIBIRANUNCIO".equals(action))) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                request.setAttribute("title", "Inicio");
                request.setAttribute("msg", "Faça login para acessar esta página!");
                rd.forward(request, response);
            }
            RequestDispatcher rd;
String caminho = (String)session.getAttribute("caminho");
            switch (action) {
                case "ADDIMV":
                    Imovel im = new Imovel();
                    Anuncio a = new Anuncio();
                    int cat = 1;
                    String caminhoImovel = new String();
                    List<String> listImovel = new ArrayList<String>();
                    try {
                        int i = 0;
                        /*Faz o parse do request*/
                        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        int nome;// = new String();
                        /*Escreve a o arquivo na pasta img*/
                        for (FileItem item : multiparts) {
                            if (item.isFormField()) {
                                if (item.getFieldName().equals("catImovel")) {
                                    im.setTipo(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("titulo")) {
                                    im.setTitulo(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("descricao")) {
                                    im.setDescricao(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("quantidade")) {
                                    im.setQuantidade_pessoas(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("optpet")) {
                                    im.setBoolean_pet(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("valor")) {
                                    im.setPreco(Float.parseFloat(item.getString()));
                                }
                                if (item.getFieldName().equals("rua")) {
                                    im.setRua(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("num")) {
                                    im.setNumero(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("cep")) {
                                    im.setCep(item.getString());
                                }
                                if (item.getFieldName().equals("cidade")) {
                                    im.setCidade(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("estado")) {
                                    im.setEstado(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item.getFieldName().equals("comple")) {
                                    im.setComplemento(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }

                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(rand.nextInt()) + ".jpg";
                                if (!item.getName().equals("")) {
                                    item.write(new File(caminho + "img" + File.separator + nomeString));
                                    caminhoImovel = "img" + File.separator + nomeString;
                                    listImovel.add(caminhoImovel);
                                    javaxt.io.Image image = new javaxt.io.Image(caminho + "img" + File.separator + nomeString);
                                    image.resize(850, 500);
                                    image.saveAs(caminho + "img" + File.separator + nomeString);
                                }
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        User u = (User) session.getAttribute("user");
                        AnuncioFacade.insereImovel(im, cat, listImovel, u);
                        request.setAttribute("msg", "Anuncio Realizao Com Sucesso");
                        //List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                        //session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("msg", "Falha ao Realizar Anuncio: " + ex);
                    } finally {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "O Imovel foi cadastrado para avaliação!");
                        session.setAttribute("mensagemAcaoTipo", 5);
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    }
                    break;
                case "ADDMOVEL":
                    Movel movel = new Movel();
                    Anuncio an = new Anuncio();
                    int cate = 2;
                    String caminhomovel = new String();
                    List<String> lista = new ArrayList<String>();
                    try {
                        int i = 0;
                        /*Faz o parse do request*/
                        List<FileItem> multiparts2 = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        int nome;
                        for (FileItem item2 : multiparts2) {
                            if (item2.isFormField()) {
                                if (item2.getFieldName().equals("titulo")) {
                                    movel.setTitulo(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("descricao")) {
                                    movel.setDescricao(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("valor")) {
                                    movel.setPreco(Float.parseFloat(item2.getString()));
                                }
                                if (item2.getFieldName().equals("select")) {
                                    movel.setTipo(Integer.parseInt(item2.getString()));
                                }
                                if (item2.getFieldName().equals("rua")) {
                                    movel.setRua(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("num")) {
                                    movel.setNumero(Integer.parseInt(item2.getString()));
                                }
                                if (item2.getFieldName().equals("cep")) {
                                    movel.setCep(item2.getString());
                                }
                                if (item2.getFieldName().equals("cidade")) {
                                    movel.setCidade(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("estado")) {
                                    movel.setEstado(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("comple")) {
                                    movel.setComplemento(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(-1 * (rand.nextInt())) + ".jpg";
                                if (!item2.getName().equals("")) {
                                    item2.write(new File(caminho + "img" + File.separator + nomeString));
                                    caminhomovel = "img" + File.separator + nomeString;
                                    lista.add(caminhomovel);
                                    javaxt.io.Image image = new javaxt.io.Image(caminho + "img" + File.separator + nomeString);
                                    image.resize(850, 500);
                                    image.saveAs(caminho + "img" + File.separator + nomeString);
                                }
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        User u = (User) session.getAttribute("user");
                        AnuncioFacade.insereMovel(movel, cate, lista, u);
                        //List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                        //session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "O Movel foi cadastrado para avaliação!");
                        session.setAttribute("mensagemAcaoTipo", 6);
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    }
                    break;

                case "ADDMATERIAL":
                    Material material = new Material();
                    Anuncio anu = new Anuncio();
                    int categ = 3;
                    String caminhomaterial = new String();
                    List<String> listaMaterial = new ArrayList<String>();
                    try {
                        int i = 0;
                        /*Faz o parse do request*/
                        List<FileItem> multiparts2 = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        int nome;
                        for (FileItem item2 : multiparts2) {
                            if (item2.isFormField()) {
                                if (item2.getFieldName().equals("titulo")) {
                                    material.setTitulo(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("descricao")) {
                                    material.setDescricao(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("valor")) {
                                    material.setPreco(Float.parseFloat(item2.getString()));
                                }
                                if (item2.getFieldName().equals("select")) {
                                    material.setTipo(Integer.parseInt(item2.getString()));
                                }
                                if (item2.getFieldName().equals("rua")) {
                                    material.setRua(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("num")) {
                                    material.setNumero(Integer.parseInt(item2.getString()));
                                }
                                if (item2.getFieldName().equals("cep")) {
                                    material.setCep(item2.getString());
                                }
                                if (item2.getFieldName().equals("cidade")) {
                                    material.setCidade(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("estado")) {
                                    material.setEstado(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                                if (item2.getFieldName().equals("comple")) {
                                    material.setComplemento(new String(item2.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                                }
                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(-1 * (rand.nextInt())) + ".jpg";
                                if (!item2.getName().equals("")) {
                                    item2.write(new File(caminho + "img" + File.separator + nomeString));
                                    caminhomaterial = "img" + File.separator + nomeString;
                                    listaMaterial.add(caminhomaterial);
                                    javaxt.io.Image image = new javaxt.io.Image(caminho + "img" + File.separator + nomeString);
                                    image.resize(850, 500);
                                    image.saveAs(caminho + "img" + File.separator + nomeString);
                                }
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        User u = (User) session.getAttribute("user");
                        AnuncioFacade.insereMaterial(material, categ, listaMaterial, u);
                        //List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                        //session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "O Material foi cadastrado para avaliação!");
                        session.setAttribute("mensagemAcaoTipo", 7);
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    }
                    break;
                case "BUSCARIMOVEISPEND":
                    List<Imovel> listaImovel = new ArrayList<Imovel>();
                    listaImovel = AnuncioFacade.buscarPendente();
                    session.setAttribute("listaImovel", listaImovel);
                    rd = request.getRequestDispatcher("aprovarAnuncio.jsp");
                    rd.forward(request, response);
                    break;

                case "BUSCARMOVEISPEND":
                    List<Movel> listaMovelPendentes = new ArrayList<Movel>();
                    listaMovelPendentes = AnuncioFacade.buscarPendenteMovel();
                    session.setAttribute("listaMovelPendente", listaMovelPendentes);
                    rd = request.getRequestDispatcher("aprovarAnuncioMovel.jsp");
                    rd.forward(request, response);
                    break;
                case "BUSCARMATERIAISPEND":
                    List<Material> listaMatPendentes = new ArrayList<Material>();
                    listaMatPendentes = AnuncioFacade.buscarPendenteMaterial();
                    session.setAttribute("listaMatPendente", listaMatPendentes);
                    rd = request.getRequestDispatcher("aprovarAnuncioMat.jsp");
                    rd.forward(request, response);
                    break;

                case "BUSCARPORID":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Imovel imovel = new Imovel();
                    imovel = AnuncioFacade.buscarImovelPorId(id);
                    session.setAttribute("imovelBuscado", imovel);
                    request.setAttribute("idAnuncio", id);
                    rd = request.getRequestDispatcher("imoveisPendentes.jsp");
                    rd.forward(request, response);
                    break;

                case "BUSCARPORIDMOVEL":
                    int idM = Integer.parseInt(request.getParameter("id"));
                    Movel m1 = new Movel();
                    m1 = AnuncioFacade.buscarMovelPorId(idM);
                    session.setAttribute("movelBuscado", m1);
                    request.setAttribute("idAnuncio", idM);
                    rd = request.getRequestDispatcher("moveisPendentes.jsp");
                    rd.forward(request, response);
                    break;

                case "BUSCARPORIDMAT":
                    int idMat = Integer.parseInt(request.getParameter("id"));
                    Material mt = new Material();
                    mt = AnuncioFacade.buscarMaterialPorId(idMat);
                    session.setAttribute("matBuscado", mt);
                    request.setAttribute("idAnuncio", idMat);
                    rd = request.getRequestDispatcher("materiaisPendentes.jsp");
                    rd.forward(request, response);
                    break;

                case "APROVAR":
                    int idImv = Integer.parseInt(request.getParameter("id"));
                    String status = request.getParameter("optradio");
                    AnuncioFacade.alterarStatus(status, idImv);
                    if (status.equalsIgnoreCase("sim")) {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "O anúncio foi aprovado!");
                        session.setAttribute("mensagemAcaoTipo", 8);
                        
                        // notificação
                        int anunciante = AnuncioFacade.retornoIdAunciante(idImv);
                        NotificacaoFacade.inserirnotificacao(1, anunciante, 5);
                        
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    } else {
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "O anúncio foi rejeitado!");
                        session.setAttribute("mensagemAcaoTipo", 8);
                        
                        // notificação
                        int anunciante = AnuncioFacade.retornoIdAunciante(idImv);
                        NotificacaoFacade.inserirnotificacao(1, anunciante, 6);
                        
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    }
                    break;

                case "BUSCAAPROVADOS":
                    String HTMLResponse = AnuncioFacade.buscarAnuncioAprovado(new FiltroAnuncio());
                    if (HTMLResponse != null) {
                        out.write(HTMLResponse);
                    } else {
                        out.write("Falha ao buscar an&uacute;ncios. Tente novamente mais tarde.");
                    }
                    break;

                case "BUSCAANUNCIOUSER":
                    try {
                        String nomeAnunciante = new String();
                        if(request.getParameter("nome") != null && !request.getParameter("nome").equals("")){
                            nomeAnunciante = request.getParameter("nome");
                        }
                        List<Categoria> listaCategoriaImovel = new ArrayList<Categoria>();
                        listaCategoriaImovel = MainPageFacade.listaCategoriasImovel();
                        session.setAttribute("listaCatImovel", listaCategoriaImovel);
                        List<Categoria> listaCategoriaMovel = new ArrayList<Categoria>();
                        listaCategoriaMovel = MainPageFacade.listaCategoriasMovel();
                        session.setAttribute("listaCatMovel", listaCategoriaMovel);
                        List<Categoria> listaCategoriaMaterial = new ArrayList<Categoria>();
                        listaCategoriaMaterial = MainPageFacade.listaCategoriasMaterial();
                        session.setAttribute("listaCatMaterial", listaCategoriaMaterial);
                        int idUser = Integer.parseInt(request.getParameter("idUsr"));
                        int statusAnuncio = Integer.parseInt(request.getParameter("status"));
                        List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(idUser, statusAnuncio);
                        session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                        User u = (User)session.getAttribute("user");
                        if(u.getId() != idUser){
                            request.setAttribute("nomeAnunciante", nomeAnunciante);
                        }
                        rd = request.getRequestDispatcher("resumo.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;
                    
                case "BUSCARANUNCIOSEGUIDOS":
                    
                    List<Integer> listaIdsAnuncios = new ArrayList<Integer>();
                    int idS = Integer.parseInt(session.getAttribute("idUserSessao").toString());
                    listaIdsAnuncios = AnuncioFacade.buscarIdsAnunciosSeguidos(idS);                  
                    List<Anuncio> listaAnuciosSeguidos = new ArrayList<Anuncio>();
                    for(int idLista : listaIdsAnuncios){
                       listaAnuciosSeguidos.add(AnuncioFacade.resumoAnuncios(idLista));
                    }
                    session.setAttribute("listaResumoAnuncios", listaAnuciosSeguidos);
                    rd = request.getRequestDispatcher("anunciosSeguidos.jsp");
                        rd.forward(request, response);
                    break;

                case "EXIBIRANUNCIO":
                    int idAnuncio = Integer.parseInt(request.getParameter("idAnuncio"));
                    int t = AnuncioFacade.verifcaTipoAnuncio(idAnuncio);
                    if (t == 1) {
                        Imovel i = new Imovel();
                        i = AnuncioFacade.buscarImovelPorId(idAnuncio);
                        session.setAttribute("imovelExibir", i);
                        session.setAttribute("idExibirAnuncio", idAnuncio);
                        session.removeAttribute("imovelAlterar");
                        session.removeAttribute("movelAlterar");
                        session.removeAttribute("materialAlterar");
                        session.removeAttribute("movelExibir");
                        session.removeAttribute("materialExibir");
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                    } else if (t == 2) {
                        Movel m = new Movel();
                        m = AnuncioFacade.buscarMovelPorId(idAnuncio);
                        session.setAttribute("movelExibir", m);
                        session.setAttribute("idExibirAnuncio", idAnuncio);
                        session.removeAttribute("movelAlterar");
                        session.removeAttribute("imovelAlterar");
                        session.removeAttribute("materialAlterar");
                        session.removeAttribute("imovelExibir");
                        session.removeAttribute("materialExibir");
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                    } else if (t == 3) {
                        Material mat = new Material();
                        mat = AnuncioFacade.buscarMaterialPorId(idAnuncio);
                        session.setAttribute("materialExibir", mat);
                        session.setAttribute("idExibirAnuncio", idAnuncio);
                        session.removeAttribute("movelAlterar");
                        session.removeAttribute("imovelAlterar");
                        session.removeAttribute("materialAlterar");
                        session.removeAttribute("imovelExibir");
                        session.removeAttribute("movelExibir");
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                    }
                    break;

                case "EXCLUIRANUNCIO":
                    try {
                        if (session.getAttribute("imovelExibir") != null) {
                            int idAnuncioExcluir = (int) session.getAttribute("idExibirAnuncio");
                            Imovel ImovelExcluir = (Imovel) session.getAttribute("imovelExibir");
                            AnuncioFacade.deletarAnuncioImovel(idAnuncioExcluir, ImovelExcluir);
                            User u = (User) session.getAttribute("user");
                            List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId(), 1);
                            session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                        } else if (session.getAttribute("movelExibir") != null) {
                            int idAnuncioExcluir = (int) session.getAttribute("idExibirAnuncio");
                            Movel MovelExcluir = (Movel) session.getAttribute("movelExibir");
                            AnuncioFacade.deletarAnuncioMovel(idAnuncioExcluir, MovelExcluir);
                            User u = (User) session.getAttribute("user");
                            List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId(), 1);
                            session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                        } else if (session.getAttribute("materialExibir") != null) {
                            int idAnuncioExcluir = (int) session.getAttribute("idExibirAnuncio");
                            Material materialExcluir = (Material) session.getAttribute("materialExibir");
                            AnuncioFacade.deletarAnuncioMaterial(idAnuncioExcluir, materialExcluir);
                            User u = (User) session.getAttribute("user");
                            List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId(), 1);
                            session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                        }
                        rd = request.getRequestDispatcher("resumo.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;

                case "ALTERARANUNCIO":
                    try {
                        if (session.getAttribute("imovelExibir") != null) {
                            int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                            Imovel ImovelAlterar = (Imovel) session.getAttribute("imovelExibir");
                            session.setAttribute("imovelAlterar", ImovelAlterar);
                            session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                            session.removeAttribute("imovelExibir");
                            List<Categoria> listaCategoriaImovel = new ArrayList<Categoria>();
                            listaCategoriaImovel = MainPageFacade.listaCategoriasImovel();
                            session.setAttribute("listaCatImovel", listaCategoriaImovel);
                        } else if (session.getAttribute("movelExibir") != null) {
                            int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                            Movel movelAlterar = (Movel) session.getAttribute("movelExibir");
                            session.setAttribute("movelAlterar", movelAlterar);
                            session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                            session.removeAttribute("movelExibir");
                            List<Categoria> listaCategoriaMovel = new ArrayList<Categoria>();
                            listaCategoriaMovel = MainPageFacade.listaCategoriasMovel();
                            session.setAttribute("listaCatMovel", listaCategoriaMovel);
                        } else if (session.getAttribute("materialExibir") != null) {
                            int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                            Material materialAlterar = (Material) session.getAttribute("materialExibir");
                            session.setAttribute("materialAlterar", materialAlterar);
                            session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                            session.removeAttribute("materialExibir");
                            List<Categoria> listaCategoriaMaterial = new ArrayList<Categoria>();
                            listaCategoriaMaterial = MainPageFacade.listaCategoriasMaterial();
                            session.setAttribute("listaCatMaterial", listaCategoriaMaterial);

                        }
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;

                case "ALTERARANUNCIOID":
                    try {
                        if (request.getParameter("tipoAnuncio").equals("imovel")) {
                            Imovel i = new Imovel();
                            if (request.getParameter("idAnuncioImovel") != null && !request.getParameter("idAnuncioImovel").equals("")) {
                                i.setId(Integer.parseInt(request.getParameter("idAnuncioImovel")));
                            }
                            if (request.getParameter("tipo") != null && !request.getParameter("tipo").equals("")) {
                                i.setTipo(Integer.parseInt(request.getParameter("tipo")));
                            }
                            if (request.getParameter("titulo") != null && !request.getParameter("titulo").equals("")) {
                                i.setTitulo(new String(request.getParameter("titulo").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("descricao") != null && !request.getParameter("descricao").equals("")) {
                                i.setDescricao(new String(request.getParameter("descricao").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("descricaoPet") != null && !request.getParameter("descricaoPet").equals("")) {
                                i.setBoolean_pet(Integer.parseInt(request.getParameter("descricaoPet")));
                            }
                            if (request.getParameter("descricaoPessoas") != null && !request.getParameter("descricaoPessoas").equals("")) {
                                i.setQuantidade_pessoas(Integer.parseInt(request.getParameter("descricaoPessoas")));
                            }
                            if (request.getParameter("valor") != null && !request.getParameter("valor").equals("")) {
                                i.setPreco(Float.parseFloat(request.getParameter("valor")));
                            }
                            if (request.getParameter("rua") != null && !request.getParameter("rua").equals("")) {
                                i.setRua(new String(request.getParameter("rua").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("num") != null && !request.getParameter("num").equals("")) {
                                i.setNumero(Integer.parseInt(request.getParameter("num")));
                            }
                            if (request.getParameter("cep") != null && !request.getParameter("cep").equals("")) {
                                i.setCep(new String(request.getParameter("cep").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("cidade") != null && !request.getParameter("cidade").equals("")) {
                                i.setCidade(new String(request.getParameter("cidade").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("estado") != null && !request.getParameter("estado").equals("")) {
                                i.setEstado(new String(request.getParameter("estado").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("comple") != null && !request.getParameter("comple").equals("")) {
                                i.setComplemento(new String(request.getParameter("comple").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            } else {
                                i.setComplemento("  ");
                            }
                            if (i.getId() != 0) {
                                int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                                AnuncioFacade.updateImovel(i, idAnuncioAlterar);
                                Imovel imov = AnuncioFacade.buscarImovelPorId(idAnuncioAlterar);
                                session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                                session.setAttribute("imovelExibir", imov);
                                session.removeAttribute("imovelAlterar");
                            }
                        } else if (request.getParameter("tipoAnuncio").equals("movel")) {
                            Movel m = new Movel();
                            if (request.getParameter("idAnuncioMovel") != null && !request.getParameter("idAnuncioMovel").equals("")) {
                                m.setId(Integer.parseInt(request.getParameter("idAnuncioMovel")));
                            }
                            if (request.getParameter("titulo") != null && !request.getParameter("titulo").equals("")) {
                                m.setTitulo(new String(request.getParameter("titulo").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("descricao") != null && !request.getParameter("descricao").equals("")) {
                                m.setDescricao(new String(request.getParameter("descricao").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("tipo") != null && !request.getParameter("tipo").equals("")) {
                                m.setTipo(Integer.parseInt(request.getParameter("tipo")));
                            }
                            if (request.getParameter("valor") != null && !request.getParameter("valor").equals("")) {
                                m.setPreco(Float.parseFloat(request.getParameter("valor")));
                            }
                            if (request.getParameter("rua") != null && !request.getParameter("rua").equals("")) {
                                m.setRua(new String(request.getParameter("rua").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("num") != null && !request.getParameter("num").equals("")) {
                                m.setNumero(Integer.parseInt(request.getParameter("num")));
                            }
                            if (request.getParameter("cep") != null && !request.getParameter("cep").equals("")) {
                                m.setCep(new String(request.getParameter("cep").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("cidade") != null && !request.getParameter("cidade").equals("")) {
                                m.setCidade(new String(request.getParameter("cidade").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("estado") != null && !request.getParameter("estado").equals("")) {
                                m.setEstado(new String(request.getParameter("estado").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("comple") != null && !request.getParameter("comple").equals("")) {
                                m.setComplemento(new String(request.getParameter("comple").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            } else {
                                m.setComplemento("  ");
                            }
                            int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                            AnuncioFacade.updateMovel(m, idAnuncioAlterar);
                            Movel mov = AnuncioFacade.buscarMovelPorId(idAnuncioAlterar);
                            session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                            session.setAttribute("movelExibir", mov);
                            session.removeAttribute("movelAlterar");
                        } else if (request.getParameter("tipoAnuncio").equals("material")) {
                            Material m = new Material();
                            if (request.getParameter("idAnuncioMaterial") != null && !request.getParameter("idAnuncioMaterial").equals("")) {
                                m.setId(Integer.parseInt(request.getParameter("idAnuncioMaterial")));
                            }
                            if (request.getParameter("tipo") != null && !request.getParameter("tipo").equals("")) {
                                m.setTipo(Integer.parseInt(request.getParameter("tipo")));
                            }

                            if (request.getParameter("titulo") != null && !request.getParameter("titulo").equals("")) {
                                m.setTitulo(new String(request.getParameter("titulo").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("descricao") != null && !request.getParameter("descricao").equals("")) {
                                m.setDescricao(new String(request.getParameter("descricao").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }

                            if (request.getParameter("tipo") != null && !request.getParameter("tipo").equals("")) {
                                m.setTipo(Integer.parseInt(request.getParameter("tipo")));
                            }

                            if (request.getParameter("valor") != null && !request.getParameter("valor").equals("")) {
                                m.setPreco(Float.parseFloat(request.getParameter("valor")));
                            }
                            if (request.getParameter("rua") != null && !request.getParameter("rua").equals("")) {
                                m.setRua(new String(request.getParameter("rua").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("num") != null && !request.getParameter("num").equals("")) {
                                m.setNumero(Integer.parseInt(request.getParameter("num")));
                            }
                            if (request.getParameter("cep") != null && !request.getParameter("cep").equals("")) {
                                m.setCep(new String(request.getParameter("cep").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("cidade") != null && !request.getParameter("cidade").equals("")) {
                                m.setCidade(new String(request.getParameter("cidade").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("estado") != null && !request.getParameter("estado").equals("")) {
                                m.setEstado(new String(request.getParameter("estado").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            }
                            if (request.getParameter("comple") != null && !request.getParameter("comple").equals("")) {
                                m.setComplemento(new String(request.getParameter("comple").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                            } else {
                                m.setComplemento("  ");
                            }
                            int idAnuncioAlterar = (int) session.getAttribute("idExibirAnuncio");
                            AnuncioFacade.updateMaterial(m, idAnuncioAlterar);
                            Material mat = AnuncioFacade.buscarMaterialPorId(idAnuncioAlterar);
                            session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                            session.setAttribute("materialExibir", mat);
                            session.removeAttribute("materialAlterar");
                        }
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;

                case "CANCELARALTERARANUNCIO":
                    try {
                        session.removeAttribute("imovelAlterar");
                        session.removeAttribute("imovelExibir");
                        int stat = Integer.parseInt(request.getParameter("status"));
                        User u = (User) session.getAttribute("user");
                        List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId(), stat);
                        session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                        request.getRequestDispatcher("resumo.jsp").forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;

                case "INFORMARVENDAANUNCIO":
                    try {
                        if (session.getAttribute("idExibirAnuncio") != null) {
                            int idAnuncioVenda = (int) session.getAttribute("idExibirAnuncio");
                            AnuncioFacade.updateStatusAnuncio(idAnuncioVenda, 5);
                            
                            //notificação de anúncio vendido **************///
                            int usrAnunc = NotificacaoFacade.buscarIdUsrAnun(idAnuncioVenda);
                            ArrayList<Integer> listaSeguidores = new ArrayList<Integer>();
                            listaSeguidores = (ArrayList)AnuncioFacade.buscarSeguidoresAnuncio(idAnuncioVenda);
                            for(int idSeg : listaSeguidores){
                                NotificacaoFacade.inserirnotificacao(usrAnunc, idSeg, 4);
                            }
                            //////////*****************************/////////
                            
                            User u = (User) session.getAttribute("user");
                            List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId(), 5);
                            session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                            request.getRequestDispatcher("resumo.jsp").forward(request, response);
                        }
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;
                case "FILTROANUNCIO":
                    try {
                        FiltroAnuncio filtro = new FiltroAnuncio(request);

                        out.write(AnuncioFacade.buscarAnuncioAprovado(filtro));
                        out.flush();
                    } catch (Exception ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "ALTERARFOTOSANUNCIO":
                    try {

                        int idA = Integer.parseInt(request.getParameter("idAnuncio"));
                        Anuncio anun = AnuncioFacade.buscaAlteraFotosAnuncio(idA);
                        request.setAttribute("anuncio", anun);
                        request.getRequestDispatcher("alteraFotoAnuncio.jsp").forward(request, response);
                    } catch (Exception e) {
                        request.setAttribute("erro", e.toString());
                        request.getRequestDispatcher("erro.jsp");
                    }
                    break;

                case "UPDATEFOTOSANUNCIO":
                    try {
                        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        String nome = new String();
                        List<Foto> listaFotosNovas = new ArrayList<>();
                        List<Foto> listaFotosAlteradas = new ArrayList<>();
                        Anuncio anunc = new Anuncio();
                        int excluir[] = new int[5];
                        int exInd = 0;
                        /*Escreve a o arquivo na pasta img*/
                        for (FileItem item : multiparts) {
                            if (item.isFormField()) {
                                if (item.getFieldName().equals("idAnuncio")) {
                                    anunc.setIdAnuncio(Integer.parseInt(item.getString()));
                                }
                                if (item.getFieldName().equals("checkExcluir")) {
                                    excluir[exInd] = Integer.parseInt(item.getString());
                                    exInd++;
                                }
                            } else {
                                Random rand = new Random();
                                String nomeString = String.valueOf(rand.nextInt()) + ".jpg";
                                if (!item.getName().equals("")) {
                                    if (item.getFieldName().equals("fotoNova")) {
                                        item.write(new File(caminho + "img" + File.separator + nomeString));
                                        Foto f = new Foto();
                                        f.setCaminho("img" + File.separator + nomeString);
                                        f.setIdAnuncio(anunc.getIdAnuncio());
                                        listaFotosNovas.add(f);
                                        javaxt.io.Image image = new javaxt.io.Image(caminho + "img" + File.separator + nomeString);
                                        image.resize(850, 500);
                                        image.saveAs(caminho + "img" + File.separator + nomeString);
                                    } else {
                                        Foto f = new Foto();
                                        f.setIdFoto(Integer.parseInt(item.getFieldName()));
                                        item.write(new File(caminho + "img" + File.separator + nomeString));
                                        f.setCaminho("img" + File.separator + nomeString);
                                        f.setIdAnuncio(anunc.getIdAnuncio());
                                        listaFotosAlteradas.add(f);
                                        javaxt.io.Image image = new javaxt.io.Image(caminho + "img" + File.separator + nomeString);
                                        image.resize(850, 500);
                                        image.saveAs(caminho + "img" + File.separator + nomeString);
                                    }
                                }
                            }
                        }
                        if (!listaFotosNovas.isEmpty()) {
                            AnuncioFacade.insereFotosAnuncio(listaFotosNovas);
                            AnuncioFacade.updateStatusAnuncio(anunc.getIdAnuncio(), 1);
                        }
                        if (!listaFotosAlteradas.isEmpty()) {
                            AnuncioFacade.alteraFotosAnuncio(listaFotosAlteradas);
                            AnuncioFacade.updateStatusAnuncio(anunc.getIdAnuncio(), 1);
                        }
                        if (excluir[0] != 0) {
                            AnuncioFacade.excluiFotosAnuncio(excluir);
                            AnuncioFacade.updateStatusAnuncio(anunc.getIdAnuncio(), 1);
                        }
                        int ann = AnuncioFacade.verifcaTipoAnuncio(anunc.getIdAnuncio());
                        switch (ann) {
                            case 1:
                                Imovel i = AnuncioFacade.buscarImovelPorId(anunc.getIdAnuncio());
                                session.setAttribute("imovelExibir", i);
                                session.removeAttribute("imovelAlterar");
                                session.setAttribute("idExibirAnuncio", anunc.getIdAnuncio());
                                break;
                            case 2:
                                Movel m = AnuncioFacade.buscarMovelPorId(anunc.getIdAnuncio());
                                session.setAttribute("movelExibir", m);
                                session.removeAttribute("movelAlterar");
                                session.setAttribute("idExibirAnuncio", anunc.getIdAnuncio());
                                break;
                            case 3:
                                Material mat = AnuncioFacade.buscarMaterialPorId(anunc.getIdAnuncio());
                                session.setAttribute("materialExibir", mat);
                                session.removeAttribute("materialAlterar");
                                session.setAttribute("idExibirAnuncio", anunc.getIdAnuncio());
                                break;
                            default:
                                break;
                        }
                       // request.getRequestDispatcher("anuncio.jsp").forward(request, response);
                        session.removeAttribute("mensagemAcao");
                        session.removeAttribute("mensagemAcaoTipo");
                        session.setAttribute("mensagemAcao", "Anuncio Alterado.");
                        session.setAttribute("mensagemAcaoTipo", 11);
                        RequestDispatcher rdi = request.getRequestDispatcher("infoAcao.jsp");
                        rdi.forward(request, response);
                    } catch (Exception e) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;

                //case para add seguidor no anuncio
                case "ADDSEGUIDOR": {
                    
                    int idAnuncioAjax = Integer.parseInt(request.getParameter("idAnuncioAjax"));
                    int idUsrAjax = Integer.parseInt(request.getParameter("idSeguidorAjax"));
                    
                    AnuncioFacade.inserirSeguidorAnuncio(idAnuncioAjax,idUsrAjax);
                    
                    break;
                }
                //case para remover seguidor no anuncio
                case "RMVSEGUIDOR": {
                    
                    int idAnuncioAjax = Integer.parseInt(request.getParameter("idAnuncioAjax"));
                    int idUsrAjax = Integer.parseInt(request.getParameter("idSeguidorAjax"));
                    
                    AnuncioFacade.removerSeguidorAnuncio(idAnuncioAjax, idUsrAjax);
                    
                    break;
                }
                 case "VRFSEGUIDOR": {
                    
                    int idAnuncioAjax = Integer.parseInt(request.getParameter("idAnuncioAjax"));
                    int idUsrAjax = Integer.parseInt(request.getParameter("idSeguidorAjax"));
                    
                    String resp = AnuncioFacade.verifSeguidor(idAnuncioAjax, idUsrAjax).toString();
                    
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");                         
                    response.getWriter().write(resp);
                    
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
        } catch (AcessoBdException ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (AcessoBdException ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
