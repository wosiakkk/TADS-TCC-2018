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
                        <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anuncios</a>
                        <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de vendas</a>
                        <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>
                    </c:if>
                    <c:if test="${user.getTipoUsuario()== 2}">
                        <a class="list-group-item" href="UserServlet?action=PERFIL&idUser=${user.getId()}">Perfil</a>
                        <a class="list-group-item" href="escolhaMeusAnuncios.jsp">Meus anuncios</a> 
                        <a class="list-group-item" href="escolhaAmigo.jsp">Meus Amigos</a>  
                        <a class="list-group-item" href="vendas.jsp">Área de vendas</a>                      
                        <a class="list-group-item" href="UserServlet?action=SEARCH">Editar Perfil</a>
                        <a class="list-group-item" href="escolhaAnuncio.jsp">Realizar um Anuncio</a>           
                        <a class="list-group-item" href="mensagem.jsp">Mensagens</a>
                        <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>
                        <a class="list-group-item" href="gerarRelatorio.jsp">Estatísticas</a>
                    </c:if>
                    <c:if test="${user.getTipoUsuario()== 3}">
                        <a class="list-group-item" href="cadastrarAdm.jsp">Cadastrar um Administrador</a>
                        <a class="list-group-item" href="cadastrarModerador.jsp">Cadastrar um moderador</a>
                        <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anuncios</a> 
                        <a class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a>
                        <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de vendas</a>
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
                    <div class="timeline">

                        <!-- Timeline header -->
                        <div class="timeline-header">
                            <div class="timeline-header-title bg-dark">Now</div>
                        </div>

                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon"><img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Profile picture">
                                </div>
                                <div class="timeline-time">30 Min ago</div>
                            </div>
                            <div class="timeline-label">
                                <p class="mar-no pad-btm"><a href="#" class="btn-link text-semibold">Maria J.</a> shared an image</p>
                                <div class="img-holder">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Image">
                                </div>
                            </div>
                        </div>
                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon bg-danger"><i class="fa fa-building fa-lg"></i>
                                </div>
                                <div class="timeline-time">2 Hours ago</div>
                            </div>
                            <div class="timeline-label">
                                <h4 class="mar-no pad-btm"><a href="#" class="text-danger">Job Meeting</a></h4>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
                            </div>
                        </div>
                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon"><img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="Profile picture">
                                </div>
                                <div class="timeline-time">3 Hours ago</div>
                            </div>
                            <div class="timeline-label">
                                <p class="mar-no pad-btm"><a href="#" class="btn-link text-semibold">Lisa D.</a> commented on <a href="#">The Article</a>
                                </p>
                                <blockquote class="bq-sm bq-open">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</blockquote>
                            </div>
                        </div>
                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon bg-purple"><i class="fa fa-check fa-lg"></i>
                                </div>
                                <div class="timeline-time">5 Hours ago</div>
                            </div>
                            <div class="timeline-label">
                                <img class="img-xs img-circle" src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="Profile picture">
                                <a href="#" class="btn-link text-semibold">Bobby Marz</a> followed you.
                            </div>
                        </div>

                        <!-- Timeline header -->
                        <div class="timeline-header">
                            <div class="timeline-header-title bg-dark">Yesterday</div>
                        </div>

                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon bg-info"><i class="fa fa-envelope fa-lg"></i>
                                </div>
                                <div class="timeline-time">15:45</div>
                            </div>
                            <div class="timeline-label">
                                <h4 class="text-info mar-no pad-btm">Lorem ipsum dolor sit amet</h4>
                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
                            </div>
                        </div>
                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon bg-success"><i class="fa fa-thumbs-up fa-lg"></i>
                                </div>
                                <div class="timeline-time">13:27</div>
                            </div>
                            <div class="timeline-label">
                                <img class="img-xs img-circle" src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="Profile picture">
                                <a href="#" class="btn-link text-semibold">Michael Both</a> Like <a href="#">The Article</a>
                            </div>
                        </div>
                        <div class="timeline-entry">
                            <div class="timeline-stat">
                                <div class="timeline-icon"></div>
                                <div class="timeline-time">11:27</div>
                            </div>
                            <div class="timeline-label">
                                <<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt.</p>
                            </div>
                        </div>
                    </div>
                    <!--===================================================-->
                    <!-- End Timeline -->
                </c:if>
            </div>
        </div> <!-- fim .col-lg-6 -->
        <div class="col-3 panel panel-primary menu-fixed-right">
            <div class="panel-heading">
                <h4>Procurar Usuários</h4>
            </div>  
            <script src="assets/js/procuraDinamica.js" type="text/javascript" charset="UTF-8"></script>
            <div class="container">
                <div class="instruction">
                    <div class="search-container">
                        <div class="ui-widget">
                            <form class="form"  action="RedirecionamentoBusca"  method="POST" role="form">
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
    </div> <!-- ./row -->
</div> <!-- ./container -->
</div>
</div>
<!-- Importando: 1-Bootstrap -->
<script src="assets/bootstrap/js/bootstrap.bundle.js" type="text/javascript"></script>

</body>
</html>


