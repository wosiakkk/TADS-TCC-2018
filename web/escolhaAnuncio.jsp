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
    <h4>Escolha uma categoria:</h4>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
</div>

<div class="col-sm-8">
    <div class="container">
        <div class="row" style="padding-top:50px">
            <div class="col-sm-4 text-center">
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="MainPageServlet?action=ANUNCIO&tipo=imovel">
                            <img class="d-block img-fluid" src="img\icones\house.png">Imóvel
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="MainPageServlet?action=ANUNCIO&tipo=movel">
                            <img class="d-block img-fluid" src="img\icones\chair.png">Móvel    
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="MainPageServlet?action=ANUNCIO&tipo=material">
                            <img class="d-block img-fluid" src="img\icones\books.png">Material
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Rodapé -->
</div>
</div>
</body>
</html>
