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
<link href="assets/css/feed-style.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/mensagem.js"></script>

<div class="col-md-12 col-lg-12 col-sm-12">
    <h1>Mensagens</h1>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
</div>

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<div class="col-md-9 col-lg-9 col-sm-12">
    <div id="respostaAjax" class="row jumbotron">
        
    </div>
    <form id="mensagemAjax">
        <input name="action" value="ADD_MENSAGEM" type="hidden">
        <input name="ID_ORIGEM" value="<c:out value="${user.getId()}"/>" type="hidden">
        <input name="ID_DESTINO" value="<c:out value="${perfil.id}"/>" type="hidden">
        <div class="form-group">
            <textarea id="DS_MSG" name="DS_MSG" class="form-control" cols="25" rows="5"></textarea>
        </div>
        <button name="BTN_ENVIAR" type="submit" class="btn btn-success float-right" value="ENVIAR">
            <i class="fa fa-send fa-2x"></i>
        </button>
    </form> <!-- fim #mensagemAjax -->
</div> <!-- fim .col-lg-10 -->
