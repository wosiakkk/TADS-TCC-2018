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
  $("#opPref").addClass("highlight");
  $("#opPrefAdm").addClass("highlight");
  $("#opPrefMod").addClass("highlight");
});
</script>

        <div class="col-8 menu-fixed-center">
            <div class="panel panel-body">             
                <!-- Usuário Comum -->    
                    <h4>Escolha uma opção de preferência:</h4>
                    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>   
                    <hr>           
                    <div class="container-fluid col-8">                                                         
                        <div class="list-group">
                            <c:if test="${not empty user.senha}">
                                <div class="list-group-item">
                                    <a  href="alterarSenha.jsp">
                                        <img class="d-block img-fluid" src="img\icones\lock.png">Alterar Senha
                                    </a>
                                </div>
                            </c:if>
                            <div class="list-group-item">
                                <a  href="UserServlet?action=PRIVACIDADE&idUser=${user.id}">
                                    <img class="d-block img-fluid" src="img\icones\security.png">Privacidade
                                </a>
                            </div>
                        </div>                                                            
                    </div>                                               
            </div>
        </div> <!-- fim .col-lg-6 -->
    </div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
