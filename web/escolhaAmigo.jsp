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