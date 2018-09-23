<%-- 
    Document   : escolhaAnuncio
    Created on : 14/04/2018, 14:36:39
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
            <h4>Escolha opção de Amizade:</h4>
            <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>                    
            <hr>           
            <div class="container-fluid col-8">                                                         
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="UserServlet?action=AMIZADE&acao=LISTARPEDIDOS&idUser=${user.getId()}">
                            <img class="d-block img-fluid" src="img\icones\waiting.png">Solicitações
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="UserServlet?action=AMIZADE&acao=LISTARACEITOS&idUser=${user.getId()}">
                            <img class="d-block img-fluid" src="img\icones\agreement.png">Amigos    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="UserServlet?action=AMIZADE&acao=LISTARBLOQUEADOS&idUser=${user.getId()}">
                            <img class="d-block img-fluid" src="img\icones\hold.png">Bloqueadas
                        </a>                                    
                    </div>
                </div>                                                            
            </div>                                    
        </c:if>    
    </div>
</div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>