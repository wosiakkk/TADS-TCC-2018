/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.dao.TimelineDAO;
import br.com.tads.tccpool.beans.Anuncio;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class TimelineFacade {
    
    public static String timeline(Integer userLogado) {
        ArrayList<Anuncio> timeline = new ArrayList<>();
        String timelineFormatado = "";
        TimelineDAO tmDAO = new TimelineDAO();
        try {
            timeline = tmDAO.callTimeline(userLogado);
            if(timeline.size() > 0) {
                timelineFormatado = HTMLTimeline(timeline);
            }
            else {
                timelineFormatado = "Não foram encontrados anúncios de seus amigos. Verifique a <a href=\"vendas.jsp\">Área de vendas</a> clicando no item ao lado.";
            }
        }
        catch (Exception ex) {
            timelineFormatado = "Ocorreu um erro ao buscar os anúncios. Por favor, tente novamente mais tarde.";
            Logger.getLogger(TimelineFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return timelineFormatado;
    }
    
    private static String HTMLTimeline(ArrayList<Anuncio> anuncios) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFmt = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String timelineEntry = "<div class=\"timeline\">\n" +
                               "  <!-- Timeline header -->\n" +
                               "  <div class=\"timeline-header\">\n" +
                               "      <div class=\"timeline-header-title bg-dark\">Recentes</div>\n" +
                               "  </div>";
        
        for (Anuncio timeline : anuncios) {
            String fotoUser = timeline.getDsFotoUsuario() == null ? "img/fotosPerfil/default.jpg" : timeline.getDsFotoUsuario();
            timelineEntry += "<div class=\"timeline-entry\">\n"
                           + "    <!-- DESCRIÇÃO DO USUÁRIO -->\n"
                           + "    <div class=\"timeline-stat\">\n"
                           + "        <div class=\"timeline-icon\"><img src=\"" + fotoUser + "\" alt=\"Foto do perfil\"></div>\n"
                           + "        <!-- DATA DE POSTAGEM DO ANÚNCIO -->\n"
                           + "        <div class=\"timeline-time\">" + fmt.format(timeline.getDtAnuncio().getTime()) + "</div>\n"
                           + "    </div>\n"
                           + "    <!-- DESCRIÇÃO DA POSTAGEM -->\n"
                           + "    <div class=\"timeline-label\">\n"
                           + "        <!-- NOME DO USUÁRIO E TÍTULO DO ANÚNCIO -->\n"
                           + "        <p class=\"mar-no pad-btm\"><a href=\"UserServlet?action=PERFIL&idUser=" + timeline.getIdUsuario() + "\" class=\"btn-link text-semibold\">" + timeline.getNmUsuario() + "</a></p>\n"
                           + "        <blockquote class=\"bq-sm bq-open\">\n"
                           + "        " + timeline.getDescricao()+ "<br>Valor: " + numberFmt.format(timeline.getValor())
                           + "        </blockquote>\n"
                           + "        <p><a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + timeline.getIdAnuncio() + "\">Detalhes</a></p>\n"
                           + "        <!-- FOTO DO ANÚNCIO -->\n"
                           + "        <div class=\"img-holder\">\n"
                           + "            <a href=\"AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=" + timeline.getIdAnuncio() + "\">\n"
                           + "                <img src=\"" + timeline.getCaminhoFoto() + "\" alt=\"Foto do anúncio não encontrada\">\n"
                           + "            </a>\n"
                           + "        </div>\n"
                           + "    </div>\n"
                           + "</div>";
        }
        
        return timelineEntry + "</div>";
    }
}
