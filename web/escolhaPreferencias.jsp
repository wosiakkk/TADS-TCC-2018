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
    <h4>Escolha uma opção de preferência:</h4>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>   
    <hr>
</div>

<div class="col-sm-8">
    <div class="container">
        <div class="row" style="padding-top:50px">
            <div class="col-sm-4 text-center">
                <div class="list-group">
                    <c:if test="${not empty user.senha}">
                        <div class="list-group-item">
                            <a  href="alterarSenha.jsp">
                                <img class="d-block img-fluid" src="img\icones\lock.png">Alterar Senha.
                            </a>
                        </div>
                    </c:if>
                    <div class="list-group-item">
                        <a  href="#">
                            <img class="d-block img-fluid" src="img\icones\timeline.png">Opções de timeline.    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="#">
                            <img class="d-block img-fluid" src="img\icones\security.png">Privacidade.
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Rodapé -->
<%@include file="footer.jsp" %>
