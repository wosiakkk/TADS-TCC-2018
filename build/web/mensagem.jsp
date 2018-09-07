<%-- 
    Document   : mensagem
    Created on : 22/05/2018, 14:18:31
    Author     : Marcos
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>
<!-- importanto CSS para mensagens e comentários -->
<link href="assets/css/mensagem.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/mensagem.js"></script>

<div class="col-md-12 col-lg-12 col-sm-12">
    <div id="respostaAjax" class="row jumbotron">
        
    </div>
    <form id="mensagemAjax">
        <input name="action" value="ADD" type="hidden">
        <input name="ID_ORIGEM" value="<c:out value="${user.getId()}"/>" type="hidden">
        <input name="ID_DESTINO" value="9" type="hidden">
        <div class="form-group">
            <textarea id="DS_MSG" name="DS_MSG" class="form-control" cols="25" rows="5"></textarea>
        </div>
        <button name="BTN_ENVIAR" type="submit" class="btn btn-success float-right" value="ENVIAR">
            <i class="fa fa-send"></i>
        </button>
        <button id="BTN_LISTAR_MENSAGEM" name="BTN_LISTAR_MENSAGEM" type="button" class="btn btn-default">LISTAR</button>
    </form> <!-- fim #mensagemAjax -->
</div> <!-- fim .col-lg-10 -->
