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
        $("#opAprovMod").addClass("highlight");
    });
</script>

<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">
        <div class="col-sm-8">
            <h2>Anúncios de Pendentes: </h2>
             <a href="escolhaPendente.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>
        </div>
        <div class="col-sm-12 list-inline">
            <img class="list-inline-item " src="img\icones\waiting.png">
            <img class="list-inline-item" src="img\icones\settings.png">
            <img class="list-inline-item" src="img\icones\tick.png"> 
            <hr>
        </div>
        <div class="jumbotron col-sm-12" >
            <c:set var="lista" value="${listaMatPendente}"/>
            <c:forEach var="lista" items="${listaMatPendente}">
                <div class="card">
                    <h3 class="card-header primary-color white-text">Anúncio Pendente</h3>
                    <div class="card-body">
                        <h4 class="card-title">Título: ${lista.titulo}</h4>
                        <p class="card-text">Descrição :${lista.descricao}</p>
                        <p class="card-text">Preço: ${lista.preco}</p>
                        <a href="AnuncioServlet?action=BUSCARPORIDMAT&id=${lista.id}" class="btn btn-primary">Verificar</a>
                    </div>
                </div>
            </c:forEach>
        </div>    
    </div>
</div>
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
