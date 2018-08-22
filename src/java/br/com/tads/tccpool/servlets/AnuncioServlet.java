/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tads.tccpool.servlets;

import br.com.tads.tccpool.beans.Anuncio;
import br.com.tads.tccpool.beans.Categoria;
import br.com.tads.tccpool.beans.Imovel;
import br.com.tads.tccpool.beans.Material;
import br.com.tads.tccpool.beans.Movel;
import br.com.tads.tccpool.beans.User;
import br.com.tads.tccpool.exception.AcessoBdException;
import br.com.tads.tccpool.facade.AnuncioFacade;
import br.com.tads.tccpool.facade.MainPageFacade;
import br.com.tads.tccpool.interfaces.Anunciavel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
            throws ServletException, IOException, AcessoBdException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String action = request.getParameter("action");

            RequestDispatcher rd;
            
            switch (action) {
                case "ADDIMV":

                    Imovel im = new Imovel();
                    Anuncio a = new Anuncio();
                    int cat = 1;
                    String caminhoImovel = new String();

                    //String caminhoReal = "C:\\Users\\onurb\\Documents\\NetBeansProjects\\TCCPOOL\\web\\img";
                    List<String> listImovel = new ArrayList<String>();
                    /// if (ServletFileUpload.isMultipartContent(request)) {

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
                                    im.setTitulo(item.getString());

                                }
                                if (item.getFieldName().equals("descricao")) {
                                    im.setDescricao(item.getString());

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
                                    im.setRua(item.getString());

                                }
                                if (item.getFieldName().equals("num")) {
                                    im.setNumero(Integer.parseInt(item.getString()));

                                }
                                if (item.getFieldName().equals("cep")) {
                                    im.setCep(item.getString());

                                }
                                if (item.getFieldName().equals("cidade")) {
                                    im.setCidade(item.getString());

                                }
                                if (item.getFieldName().equals("estado")) {
                                    im.setEstado(item.getString());

                                }
                                if (item.getFieldName().equals("comple")) {
                                    im.setComplemento(item.getString());

                                }
                                a.setObj(im);

                            } else {
                                // nome = AnuncioFacade.getId()+1;
                                // File imagem = new File(destino, nome);
                                // item.write(imagem);
                                //nome = multiparts.get(i).getName();
                                Random rand = new Random();
                                String nomeString = String.valueOf(rand.nextInt()) + ".jpg";
                                if (!item.getName().equals("")) {
                                    item.write(new File(request.getServletContext().getRealPath("img") + File.separator + nomeString));

                                    caminhoImovel = "img" + File.separator + nomeString;
                                    listImovel.add(caminhoImovel);
                                }
                                //item.getFieldName().equals("titulo");

                            }
                            //item.getString("titulo");
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                   
                        User u = (User)session.getAttribute("user");
                        AnuncioFacade.insereImovel(im, cat, listImovel, u);
                        request.setAttribute("msg", "Anuncio Realizao Com Sucesso");
                     List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                     
                     
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("msg", "Falha ao Realizar Anuncio: " + ex);
                    } finally {
                    rd = request.getRequestDispatcher("resumo.jsp");
                    rd.forward(request, response);
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
                        int nome;// = new String();

                        for (FileItem item2 : multiparts2) {
                            if (item2.isFormField()) {
                                if (item2.getFieldName().equals("titulo")) {
                                    movel.setTitulo(item2.getString());
                                }
                                if (item2.getFieldName().equals("descricao")) {
                                    movel.setDescricao(item2.getString());
                                }
                                if (item2.getFieldName().equals("valor")) {
                                    movel.setPreco(Float.parseFloat(item2.getString()));
                                }
                                //  an.setObj(movel);
                            } else {
                                nome = AnuncioFacade.getId() + 1;
                                //String nomeString = String.valueOf(nome)+".jpg";
                                Random rand = new Random();
                                String nomeString = String.valueOf(-1 * (rand.nextInt())) + ".jpg";

                                item2.write(new File(request.getServletContext().getRealPath("img") + File.separator + nomeString));
                                caminhomovel = "img" + File.separator + nomeString;
                                lista.add(caminhomovel);
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        User u = (User)session.getAttribute("user");
                        AnuncioFacade.insereMovel(movel, cate, lista, u);
                        List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        request.getRequestDispatcher("resumo.jsp").forward(request, response);
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
                        int nome;// = new String();
                        for (FileItem item2 : multiparts2) {
                            if (item2.isFormField()) {
                                if (item2.getFieldName().equals("titulo")) {
                                    material.setTitulo(item2.getString());
                                }
                                if (item2.getFieldName().equals("descricao")) {
                                    material.setDescricao(item2.getString());
                                }
                                if (item2.getFieldName().equals("valor")) {
                                    material.setPreco(Float.parseFloat(item2.getString()));
                                }
                                if (item2.getFieldName().equals("select")) {
                                    material.setTipo(Integer.parseInt(item2.getString()));
                                }
                                // anu.setObj(material);
                            } else {
                                 nome = AnuncioFacade.getId() + 1;
                                //String nomeString = String.valueOf(nome)+".jpg";
                                Random rand = new Random();
                                String nomeString = String.valueOf(-1 * (rand.nextInt())) + ".jpg";

                                item2.write(new File(request.getServletContext().getRealPath("img") + File.separator + nomeString));
                                caminhomaterial = "img" + File.separator + nomeString;
                                listaMaterial.add(caminhomaterial);
                                
                                /*nome = AnuncioFacade.getId() + 1;
                                item2.write(new File(request.getServletContext().getRealPath("img") + File.separator + nome));
                                caminhomaterial = request.getServletContext().getRealPath("img") + File.separator + nome;
                                caminhomaterial = "img" + File.separator + nomeString;
                                i++;*/
                            }
                        }
                        request.setAttribute("message", "Arquivo carregado com sucesso");
                    } catch (Exception ex) {
                        request.setAttribute("message", "Upload de arquivo falhou devido a " + ex);
                    }
                    try {
                        User u = (User)session.getAttribute("user");
                        AnuncioFacade.insereMaterial(material, categ, listaMaterial, u);
                        List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(AnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    finally{
                        request.getRequestDispatcher("resumo.jsp").forward(request, response);
                    }
                    break;
                case "BUSCARIMOVEISPEND":
                    List<Imovel> listaImovel = new ArrayList<Imovel>();
                    listaImovel = AnuncioFacade.buscarPendente();
                    session.setAttribute("listaImovel", listaImovel);
                    rd = request.getRequestDispatcher("aprovarAnuncio.jsp");
                    rd.forward(request, response);
                    break;

                case "BUSCARPORID":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Imovel imovel = new Imovel();
                    imovel = AnuncioFacade.buscarImovelPorId(id);
                    session.setAttribute("imovelBuscado", imovel);
                    rd = request.getRequestDispatcher("homeAnunciosPendentes.jsp");
                    rd.forward(request, response);
                    break;

                case "APROVIMV":
                    int idImv = Integer.parseInt(request.getParameter("id"));
                    String status = request.getParameter("optradio");
                    AnuncioFacade.alterarStatus(status, idImv);
                    response.sendRedirect("escolhaPendente.jsp");
                    break;
                    
                case "BUSCAAPROVADOS":
                    List<Anuncio> anunciosAprovados = AnuncioFacade.buscarAnuncioAprovado();
                    String HTMLResponse = "";
                    if(anunciosAprovados != null) {
                        for (Anuncio anuncio : anunciosAprovados) {
                            HTMLResponse += "<div class=\"col-lg-4 col-md-6 mb-4\">\n" +
                                            "    <div class=\"card h-100\">\n" +
                                            "        <a href=\"#\"><img class=\"card-img-top\" src=\"" + anuncio.getCaminhoFoto() + "\" alt=\"\"></a>\n" +
                                            "        <div class=\"card-body\">\n" +
                                            "            <h4 class=\"card-title\">\n" +
                                            "                <a href=\"#\">" + anuncio.getTitulo() + "</a>\n" +
                                            "            </h4>\n" +
                                            "            <h5>$" + String.valueOf(anuncio.getValor()) + "</h5>\n" +
                                            "            <p class=\"card-text\">" + anuncio.getDescricao() + "</p>\n" +
                                            "        </div>\n" +
                                            "        <div class=\"card-footer\">\n" +
                                            "            <a href=\"#\">veja mais</a>\n" +
                                            "        </div>\n" +
                                            "    </div>\n" +
                                            "</div>";
                        }
                        out.write(HTMLResponse);
                    }
                    else {
                        out.write("Falha ao buscar an&uacute;ncios");
                    }
                    break;
                    
                    //buscar anuncios de determinado usuario para mostrar no botão ''mues anuncios"
                case "BUSCAANUNCIOUSER":
                    try{
                    int idUser = Integer.parseInt(request.getParameter("idUsr"));
                     List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(idUser);
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                    rd = request.getRequestDispatcher("resumo.jsp");
                    rd.forward(request, response); 
                    }catch(Exception e){
                            
                        }
                    break;
                    
                     //exibir página de anuncio
                case "EXIBIRANUNCIO":
                    int idAnuncio = Integer.parseInt(request.getParameter("idAnuncio"));
                    int t = AnuncioFacade.verifcaTipoAnuncio(idAnuncio);
                    if (t == 1) {
                        Imovel i = new Imovel();
                        //int idImovel = AnuncioFacade.retornoIdImovel(idAnuncio);
                        i = AnuncioFacade.buscarImovelPorId(idAnuncio);
                        //i.setId(idAnuncio);
                      //  i = AnuncioFacade.exibirImovel(idAnuncio);
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
                        try{
                     if(session.getAttribute("imovelExibir") != null){
                     int idAnuncioExcluir = (int)session.getAttribute("idExibirAnuncio");
                     Imovel ImovelExcluir = (Imovel)session.getAttribute("imovelExibir");
                     AnuncioFacade.deletarAnuncioImovel(idAnuncioExcluir, ImovelExcluir);
                     User u = (User)session.getAttribute("user");
                     List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                     } else if(session.getAttribute("movelExibir") != null){
                         int idAnuncioExcluir = (int)session.getAttribute("idExibirAnuncio");
                     Movel MovelExcluir = (Movel)session.getAttribute("movelExibir");
                     AnuncioFacade.deletarAnuncio(idAnuncioExcluir);
                     User u = (User)session.getAttribute("user");
                     List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                     }
                    rd = request.getRequestDispatcher("resumo.jsp");
                    rd.forward(request, response); 
                        }catch(Exception e){
                            
                        }
                    break;
                    
                    case "ALTERARANUNCIO":
                        try{
                     if (session.getAttribute("imovelExibir") != null){
                     int idAnuncioAlterar = (int)session.getAttribute("idExibirAnuncio");
                     Imovel ImovelAlterar = (Imovel)session.getAttribute("imovelExibir");
                     session.setAttribute("imovelAlterar", ImovelAlterar);
                        session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                        session.removeAttribute("imovelExibir");
                        List<Categoria> listaCategoriaImovel = new ArrayList<Categoria>();
                                listaCategoriaImovel = MainPageFacade.listaCategoriasImovel();
                            session.setAttribute("listaCatImovel", listaCategoriaImovel);
                            
                     }else if(session.getAttribute("movelExibir") != null){
                         int idAnuncioAlterar = (int)session.getAttribute("idExibirAnuncio");
                     Movel movelAlterar = (Movel)session.getAttribute("movelExibir");
                     session.setAttribute("movelAlterar", movelAlterar);
                        session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                        session.removeAttribute("movelExibir");
                        
                     }
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response); 
                        }catch(Exception e){
                            
                        }
                    break;
                    
                    case"ALTERARANUNCIOID":
                        
                        try{
                            
                            if(request.getParameter("tipoAnuncio").equals("imovel")){
                                Imovel i = new Imovel();
                           if(request.getParameter("idAnuncioImovel")!= null && !request.getParameter("idAnuncioImovel").equals("")){
                               i.setId(Integer.parseInt(request.getParameter("idAnuncioImovel")));
                           }
                           if(request.getParameter("tipo")!= null && !request.getParameter("tipo").equals("")){
                               i.setTipo(Integer.parseInt(request.getParameter("tipo")));
                           }
                           if(request.getParameter("titulo")!= null && !request.getParameter("titulo").equals("")){
                               i.setTitulo(request.getParameter("titulo"));
                           }
                           if(request.getParameter("descricao")!= null && !request.getParameter("descricao").equals("")){
                               i.setDescricao(request.getParameter("descricao"));
                           }
                           if(request.getParameter("descricaoPet")!= null && !request.getParameter("descricaoPet").equals("")){
                               i.setBoolean_pet(Integer.parseInt(request.getParameter("descricaoPet")));
                           }
                           if(request.getParameter("descricaoPessoas")!= null && !request.getParameter("descricaoPessoas").equals("")){
                               i.setQuantidade_pessoas(Integer.parseInt(request.getParameter("descricaoPessoas")));
                           }
                           if(request.getParameter("valor")!= null && !request.getParameter("valor").equals("")){
                               i.setPreco(Float.parseFloat(request.getParameter("valor")));
                           }
                           if(request.getParameter("rua")!= null && !request.getParameter("rua").equals("")){
                               i.setRua(request.getParameter("rua"));
                           }
                           if(request.getParameter("num")!= null && !request.getParameter("num").equals("")){
                               i.setNumero(Integer.parseInt(request.getParameter("num")));
                           }
                           if(request.getParameter("cep")!= null && !request.getParameter("cep").equals("")){
                               i.setCep(request.getParameter("cep"));
                           }
                           if(request.getParameter("cidade")!= null && !request.getParameter("cidade").equals("")){
                               i.setCidade(request.getParameter("cidade"));
                           }
                           if(request.getParameter("estado")!= null && !request.getParameter("estado").equals("")){
                               i.setEstado(request.getParameter("estado"));
                           }
                           if(request.getParameter("comple")!= null && !request.getParameter("comple").equals("")){
                               i.setComplemento(request.getParameter("comple"));
                           }else{
                               i.setComplemento("  ");
                           }
                           if(i.getId() != 0){
                               int idAnuncioAlterar = (int)session.getAttribute("idExibirAnuncio");
                               AnuncioFacade.updateImovel(i, idAnuncioAlterar);
                               Imovel imov = AnuncioFacade.buscarImovelPorId(idAnuncioAlterar);
                               session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                               session.setAttribute("imovelExibir", imov);
                        session.removeAttribute("imovelAlterar");
                           }
                           
                        }else if(request.getParameter("tipoAnuncio").equals("movel")){
                                 Movel m = new Movel();
                           if(request.getParameter("idAnuncioMovel")!= null && !request.getParameter("idAnuncioMovel").equals("")){
                               m.setId(Integer.parseInt(request.getParameter("idAnuncioMovel")));
                           }
                           
                           if(request.getParameter("titulo")!= null && !request.getParameter("titulo").equals("")){
                               m.setTitulo(request.getParameter("titulo"));
                           }
                           if(request.getParameter("descricao")!= null && !request.getParameter("descricao").equals("")){
                               m.setDescricao(request.getParameter("descricao"));
                           }
                           
                           if(request.getParameter("valor")!= null && !request.getParameter("valor").equals("")){
                               m.setPreco(Float.parseFloat(request.getParameter("valor")));
                           }
                           int idAnuncioAlterar = (int)session.getAttribute("idExibirAnuncio");
                               AnuncioFacade.updateMovel(m, idAnuncioAlterar);
                               Movel mov = AnuncioFacade.buscarMovelPorId(idAnuncioAlterar);
                               session.setAttribute("idExibirAnuncio", idAnuncioAlterar);
                               session.setAttribute("movelExibir", mov);
                        session.removeAttribute("movelAlterar");
                        }
                        rd = request.getRequestDispatcher("anuncio.jsp");
                        rd.forward(request, response);
                        }catch(Exception e){
                            
                        }
                    break;
                    
                    case"CANCELARALTERARANUNCIO":
                        try{
                            session.removeAttribute("imovelAlterar");
                            session.removeAttribute("imovelExibir");
                            User u = (User)session.getAttribute("user");
                     List<Anuncio> aunciosDoUsuario = AnuncioFacade.buscarAnuncioDoUsuario(u.getId());
                    session.setAttribute("ListaAunciosDoUusario", aunciosDoUsuario);
                            request.getRequestDispatcher("resumo.jsp").forward(request, response);
                            
                        }catch(Exception e){
                            
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
