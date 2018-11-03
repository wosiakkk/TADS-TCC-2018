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
<link href="assets/css/feed-style.css" rel="stylesheet">

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<!-- Script para dar destaque na opção navegada -->
<script>  
    $(document).ready(function () {
  $("#opAnuncio").addClass("highlight");
});
</script>

<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">             
        <!-- Usuário Comum -->
        <c:if test="${user.getTipoUsuario()== 2}">
            <div class="col-sm-12">
                <h2>Anúncios de ${user.nome}: </h2>
                <a href="escolhaMeusAnuncios.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
                <hr>
            </div>

            <div class="col-sm-12 list-inline">
                <img class="list-inline-item " src="img\icones\house.png">
                <img class="list-inline-item" src="img\icones\chair.png">
                <img class="list-inline-item" src="img\icones\books.png"> 
                <hr>
            </div>
                <c:if test="${fn:length(ListaAunciosDoUusario) == 0}">
                    <h3>Não existem anúncios nesta categoria.</h3>
                </c:if>

            <div class="jumbotron col-sm-12" >
                <c:set var="listaDoUsuario" value="${ListaAunciosDoUusario}"/>
                <c:forEach var="listaDoUsuario" items="${listaDoUsuario}">
                    <div class="card">
                        <h3 class="card-header primary-color white-text">Anuncio ${listaDoUsuario.statusAnuncio}</h3>
                        <div class="card-body">
                            <h4 class="card-title">Descrição: ${listaDoUsuario.descricao}</h4>
                            <p class="card-text">Categoria :${listaDoUsuario.categoria}</p>
                            <p class="card-text">Status do Anúncio :${listaDoUsuario.statusAnuncio}</p>

                            <a href="AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=${listaDoUsuario.idAnuncio}" class="btn btn-primary">Ver Anúncio</a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
