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

<div class="container">       
    <div class="row">
        <div class="col-lg-3">            
            <!-- Página com foto e as opções do perfil -->
            <%@include file="opcoes.jsp" %>
            <!-- Script para dar destaque na opção navegada -->
            <script>
                $(document).ready(function () {
                    $("#opVendas").addClass("highlight");
                });
            </script>
        </div>            
        <div class="col-lg-9">
            <h2>Avaliação do Anúncio de ID: ${idAnuncio} </h2>
            <a href="escolhaPendente.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr> 
            <div class="card mt-4">
                <div id="demo" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <c:forEach var="foto" items="${movelBuscado.fotos}" varStatus="j">
                            <c:choose>
                                <c:when test="${j.index == 0}">
                                    <li data-target="#demo" data-slide-to="${j.index}" class="active"></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li data-target="#demo" data-slide-to="${j.index}"></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </ul>
                    <!-- The slideshow -->
                    <div class="carousel-inner">
                        <c:forEach var="foto" items="${movelBuscado.fotos}" varStatus="i">
                            <c:choose>
                                <c:when test="${i.index == 0}">
                                    <div class="carousel-item active">
                                        <img src="${foto}" alt="" width="1100" height="500">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="carousel-item">
                                        <img src="${foto}" alt="" width="1100" height="500">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach> 
                    </div>
                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#demo" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#demo" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>
                <div class="card-body">
                    <h3 class="card-title">${movelBuscado.titulo}</h3>
                    <h4><fmt:formatNumber value="${movelBuscado.preco}" type="currency"/></h4>
                    <p class="card-text"> ${movelBuscado.descricao}
                        <br><label>Tipo de Móvel:</label> ${movelBuscado.tipoDesc}                      
                        <br><label>Logradouro: </label> ${movelBuscado.rua}
                        <br><label>Número: </label> ${movelBuscado.numero}
                        <br><label>CEP: </label> ${movelBuscado.cep}
                        <br><label>Cidade: </label> ${movelBuscado.cidade}
                        <br><label>Estado: </label> ${movelBuscado.estado}
                        <br><label>Complemento: </label> ${movelBuscado.complemento}
                    </p>                       
                </div>
            </div>
            <hr>
            <form class="form-horizontal"  action="AnuncioServlet?action=APROVAR&id=${idAnuncio}"  method="POST" role="form">
                <div class="form-group">
                    <label for="" class="col-sm-3 control-label">Aprovar:</label>   
                    <label class="radio-inline"><input type="radio" name="optradio" value="sim">Sim</label>
                    <label class="radio-inline"><input type="radio" name="optradio" checked value="nao">Não</label>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-outline-dark">Confirmar</button>
                    </div>
                </div>
            </form>
            <hr>
        </div>
    </div>
</div>
</div> <!-- ./row -->
<!-- Rodapé -->
</div> <!-- ./container -->
</body>
</html>
