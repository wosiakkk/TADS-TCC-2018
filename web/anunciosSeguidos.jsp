<%-- 
    Document   : aprovarAnuncio
    Created on : 05/05/2018, 15:28:04
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

<input name="idOculta" type="text" value="${user.getId()}" hidden>
<div class="col-8 menu-fixed-center">
    <div class="panel panel-body">             
        <!-- Usuário Comum -->

        <div class="col-sm-12">
            <h2>Anúncios que você segue: </h2>
            <a href="escolhaMeusAnuncios.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>

            <hr>
        </div>

        <div class="col-sm-12 list-inline">
            <img class="list-inline-item " src="img\icones\shop.png">
            <img class="list-inline-item" src="img\icones\track.png">
            <img class="list-inline-item" src="img\icones\eye-tracking.png">
            <hr>
        </div>

        <div id="notibody" class="jumbotron col-sm-12" >
            <c:set var="listaResumos" value="${listaResumoAnuncios}"/>   
            <c:choose> 
                <c:when test="${not empty listaResumos}">
                    <c:forEach var="listaResumos" items="${listaResumoAnuncios}">                                               
                        <div class="card">   

                            <div class="card-body">                           
                                <div class="d-inline-block">
                                    <h5 class="card-title center"><strong>Descrição: </strong>${listaResumos.descricao}</h5>
                                </div>
                                <div class="d-inline-block">
                                    <c:if test="${listaResumos.statusAnuncio =='Disponível para compra'}">
                                        <h5 class="card-title center" style="color:#218838"><strong>Status: </strong>${listaResumos.statusAnuncio}</h5>
                                    </c:if> 
                                    <c:if test="${listaResumos.statusAnuncio =='Vendido'}">
                                        <h5 class="card-title center" style="color:#d39e00"><strong>Status: </strong>${listaResumos.statusAnuncio}</h5>
                                    </c:if>
                                </div>
                                <hr>
                                <div>
                                    <a href="AnuncioServlet?action=EXIBIRANUNCIO&idAnuncio=${listaResumos.idAnuncio}" class="btn btn-outline-dark">Ver anúncio</a> 
                                    
                                </div>                           
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="card">   
                        <div class="card-body">
                            <h4 class="card-title">Você não está seguindo nenhum anúncio.</h4>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose> 
    </div>
</div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>


