<%-- 
    Document   : aprovarAnuncio
    Created on : 05/05/2018, 15:28:04
    Author     : onurb
--%>
<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>
<div class="col-sm-12">
    <h2>Anúncios de ${user.nome}: </h2>
    <hr>
</div>
    
<div class="col-sm-12 list-inline">
    <img class="list-inline-item " src="img\icones\house.png">
    <img class="list-inline-item" src="img\icones\chair.png">
    <img class="list-inline-item" src="img\icones\books.png"> 
    <hr>
</div>
<div class="jumbotron col-sm-12" >
    <c:set var="listaDoUsuario" value="${ListaAunciosDoUusario}"/>
    <c:forEach var="listaDoUsuario" items="${listaDoUsuario}">
        <div class="card">
            <h3 class="card-header primary-color white-text">Anuncio Pendente</h3>
            <div class="card-body">
                <h4 class="card-title">Descrição: ${listaDoUsuario.descricao}</h4>
                <p class="card-text">Categoria :${listaDoUsuario.categoria}</p>
                <p class="card-text">Status do Anúncio :${listaDoUsuario.statusAnuncio}</p>

                <a href="AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=${listaDoUsuario.idAnuncio}" class="btn btn-primary">Ver Anúncio</a>
            </div>
        </div>
    </c:forEach>
</div>
<!-- Rodapé -->
<%@include file="footer.jsp" %>
