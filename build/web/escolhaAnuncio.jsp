<%-- 
    Document   : escolhaAnuncio
    Created on : 14/04/2018, 14:36:39
    Author     : onurb
--%>

<!-- Cabe�alho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Fa�a login para acessar esta p�gina!"></c:param>
    </c:redirect>
</c:if>

<div class="col-sm-4" style="padding-top: 5%">
    <h4>Escolha uma categoria:</h4>
    <hr>
</div>

<div class="col-sm-8">
    <div class="container">
        <div class="row" style="padding-top:50px">
            <div class="col-sm-4 text-center">
                <div class="list-group">
                    <div class="list-group-item">
                        <a  href="MainPageServlet?action=ANUNCIO&tipo=imovel">
                            <img class="d-block img-fluid" src="img\icones\house.png">Im�vel
                        </a>
                    </div>
                    <div class="list-group-item">
                        <a  href="MainPageServlet?action=ANUNCIO&tipo=movel">
                            <img class="d-block img-fluid" src="img\icones\chair.png">M�vel    
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

<!-- Rodap� -->
<%@include file="footer.jsp" %>
