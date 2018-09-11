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
    <h2>Solicitações de Amizade: </h2>
    <a href="escolhaAmigo.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
</div>

<div class="col-sm-12 list-inline">
    <img class="list-inline-item " src="img\icones\add-friend.png">
    <img class="list-inline-item" src="img\icones\add-frid.png">
    <img class="list-inline-item" src="img\icones\frien.png">
    
    <hr>
</div>
<div class="jumbotron col-sm-12" >
    <c:set var="listaPendentes" value="${amigosPendentes}"/>   
    <c:choose> 
        <c:when test="${not empty listaPendentes}">
            <c:forEach var="listaPendentes" items="${amigosPendentes}">                                               
                <div class="card">   
                    
                    <div class="card-body">
                        <div class="d-inline-block">
                            <img src="${listaPendentes.foto}"  class="img-thumbnail" width="50" height="50">
                        </div>
                        <div class="d-inline-block">
                            <h5 class="card-title center">${listaPendentes.nome}</h5>
                        </div>
                        <div>
                           <a href="UserServlet?action=PERFIL&idUser=${listaPendentes.id}" class="btn btn-primary">Ver perfil</a> 
                           <a href="UserServlet?action=AMIZADE&idSolicitante=${user.getId()}&idSolicitado=${listaPendentes.id}&acao=ACEITAR" class="btn btn-primary">Aceitar Amizade</a>
                           <a href="UserServlet?action=AMIZADE&acao=REJEITAR&idSessao=${user.getId()}&idSolicitante=${listaPendentes.id}" class="btn btn-primary">Rejeitar</a> 
                           <a href="UserServlet?action=AMIZADE&acao=REJEITAREBLOQUEAR&idSessao=${user.getId()}&idSolicitante=${listaPendentes.id}" class="btn btn-primary">Rejeitar & Bloquear</a> 
                        </div>
                        
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="card">   
                <div class="card-body">
                    <h4 class="card-title">Você não tem solicitações de amizade ):</h4>
                </div>
            </div>
        </c:otherwise>
    </c:choose>    
</div>
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
