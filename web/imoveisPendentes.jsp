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
<form class="form-horizontal"  action="AnuncioServlet?action=APROVIMV&id=${imovelBuscado.id}"  method="POST" role="form">
    <h2>Avaliação do Anúncio de ID: ${imovelBuscado.id} </h2>
    <a href="escolhaPendente.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr> 
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${imovelBuscado.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">
                        <div class="carousel-item active">
                            <img src="${lista}" style="height: 400px" alt="Fotos do anuncio">
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="carousel-item">
                            <img src="${lista}" style="height: 400px" alt="Fotos do anuncio">
                        </div>
                    </c:otherwise>
                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>
        </div>          
        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <div class="col-sm-9">
        <label for="titulo">Titulo:</label>
        ${imovelBuscado.titulo}
    </div>

    <div class="col-sm-12">
        <label>Tipo Imovel:</label>
        ${imovelBuscado.tipoDesc}
    </div>
    <div class="col-sm-9">
        <label for="descricao">Descri&ccedil&atildeo:</label>
        ${imovelBuscado.descricao}
    </div>
    <div class="col-sm-9">
        <label for="descricao" >Quantidade de pessoas:</label>
        ${imovelBuscado.quantidade_pessoas}
    </div>
    <div class="col-sm-9">
        <label for="descricao">Pet:</label>
        <c:if test="${imovelBuscado.boolean_pet < 1}">
            NÃ£o
        </c:if>
        <c:if test="${imovelBuscado.boolean_pet == 1}">
            Sim
        </c:if>
    </div>
    <div class="col-sm-9">
        <label for="valor" >Preço:</label>
        <fmt:formatNumber value="${imovelBuscado.preco}" type="currency"/>
    </div>
    <div class="col-sm-6">
        <label for="rua">Logradouro:</label>
        ${imovelBuscado.rua}
    </div>
    <div class="col-sm-3">
        <label for="num">Número:</label>
        ${imovelBuscado.numero}
    </div>
    <div class="col-sm-9">
        <label for="cep" >CEP:</label>
        ${imovelBuscado.cep}
    </div>
    <div class="col-sm-4">
        <label for="cidade" >Cidade:</label>
        ${imovelBuscado.cidade}
    </div>
    <div class="col-sm-2">
        <label for="estado" >Estado:</label>
        ${imovelBuscado.estado}
    </div>
    <div class="col-sm-9">
        <label for="comple" >Complemento:</label>
        ${imovelBuscado.complemento}
    </div>
    <div class="form-group">
        <label for="" class="col-sm-3 control-label">Aprovar:</label>   
        <label class="radio-inline"><input type="radio" name="optradio" value="sim">Sim</label>
        <label class="radio-inline"><input type="radio" name="optradio" value="nao">Não</label>
    </div>
    <div class="form-group">
        <div class="col-sm-9 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
        </div>
    </div>
</form>   <!-- /form -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>


