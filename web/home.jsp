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

<div class="col-3">
    <div class="menu-fixed-left">
        <div class="center">
            <img class="d-block img-fluid rounded-circle" src="${user.getFoto()}" style="width: 100px; height: 100px">
            <h4><strong style="color: gray"><c:out value="${user.getNome()}"/></strong></h4>
        </div>
        <div class="list-group">
                <c:if test="${user.getTipoUsuario()== 1}">
                    <a class="list-group-item" href="escolhaPendente.jsp">Aprovar Anuncios</a>
                    <a class="list-group-item" href="#">Opção2</a>
                    <a class="list-group-item" href="#">Opção3</a>
                    <a class="list-group-item" href="#">Opção4</a>
                </c:if>
                <c:if test="${user.getTipoUsuario()== 2}">
                    <a class="list-group-item" href="UserServlet?action=PERFIL&idUser=${user.getId()}">Visualizar Meu Perfil</a>
                    <a class="list-group-item" href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}">Meus anuncios</a> 
                    <a class="list-group-item" href="escolhaAmigo.jsp">Meus Amigos</a>  
                    <a class="list-group-item" href="vendas.jsp">Área de vendas</a>                      
                    <a class="list-group-item" href="UserServlet?action=SEARCH">Editar Perfil</a>
                    <a class="list-group-item" href="escolhaAnuncio.jsp">Realizar um Anuncio</a>           
                    <a class="list-group-item" href="#">Preferências</a>              
                </c:if>
                <c:if test="${user.getTipoUsuario()== 3}">
                    <a class="list-group-item" href="#">Cadastrar um moderador</a>
                    <a class="list-group-item" href="#">Opção2</a>
                    <a class="list-group-item" href="#">Opção3</a>
                    <a class="list-group-item" href="#">Opção4</a>
                </c:if>
        </div>
    </div>
</div>
<div class="col-6 menu-fixed-center">
    <div class="panel panel-body">
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
                    <input type="text" id="search" name="search" class="search" placeholder="Procure aqui"/>
                    <button type="submit">Ver Perfil</button>
                      
                    </form>
                </div>   
        </div>
    </div>
</div>

            </div>
<!-- Rodapé -->
<%-- 
    Document   : footer
    Created on : 03/04/2018, 21:03:46
    Author     : Marcos
--%>

</div> <!-- ./row -->
</div> <!-- ./container -->

<!--footer class="py-5 bg-dark">
    <div class="container">
        <p id="copyright" class="m-0 text-center text-white">Copyright &COPY; [[ano]] Mercad&atilde;o do Aluno - SEPT - UFPR</p>
    </div>
</footer-->

<!-- Importando: 1-Bootstrap -->
<script src="assets/bootstrap/js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="assets/js/comentario.js" type="text/javascript" charset="UTF-8"></script>

<script type="text/javascript">
    $(document).ready(function () {
        setCopyright();
    });
</script>
</body>
</html>


