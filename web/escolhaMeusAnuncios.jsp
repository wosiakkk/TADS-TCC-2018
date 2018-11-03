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
  $("#opAnuncio").addClass("highlight");
});
</script>

<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">             
        <!-- Usuário Comum -->
        <c:if test="${user.getTipoUsuario()== 2}">
            <div class="col-sm-12" style="padding-top: 5%">
                <h4>Escolha uma categoria:</h4>
                <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
                <hr>
            </div>
            <div class="container-fluid col-8">  
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}&status=2">
                            <img class="d-block img-fluid" src="img\icones\confirmation.png">Aprovados
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}&status=5">
                            <img class="d-block img-fluid" src="img\icones\vendido.png">Vendidos    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}&status=1">
                            <img class="d-block img-fluid" src="img\icones\time.png">Pendentes
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${user.getId()}&status=4">
                            <img class="d-block img-fluid" src="img\icones\negado.png">Rejeitados
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARANUNCIOSEGUIDOS">
                            <img class="d-block img-fluid" src="img\icones\follow.png">Anúncios que você está seguindo
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