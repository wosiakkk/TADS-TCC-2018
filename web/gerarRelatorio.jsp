<%-- 
    Document   : cadastrar
    Created on : 25/03/2018, 18:55:52
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
        $("#opEst").addClass("highlight");
    });
</script>

<c:if test="${user.getTipoUsuario()== 2}">
    <div class="col-md-9 col-sm-9 col-xs-9 col-lg-9">
        <form id="formulario" class="form-horizontal"  action="UserServlet?action=ALTSENHA"  method="POST" role="form">
            <h2>Gerar estatísticas dos seus anúncios.</h2>
            <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>         
            <div class="col-sm-9 col-sm-offset-3">
                <a class="btn btn-outline-dark" href="#"> Gerar </a> 
            </div>          
            <hr>
        </form> <!-- ./form -->
    </div>
    <script type="text/javascript" src="assets\js\validarTrocaSenha.js"></script>
</c:if>

</div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>   
