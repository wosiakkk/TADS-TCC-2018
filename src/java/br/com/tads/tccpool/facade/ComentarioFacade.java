package br.com.tads.tccpool.facade;

import br.com.tads.tccpool.beans.Comentario;
import br.com.tads.tccpool.dao.ComentarioDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class ComentarioFacade {
    public static String insereComentario(Comentario comentario) {
        ComentarioDAO mensagemDAO = new ComentarioDAO();
        try {
            mensagemDAO.inserir(comentario);
            return "Comentario inserido com sucesso!";
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return "Falha ao inserir comentario!";
        }
    }
    
    /**
     * Lista todos os comentários e replys do anúncio informado
     * @param IdAnuncio ID do anúncio
     * @param isManager Flag para identificar se o usuário logado é Administrador ou Moderador
     * @return Comentários do anúncio informado em formato HTML para exibição
     */
    public static String listarComentarios(int IdAnuncio, Boolean isManager) {
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        ArrayList<Comentario> replayList = new ArrayList<>();
        String comentarioHTML = "";
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        
        
        try {
            comentarioList = comentarioDAO.listar(IdAnuncio);
            for (Comentario comentario : comentarioList) {
                //Monta HTML do comentário
                comentarioHTML += ComentarioFacade.HTMLComentario(comentario, false, isManager);
                
                if(comentarioDAO.verificaReply(comentario.getIdComentario())) {
                    replayList = ComentarioFacade.listarReply(comentario);
                    for (Comentario replay : replayList) {
                        //Monta HTML das reposta do comentário
                        comentarioHTML += ComentarioFacade.HTMLComentario(replay, true, isManager);
                    }
                }
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ComentarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            comentarioHTML = "Falha ao buscar comentarios!";
        }
        
        return comentarioHTML;
    }
    
    public static ArrayList<Comentario> listarReply(Comentario comentarioPai) throws SQLException {
        ArrayList<Comentario> comentarioList = new ArrayList<>();
        String comentarioHTML = "";
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        comentarioList = comentarioDAO.listarReply(comentarioPai.getIdAnuncio(), comentarioPai.getIdComentario());
        return comentarioList;
    }
    
    public static void setLike(int idComentario) {
        try {
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.setLike(idComentario);
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setUnlike(int idComentario) {
        try {
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.setUnlike(idComentario);
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String HTMLComentario(Comentario comentario, boolean isReply, boolean isManager) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String HTMLComentario = "<div class=\"row p-1 pt-3 pr-4 " + (isReply ? "offset-md-1" : "") + "\">\n"
                              + "    <div class=\"col-lg-2 col-3 user-img text-center\">\n"
                              + "        <img src=\"img/profile.jpg\" class=\"main-cmt-img\">\n"
                              + "    </div>\n"
                              + "    <div class=\"col-lg-10 col-9 user-comment bg-light rounded pb-1\">\n";

        if(isManager) {      
            HTMLComentario += "        <div class=\"row\">\n"
                            + "            <div class=\"col-lg-4 col-sm-12 col-4 border-bottom pr-0\">\n"
                            + "                <i><p class=\"w-100 p-2 m-0\">" + comentario.getNmUser() + "</p></i>\n"
                            + "            </div>\n"
                            + "            <div class=\"col-lg-4 col-sm-12 col-4 border-bottom p-1 p1-2\">\n"
                            + "                <div class=\"m-0 mr-2\" data-comentario=\"" + comentario.getIdComentario() + "\" onClick=\"excluirComentario(this)\">\n"
                            + "                    <a class=\"m-0 mr-2\"><span class=\"float-right\"><i class=\"fa fa-trash mr-1\" aria-hidden=\"true\"></i> Excluir</span></a>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "            <div class=\"col-lg-4 col-sm-12 col-4 border-bottom\">\n"
                            + "                <p class=\"w-100 p-2 m-0\"><span class=\"float-right\"><i class=\"fa fa-clock-o mr-1\" aria-hidden=\"true\"></i>" + format.format(comentario.getData().getTime()) + "</span></p>\n"
                            + "            </div>\n"
                            + "        </div>\n";
        }
        else {
            HTMLComentario += "        <div class=\"row\">\n"
                            + "            <div class=\"col-lg-8 col-sm-12 col-6 border-bottom pr-0\">\n"
                            + "                <i><p class=\"w-100 p-2 m-0\">" + comentario.getNmUser() + "</p></i>\n"
                            + "            </div>\n"
                            + "            <div class=\"col-lg-4 col-sm-12 col-6 border-bottom\">\n"
                            + "                <p class=\"w-100 p-2 m-0\"><span class=\"float-right\"><i class=\"fa fa-clock-o mr-1\" aria-hidden=\"true\"></i>" + format.format(comentario.getData().getTime()) + "</span></p>\n"
                            + "            </div>\n"
                            + "        </div>\n";
        }
        
        HTMLComentario += "        <div class=\"row\">\n"
                        + "            <div class=\"col-lg-12 border-bottom pr-0\">\n"
                        + "                <p class=\"w-100 p-2 m-0\">" + comentario.getConteudo() + "</p>\n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div class=\"user-comment-desc p-1 pl-2\">\n"
                        + "            <div class=\"like\" data-comentario=\"" + comentario.getIdComentario() + "\" onClick=\"avaliarComentario('LIKE_COMENTARIO', this)\">\n"
                        + "                <a class=\"m-0 mr-2\"><i class=\"fa fa-thumbs-up mr-1\" aria-hidden=\"true\"></i> " + String.valueOf(comentario.getQtdeLikes()) + "</a>\n"
                        + "            </div>\n"
                        + "            <div class=\"unlike\" data-comentario=\"" + comentario.getIdComentario() + "\" onClick=\"avaliarComentario('UNLIKE_COMENTARIO', this)\">\n"                        
                        + "                <a class=\"m-0 mr-2\"><i class=\"fa fa-thumbs-down mr-1\" aria-hidden=\"true\"></i> " + String.valueOf(comentario.getQtdeUnlikes()) + "</a>\n"     
                        + "            </div>\n";
        
        if(!isReply) {
            HTMLComentario += "            <div class=\"reply\" data-comentario=\"" + comentario.getIdComentario() + "\" onClick=\"replyComentario(this)\">\n"                
                            + "                <a><i class=\"fa fa-mail-reply reply\"></i> Responder</a>\n"                        
                            + "            </div>\n";
        }
        
        HTMLComentario += "        </div>\n"
                        + "    </div>\n"
                        + "</div>";
        
        return HTMLComentario;
    }

    public static String excluirComentario(int idComentario) {
        ComentarioDAO dao = new ComentarioDAO();
        try {
            boolean deleteOK = dao.excluirReplay(idComentario);
            deleteOK = dao.excluirComentario(idComentario);
            if(deleteOK) {
                return "Comentário excluído com sucesso!";
            }
            else {
                return "Falha ao excluir comentário.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return "Ocorreu um erro ao excluir o comentario. Por favor tente novamente mais tarde.";
        }
    }
    
}
