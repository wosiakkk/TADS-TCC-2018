<%-- 
    Document   : teste
    Created on : 24/03/2018, 17:42:30
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

<div class="container">
    <div class="row">
        <div class="col-3">
            <div class="menu-fixed-left">
                <div class="center">
                    <img class="d-block img-fluid rounded-circle" style="width: 100px; height: 100px" src="${user.getFoto()}">
                    <h4><strong style="color: gray"><c:out value="${user.getNome()}"/></strong></h4>
                </div>
                <div class="list-group">
                    <c:if test="${user.getTipoUsuario()== 1}">                  
                        <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anúncios</a>
                        <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>
                    </c:if>
                    <c:if test="${user.getTipoUsuario()== 2}">
                        <a id="opPerfil" class="list-group-item " href="UserServlet?action=PERFIL&idUser=${user.getId()}">Perfil</a>
                        <a id="opPref" class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a> 
                        <a id="opAmigo" class="list-group-item" href="escolhaAmigo.jsp">Meus Amigos</a>
                        <a id="opMsg" class="list-group-item" href="mensagem.jsp">Mensagens</a>
                        <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>                      
                        <a id="opAnuncio" class="list-group-item" href="escolhaMeusAnuncios.jsp">Meus Anúncios</a>
                        <a id="opRanuncio" class="list-group-item" href="escolhaAnuncio.jsp">Realizar um Anúncio</a>
                        <a id="opEst" class="list-group-item" href="gerarRelatorio.jsp">Estatísticas</a>
                    </c:if>
                    <c:if test="${user.getTipoUsuario()== 3}">
                        <a class="list-group-item" href="cadastrarAdm.jsp">Cadastrar um Administrador</a>
                        <a class="list-group-item" href="cadastrarModerador.jsp">Cadastrar um Moderador</a>
                        <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anúncios</a>
                        <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>
                        <a class="list-group-item" href="gerarRelatorioAdm.jsp">Estatísticas</a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-6 menu-fixed-center">
            <div class="panel panel-body">
                <!-- Usuário Moderador -->
                <c:if test="${user.getTipoUsuario()== 1}">           
                    <div class="jumbotron">
                        <hr>
                        <div class="col-6">
                            <img src="img\logos\logo400.jpg" class="rounded border border-dark">
                        </div>
                        <hr>
                    </div>
                </c:if>
                <!-- Usuário Administrador -->
                <c:if test="${user.getTipoUsuario()== 3}">           
                    <div class="jumbotron">
                        <hr>
                        <div class="col-6">
                            <img src="img\logos\logo400.jpg" class="rounded border border-dark">
                        </div>
                        <hr>
                    </div>
                </c:if>
                <!-- Usuário Comum -->
                <c:if test="${user.getTipoUsuario()== 2}">
                    <!-- Timeline -->
                    <!--===================================================-->
                    <div id="loaderTimeline" class="center">
                        <img src="img/icones/loader.gif"/>
                    </div>
                    <div id="timeline_container">
                        
                    </div>
                    <!--===================================================-->
                    <!-- End Timeline -->
                </c:if>
            </div>
        </div> <!-- fim .col-lg-6 -->
        <c:if test="${user.getTipoUsuario()== 2}">
        <div class="col-3 panel panel-primary menu-fixed-right">
            <div class="panel-heading">
                <h4>Procurar Usuários</h4>
            </div>  
            <script src="assets/js/procuraDinamica.js" type="text/javascript" charset="UTF-8"></script>
            <div class="container">
                <div class="instruction">
                    <div class="search-container">
                        <div class="ui-widget">
                            <form id="formBuscaUser" class="form" action="RedirecionamentoBusca" method="POST" role="form">
                                <input id="idSearch" name="idSearch" type="hidden" value="">
                                <input type="text" id="search" name="search" class="search form-control" placeholder="Procure aqui"/>
                                <div id="anchor" class="col-md-12"></div>
                                <button id="btnSearch" name="btnSearch" type="submit" class="btn form-control">Ver Perfil</button>
                            </form>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${(!empty(msg))}">
                        <div class="alert alert-warning col-lg-12 col-sm-12 col-md-12 col-xs-12" role="alert">
                            <p class="text-center"><c:out value="${msg}"/></p>
                        </div>
                    </c:when>
                    <c:when test="${(!empty(param.msg))}">
                        <div class="alert alert-warning col-lg-12 col-sm-12 col-md-12 col-xs-12" role="alert">
                            <h4 class="text-center"><c:out value="${param.msg}"/></h4>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
        </c:if>
    </div> <!-- ./row -->
</div> <!-- ./container -->
</div>
</div>
<!-- Importando: 1-Bootstrap -->
<script src="assets/bootstrap/js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="assets/js/home.js" type="text/javascript"></script>

</body>
</html>


