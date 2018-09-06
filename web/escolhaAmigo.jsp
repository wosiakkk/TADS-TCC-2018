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

<div class="col-sm-4" style="padding-top: 5%">
    <h4>Escolha opção de Amizade:</h4>
    <hr>
</div>

<div class="col-sm-8">
    <div class="container">
        <div class="row" style="padding-top:50px">
            <div class="col-sm-4 text-center">
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
        </div>
    </div>
</div>


<!-- Rodapé -->
<%@include file="footer.jsp" %>
