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


<div class="col-3">
    <div class="menu-fixed-left">
        <div class="center">
            <img class="d-block img-fluid rounded-circle" src="${user.getFoto()}">
            <h4><strong style="color: gray"><c:out value="${user.getNome()}"/></strong></h4>
        </div>
        <div class="list-group">
            <c:if test="${user.getTipoUsuario()== 1}">                  
                <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anuncios</a>
                <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>
            </c:if>
            <c:if test="${user.getTipoUsuario()== 2}">
                <a class="list-group-item" href="UserServlet?action=PERFIL&idUser=${user.getId()}">Visualizar Meu Perfil</a>
                <a class="list-group-item" href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}">Meus anuncios</a> 
                <mark><a class="list-group-item" href="escolhaAmigo.jsp">Meus Amigos</a></mark>
                <a class="list-group-item" href="vendas.jsp">Área de vendas</a>                      
                <a class="list-group-item" href="UserServlet?action=SEARCH">Editar Perfil</a>
                <a class="list-group-item" href="escolhaAnuncio.jsp">Realizar um Anuncio</a>           
                <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>              
            </c:if>
            <c:if test="${user.getTipoUsuario()== 3}">
                <a class="list-group-item" href="cadastrarAdm.jsp">Cadastrar um Administrador</a>
                <a class="list-group-item" href="cadastrarModerador.jsp">Cadastrar um moderador</a>
                <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anuncios</a> 
                <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>
            </c:if>
        </div>
    </div>
</div>
<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">             
        <!-- Usuário Comum -->
        <c:if test="${user.getTipoUsuario()== 2}">
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
                                        <a href="UserServlet?action=PERFIL&idUser=${listaPendentes.id}" class="btn btn-outline-dark">Ver perfil</a> 
                                        <a href="UserServlet?action=AMIZADE&idSolicitante=${user.getId()}&idSolicitado=${listaPendentes.id}&acao=ACEITAR" class="btn btn-outline-dark">Aceitar Amizade</a>
                                        <a href="UserServlet?action=AMIZADE&acao=REJEITAR&idSessao=${user.getId()}&idSolicitante=${listaPendentes.id}" class="btn btn-outline-dark">Rejeitar</a> 
                                        <a href="UserServlet?action=AMIZADE&acao=REJEITAREBLOQUEAR&idSessao=${user.getId()}&idSolicitante=${listaPendentes.id}" class="btn btn-outline-dark">Rejeitar & Bloquear</a> 
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
            </c:if>    
        </div>
    </div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
