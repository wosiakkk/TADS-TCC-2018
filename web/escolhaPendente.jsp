<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"/>
        <!--c:set var="msg" value="Fa&ccedila login para acessar esta p&aacutegina!" scope="request"/-->
    </c:redirect>
</c:if>

<link href="assets/css/feed-style.css" rel="stylesheet">  

<!-- Caso o usuário seja Moderador -->       
<c:if test="${user.getTipoUsuario()== 1}">
    <!-- Página com foto e as opções do perfil -->
    <%@include file="opcoes.jsp" %>

    <!-- Script para dar destaque na opção navegada -->
    <script>
        $(document).ready(function () {
            $("#opAprovMod").addClass("highlight");
        });
    </script>

    <div class="col-8 menu-fixed-center">
        <div class="panel panel-body">
            <h4>Escolha uma categoria:</h4>
            <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>
            <div class="container-fluid col-8">                                                         
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARIMOVEISPEND">
                            <img class="d-block img-fluid" src="img\icones\house.png">Imóvel
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARMOVEL">
                            <img class="d-block img-fluid" src="img\icones\chair.png">Móvel    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARMATERIAL">
                            <img class="d-block img-fluid" src="img\icones\books.png">Material
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
</c:if> 

<!-- Caso o usuário seja Administrador --> 
<c:if test="${user.getTipoUsuario()== 3}">

    <!-- Página com foto e as opções do perfil -->
    <%@include file="opcoes.jsp" %>

    <!-- Script para dar destaque na opção navegada -->
    <script>
        $(document).ready(function () {
            $("#opAprovAdm").addClass("highlight");
        });
    </script>

    <div class="col-8 menu-fixed-center">
        <div class="panel panel-body">
            <h4>Escolha uma categoria:</h4>
            <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>
            <div class="container-fluid col-8">                                                         
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARIMOVEISPEND">
                            <img class="d-block img-fluid" src="img\icones\house.png">Imóvel
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARMOVEL">
                            <img class="d-block img-fluid" src="img\icones\chair.png">Móvel    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="AnuncioServlet?action=BUSCARMATERIAL">
                            <img class="d-block img-fluid" src="img\icones\books.png">Material
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
</c:if> 
