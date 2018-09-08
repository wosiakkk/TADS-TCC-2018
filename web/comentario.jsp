<%-- 
    Document   : mensagem
    Created on : 22/05/2018, 14:18:31
    Author     : Marcos

    ******************************************************IMPORTANTE******************************************************
    Este arquivo foi pensado para ser incluído dentro de outros.
    Por isso não tem declaração de taglib nem dos objetos utilizados nele,mas sim, usa os da página onde ele foi incluído.
    Vejo um exemplo de utilizão no arqv anuncio.jsp
    ******************************************************IMPORTANTE******************************************************
--%>

<c:choose>
    <c:when test="${(empty(user))}">
        <h4 class="text-center">Faça <a href="login.jsp">login</a> para comentar este an&uacute;ncio!</h4>
    </c:when>
    <c:otherwise>
        <link href="assets/css/comentario.css" rel="stylesheet">

        <div class="col-lg-10">
            <form id="comentarioAjax">
                <input name="action" value="ADD" type="hidden">
                <input name="ID_ANUNCIO" value="<c:out value="${idExibirAnuncio}"/>" type="hidden">  
                <input name="ID_USUARIO" value="<c:out value="${user.getId()}"/>" type="hidden">
                <input name="ID_REPLY" value="" type="hidden">
                <div class="form-group">
                    <label for="DS_MSG">Deixe aqui seu coment&aacute;rio:</label>
                    <textarea id="DS_MSG" name="DS_MSG" class="form-control" cols="25" rows="5"></textarea>
                </div>
                <div class="form-group send-icon">
                    <button name="BTN_ENVIAR" type="submit" class="btn btn-success pull-right" value="Enviar">
                        <i class="fa fa-send"></i>
                    </button>
                </div>
            </form> <!-- fim #comentarioAjax -->
        </div> <!-- fim .col-lg-10 -->
        <div id="respostaAjax" class="col-lg-10 comment-main">
            <ul class="p-0">
                <li>

                </li>
            </ul>
        </div> <!-- fim #respostaAjax -->
        <!-- Rodapé -->
        <script src="assets/js/comentario.js" type="text/javascript" charset="UTF-8"></script>
    </c:otherwise>
</c:choose>
    
