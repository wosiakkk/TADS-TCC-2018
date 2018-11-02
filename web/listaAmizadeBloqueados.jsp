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
  $("#opAmigo").addClass("highlight");
});
</script>

<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">             
        <!-- Usuário Comum -->
        <c:if test="${user.getTipoUsuario()== 2}">
            <div class="col-sm-12">
                <h2>Seus amigos bloqueados: </h2>
                <a href="escolhaAmigo.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
                <hr>
            </div>

            <div class="col-sm-12 list-inline">
                <img class="list-inline-item " src="img\icones\friendship.png">
                <img class="list-inline-item" src="img\icones\handshake.png">
                <img class="list-inline-item" src="img\icones\fist.png">

                <hr>
            </div>

            <div class="jumbotron col-sm-12" >
                <c:set var="listaBloqueados" value="${amigosBloqueados}"/>   
                <c:choose> 
                    <c:when test="${not empty listaBloqueados}">
                        <c:forEach var="listaBloqueados" items="${amigosBloqueados}">                                               
                            <div class="card">   

                                <div class="card-body">
                                    <div class="d-inline-block">
                                        <img src="${listaBloqueados.foto}"  class="img-thumbnail" width="50" height="50">
                                    </div>
                                    <div class="d-inline-block">
                                        <h5 class="card-title center">${listaBloqueados.nome}</h5>
                                    </div>
                                    <div>
                                        <a href="UserServlet?action=PERFIL&idUser=${listaBloqueados.id}" class="btn btn-primary">Ver perfil</a> 
                                        <a href="UserServlet?action=AMIZADE&acao=DESBLOQUEAR&idSessao=${user.getId()}&idSolicitante=${listaBloqueados.id}" class="btn btn-primary">Desbloquear</a> 
                                    </div>                           
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="card">   
                            <div class="card-body">
                                <h4 class="card-title">Você não tem amigos bloqueados :)</h4>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose> 
            </c:if>
        </div>
    </div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>



