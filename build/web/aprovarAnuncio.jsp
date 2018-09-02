<%-- 
    Document   : aprovarAnuncio
    Created on : 05/05/2018, 15:28:04
    Author     : onurb
--%>
<!-- Cabe�alho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Fa�a login para acessar esta p�gina!"></c:param>
    </c:redirect>
</c:if>
<c:set var="listaImovel" value="${listaImovel}"/>
<c:forEach var="listaImovel" items="${listaImovel}">
    <div class="card">
        <h3 class="card-header primary-color white-text">Anuncio Pendente</h3>
        <div class="card-body">
            <h4 class="card-title">T�tulo: ${listaImovel.titulo}</h4>
            <p class="card-text">Descri��o :${listaImovel.descricao}</p>
            <p class="card-text">Pre�o: ${listaImovel.preco}</p>
            <a href="AnuncioServlet?action=BUSCARPORID&id=${listaImovel.id}" class="btn btn-primary">Veja mais</a>
        </div>
    </div>
</c:forEach>

<!-- Rodap� -->
<%@include file="footer.jsp" %>
