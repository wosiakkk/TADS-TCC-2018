<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"/>
        <!--c:set var="msg" value="Fa&ccedila login para acessar esta p&aacutegina!" scope="request"/-->
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



<%@include file="footer.jsp" %>