package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.Conversa;
import br.com.tads.tccpool.dao.MensagemDAO;
import br.com.tads.tccpool.beans.Mensagem;
import br.com.tads.tccpool.beans.User;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Mensagens dos usuários
 * @author Marcos
 */
public class MensagemFacade {
    
    public static String inserir(Mensagem mensagem) {
        MensagemDAO mDAO = new MensagemDAO();
        int insertOK = 0;
        try {
            insertOK = mDAO.inserir(mensagem);
        } catch (SQLException ex) {
            insertOK = 0;
            Logger.getLogger(MensagemFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(insertOK > 0) {
            return "Mensagem inserida com sucesso";
        }
        else {
            return "Falha ao inserir mensagem. Verifique os logs do servidor";
        }
    }
    
    public static String listar(int idOrigem, int idDestino, User userLogado) {
        MensagemDAO mDAO = new MensagemDAO();
        ArrayList<Mensagem> mensagens;
        String mensagensFormatadas = "";
        try {
            mensagens = mDAO.listar(idOrigem, idDestino);
            mensagensFormatadas += MensagemFacade.HTMLMensagem(mensagens, userLogado);
        } catch (SQLException ex) {
            mensagensFormatadas = "Falha ao buscar mensagens. Verifique os logs do servidor.";
            Logger.getLogger(MensagemFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mensagensFormatadas;
    }
    
    public static String listarConversas(Integer userLogado) {
        MensagemDAO mDAO = new MensagemDAO();
        ArrayList<Conversa> conversas;
        String conversasFormatadas = "";
        try {
            conversas = mDAO.listarConversas(userLogado);
            for (Conversa conversa : conversas) {
                conversasFormatadas += "<a class=\"list-group-item conversa\" data-conversa=\"" + conversa.getIdConversa() + "\" data-destino=\"" + conversa.getIdDestino() + "\" href=\"#\">"
                                     + "    <div class=\"col-lg-2 col-2 user-img text-center\">\n"
                                     + "        <img src=\"" + conversa.getFotoDestino() + "\" class=\"main-cmt-img\">\n"
                                     + "    </div>\n"
                                     + "    <p>" + conversa.getNmDestino() + "</p>"
                                     + "</a>";
                
            }
        } catch (SQLException ex) {
            conversasFormatadas = "Falha ao buscar as conversas. Tente novamente mais tarde.";
            Logger.getLogger(MensagemFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conversasFormatadas;
    }
    
    public static String HTMLMensagem(ArrayList<Mensagem> mensagens, User userLogado) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int primeiroID = 0;
        String HTML = "";
        
        /*O Select ordena da mensagem mais antiga para mais atual
         *Então pega o indicie 0 para ter o usuario que envio a primeira mensagem 
        **/
        primeiroID = mensagens.get(0).getIdOrigem();
        for (Mensagem mensagem : mensagens) {
            if(mensagem.getIdOrigem() == userLogado.getId()) {
                HTML += "<div class=\"row col-md-12 col-lg-12 col-sm-12 p-1 pt-3 pr-4\">\n"
                      + "    <div class=\"col-lg-10 col-8 bg-light rounded pb-1\">\n"
                      + "        <div class=\"row\">\n"
                      + "            <div class=\"col-lg-8 col-sm-12 col-6 border-bottom pr-0\">\n"
                      + "                <i><p class=\"w-100 p-2 m-0\">" + mensagem.getNmOrigem()+ "</p></i>\n"
                      + "            </div>\n"
                      + "            <div class=\"col-lg-4 col-sm-12 col-6 border-bottom\">\n"
                      + "                <p class=\"w-100 p-2 m-0\"><span class=\"float-right\"><i class=\"fa fa-clock-o mr-1\" aria-hidden=\"true\"></i>" + format.format(mensagem.getData().getTime()) + "</span></p>\n"
                      + "            </div>\n"
                      + "        </div>\n"
                      + "        <div class=\"row\">\n"
                      + "            <div class=\"col-lg-12 border-bottom pr-0\">\n"
                      + "                <p class=\"w-100 p-2 m-0\">" + mensagem.getConteudo() + "</p>\n"
                      + "            </div>\n"
                      + "        </div>\n"
                      + "    </div>\n"
                      + "    <div class=\"col-lg-2 col-2 user-img text-center\">\n"
                      + "        <img src=\"" + userLogado.getFoto() + "\" class=\"main-cmt-img\">\n"
                      + "    </div>\n"
                      + "</div>";
            }
            else {
                HTML += "<div class=\"row col-md-12 col-lg-12 col-sm-12 p-1 pt-3 pr-4\">\n"
                      + "    <div class=\"col-lg-2 col-2 user-img text-center\">\n"
                      + "        <img src=\"" + mensagem.getFotoDestino() + "\" class=\"main-cmt-img\">\n"
                      + "    </div>\n"
                      + "    <div class=\"col-lg-10 col-8 bg-light rounded pb-1\">\n"
                      + "        <div class=\"row\">\n"
                      + "            <div class=\"col-lg-8 col-sm-12 col-6 border-bottom pr-0\">\n"
                      + "                <i><p class=\"w-100 p-2 m-0\">" + mensagem.getNmOrigem() + "</p></i>\n"
                      + "            </div>\n"
                      + "            <div class=\"col-lg-4 col-sm-12 col-6 border-bottom\">\n"
                      + "                <p class=\"w-100 p-2 m-0\"><span class=\"float-right\"><i class=\"fa fa-clock-o mr-1\" aria-hidden=\"true\"></i>" + format.format(mensagem.getData().getTime()) + "</span></p>\n"
                      + "            </div>\n"
                      + "        </div>\n"
                      + "        <div class=\"row\">\n"
                      + "            <div class=\"col-lg-12 border-bottom pr-0\">\n"
                      + "                <p class=\"w-100 p-2 m-0\">" + mensagem.getConteudo() + "</p>\n"
                      + "            </div>\n"
                      + "        </div>\n"
                      + "    </div>\n"
                      + "</div>";
            }
                  
        }
        
        return HTML;
    }
    
}
