<%-- 
Document   : cadastrar
Created on : 25/03/2018, 18:55:52
Author     : onurb
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<link href="assets/css/feed-style.css" rel="stylesheet">
<script src="assets\js\seguirAnuncio.js" type="text/javascript"></script>
<script>
    function formatar(mascara, documento) {
        var i = documento.value.length;
        var saida = mascara.substring(0, 1);
        var texto = mascara.substring(i)

        if (texto.substring(0, 1) != saida) {
            documento.value += texto.substring(0, 1);
        }

    }

    function numeros(campo)
    {
        if (isNaN(campo.value.substring(campo.value.length - 1)))
            campo.value = campo.value.substr(0, campo.value.length - 1);
    }
</script>

<!-- Campos escondidos usados pelo ajax de seguir anúncio -->
<input name="idAnuncioOculta" type="text" value="${idExibirAnuncio}" hidden>
<input name="idUserOculta" type="text" value="${idUserSessao}" hidden>

<!-- ############################# ALTERAR IMOVEL ################################ -->
<!-- ############################# ALTERAR IMOVEL ################################ -->
<c:if test="${imovelAlterar != null}">
    <div class="container">       
        <div class="row">
            <div class="col-lg-3">            
                <!-- Página com foto e as opções do perfil -->
                <%@include file="opcoes.jsp" %>
                <!-- Script para dar destaque na opção navegada -->
                <script>
                    $(document).ready(function () {
                        $("#opAnuncio").addClass("highlight");
                    });
                </script>
            </div>            
            <div class="col-lg-9">
                <h2>Alterar Anuncio: ${idExibirAnuncio} </h2>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${imovelAlterar.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${imovelAlterar.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="850" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="850" height="500">
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

                    <div class="col-md-12" align="center">
                        <br><a href="AnuncioServlet?action=ALTERARFOTOSANUNCIO&idAnuncio=${idExibirAnuncio}" class="btn btn-block btn-dark" role="button">Alterar Fotos</a>
                    </div>

                    <form class="form"  action="AnuncioServlet"  method="POST" role="form">
                        <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
                        <input type="hidden" name="idAnuncioImovel"  value="${imovelAlterar.id}" >
                        <input type="hidden" name="tipoAnuncio"  value="imovel" >
                        <div class="form-group">
                            <br><hr>
                            <label for=tipo class="col-sm-3 control-label">Tipo Imovel:</label>
                            <div class="col-sm-9">
                                <c:set var="lista" value="${listaCatImovel}"/>
                                <select class="selectpicker form-control" name="tipo" id="select">
                                    <c:forEach var="lista" items="${lista}">
                                        <option value="${lista.id}" <c:if test="${lista.id == imovelAlterar.tipo}">selected</c:if> > ${lista.descricao}</option>
                                    </c:forEach>
                                </select>
                                <!--<input type="text" name="tipo" id="titulo" value="${imovelAlterar.tipoDesc}" class="form-control"  > !-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for=titulo class="col-sm-3 control-label">Titulo:</label>
                            <div class="col-sm-9">
                                <input type="text" name="titulo" id="titulo" value="${imovelAlterar.titulo}" class="form-control"  > 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="col-sm-3 control-label">Descrição:</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="5" id="descricao" name="descricao">${imovelAlterar.descricao}</textarea>

                                <span class="help-block">Descreva seu anuncio</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="col-sm-3 control-label">O imóvel comporta quantas pessoas?</label>
                            <div class="col-sm-9">
                                <input type="text" name="descricaoPessoas" id="descricao" value="${imovelAlterar.quantidade_pessoas}" class="form-control"  > 

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="col-sm-3 control-label">Pet:</label>
                            <div class="col-sm-9">
                                <label for="opcoes" class="col-sm-10 control-label">É autorizado pets na residencia?:</label>
                                <div id="opcoes">
                                    <label class="radio-inline"><input type="radio" <c:if test="${imovelAlterar.boolean_pet == 1}">checked</c:if> name="descricaoPet" value="1">Sim</label>
                                    <label class="radio-inline"><input  type="radio" <c:if test="${imovelAlterar.boolean_pet < 1}">checked</c:if>  name="descricaoPet" value="0">Não</label>
                                    </div>
                                    <!-- <c:if test="${imovelAlterar.boolean_pet < 1}">
                                         <input type="text" name="descricaoPet" id="descricao" placeholder="NÃ£o" value="0" class="form-control"  >
                                </c:if>
                                <c:if test="${imovelAlterar.boolean_pet == 1}">
                                    <input type="text" name="descricaoPet" id="descricao" placeholder="Sim" value="1" class="form-control"  >
                                </c:if>!-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="valor" class="col-sm-3 control-label">Preço do aluguel:</label>
                            <div class="col-sm-6">
                                <input type="number" name="valor" id="valor" value="${imovelAlterar.preco}" class="form-control" min="0" step="any" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rua" class="col-sm-3 control-label">Logradouro:</label>
                            <div class="col-sm-9">
                                <input type="text" name="rua" id="rua" value="${imovelAlterar.rua}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="num" class="col-sm-3 control-label">Número:</label>
                            <div class="col-sm-2">
                                <input type="number" name="num" id="num" value="${imovelAlterar.numero}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cep" class="col-sm-3 control-label">CEP:</label>
                            <div class="col-sm-9">
                                <input class="form-control" name="cep" id="cep" type="text" maxlength="9" onkeyup="numeros(this)" OnKeyPress="formatar('#####-###', this)" value="${imovelAlterar.cep}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cidade" class="col-sm-3 control-label">Cidade:</label>
                            <div class="col-sm-9">
                                <input type="text" name="cidade" id="cidade" value="${imovelAlterar.cidade}" class="form-control"  >
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="estado" class="col-sm-3 control-label">Estado:</label>
                            <div class="col-sm-2">
                                <input type="text" name="estado" id="estado" value="${imovelAlterar.estado}" class="form-control" maxlength="2" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comple" class="col-sm-3 control-label">Complemento:</label>
                            <div class="col-sm-9">
                                <input type="text" name="comple" id="comple" value="${imovelAlterar.complemento}" class="form-control"  >
                            </div>
                        </div>
                        <hr>
                        <div class="form-group col-md-12">
                            <input type="submit" value="Alterar"  class="form-control btn-primary btn btn-outline-dark" />
                        </div>
                    </form>
                    <div class="form-group col-md-12">
                        <form  action="AnuncioServlet" method="POST"  >
                            <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                            <input type="hidden" name="status"  value="${imovelAlterar.status}" >
                            <input type="submit" value="Cancelar"  class="form-control btn-primary btn btn-outline-dark" />
                        </form>
                    </div>
                </div><!-- /form -->
            </div>
        </div>
    </div>            
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->


<!-- ############################# EXIBIR IMOVEL ################################ -->
<!-- ############################# EXIBIR IMOVEL ################################ -->  
<c:if test="${imovelExibir != null}">
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
                <c:choose>
                    <c:when test="${!(empty(user))}">
                        <h2>${imovelExibir.titulo}</h2><br><hr>
                    </c:when>
                    <c:otherwise>
                        <h2>{imovelExibir.titulo}</h2><br><hr>
                    </c:otherwise>    
                </c:choose>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${imovelExibir.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${imovelExibir.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="900" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="900" height="500">
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
                        <h3 class="card-title">${imovelExibir.titulo}</h3>
                        <h4><fmt:formatNumber value="${imovelExibir.preco}" type="currency"/></h4>
                        <p class="card-text"> ${imovelExibir.descricao}
                        <br><hr>
                        <br><label>Tipo Imovel:</label> ${imovelExibir.tipoDesc}
                        <br><label>Quantidade de pessoas:</label> ${imovelExibir.quantidade_pessoas}
                        <br><label>Aceita Pet:</label>
                        <c:if test="${imovelExibir.boolean_pet < 1}">
                            Não
                        </c:if>
                        <c:if test="${imovelExibir.boolean_pet == 1}">
                            Sim
                        </c:if>
                        <br><label>Logradouro: </label> ${imovelExibir.rua}
                        <br><label>Número: </label> ${imovelExibir.numero}
                        <br><label>CEP: </label> ${imovelExibir.cep}
                        <br><label>Cidade: </label> ${imovelExibir.cidade}
                        <br><label>Estado: </label> ${imovelExibir.estado}
                        <br><label>Complemento: </label> ${imovelExibir.complemento}
                        </p>                       
                    </div>                      
                </div>                                     
                <div class="col-md-12">                    
                </div>
                <c:if test="${!empty(user)}">
                    <c:if test="${imovelExibir.idAnunciante == user.id}">
                        <c:if test="${imovelExibir.status != 5}">
                            <br><div class="form-group">
                                <form class="form-inline" action="AnuncioServlet" method="POST">
                                    <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <c:if test="${imovelExibir.status == 2}">
                                        <input type="submit" value="Marcar Como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${imovelExibir.id}" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    </c:if>
                                </form>                          
                            </div>                          
                        </c:if>                    
                    </c:if>
                    <c:if test="${imovelExibir.idAnunciante != user.id}">
                        <input type="button" value="Seguir anúncio" id="seguirImovel" class=" btn btn-primary col-md-4 btn btn-outline-dark botaoSeguir" readonly="true"/>
                        <a href="UserServlet?action=PERFIL&idUser=${imovelExibir.idAnunciante}">
                            <button type="button" value="Ver Perfil do Anunciante" id="perfilAnunciante" class=" btn btn-md col-md-4 btn-outline-dark botaoSeguir" readonly="true">
                                Ver Perfil do Anunciante
                            </button>
                        </a>
                    </c:if>    
                </c:if>
                <div>
                    <%@include file="comentario.jsp" %>
                </div>
                <div class="col-md-12"></div>
            </div>
        </div>                        
    </div>                          
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->


<!-- ############################# EXIBIR MOVEL ################################ -->
<!-- ############################# EXIBIR MOVEL ################################ -->
<c:if test="${movelExibir != null}">   
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
                <c:choose>
                    <c:when test="${!(empty(user))}">
                        <h2>${movelExibir.titulo}</h2><br><hr>
                    </c:when>
                    <c:otherwise>
                        <h2>{movelExibir.titulo}</h2><br><hr>
                    </c:otherwise>    
                </c:choose>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${movelExibir.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${movelExibir.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="850" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="850" height="500">
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
                        <h3 class="card-title">${movelExibir.titulo}</h3>
                        <h4><fmt:formatNumber value="${movelExibir.preco}" type="currency"/></h4>
                        <p class="card-text"> ${movelExibir.descricao}
                        <br><hr>
                        <br><label>Tipo Móvel:</label> ${movelExibir.tipoDesc}                       
                        <br><label>Logradouro: </label> ${movelExibir.rua}
                        <br><label>Número: </label> ${movelExibir.numero}
                        <br><label>CEP: </label> ${movelExibir.cep}
                        <br><label>Cidade: </label> ${movelExibir.cidade}
                        <br><label>Estado: </label> ${movelExibir.estado}
                        <br><label>Complemento: </label> ${movelExibir.complemento}
                        </p>                       
                    </div>                      
                </div>                                     
                <div class="col-md-12">                    
                </div>
                <c:if test="${!empty(user)}">
                    <c:if test="${movelExibir.idAnunciante == user.id}">
                        <c:if test="${movelExibir.status != 5}">
                            <br><div class="form-group">
                                <form class="form-inline" action="AnuncioServlet" method="POST">
                                    <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <c:if test="${movelExibir.status == 2}">
                                        <input type="submit" value="Marcar como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${movelExibir.id}" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    </c:if>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${movelExibir.idAnunciante != user.id}">
                        <input type="button" value="Seguir anúncio" id="seguirImovel" class=" btn btn-primary col-md-6 btn btn-outline-dark botaoSeguir" readonly="true"/>
                        <a href="UserServlet?action=PERFIL&idUser=${movelExibir.idAnunciante}">
                            <button type="button" value="Ver Perfil do Anunciante" id="perfilAnunciante" class=" btn btn-md col-md-4 btn-outline-dark botaoSeguir" readonly="true">
                                Ver Perfil do Anunciante
                            </button>
                        </a>
                    </c:if>  
                </c:if>
                <div>
                    <%@include file="comentario.jsp" %>
                </div>
                <div class="col-md-12"></div>
            </div>
        </div>                        
    </div>              
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->


<!-- ############################# ALTERAR MOVEL ################################ -->
<!-- ############################# ALTERAR MOVEL ################################ -->    
<c:if test="${movelAlterar != null}"> 
    <div class="container">       
        <div class="row">
            <div class="col-lg-3">            
                <!-- Página com foto e as opções do perfil -->
                <%@include file="opcoes.jsp" %>
                <!-- Script para dar destaque na opção navegada -->
                <script>
                    $(document).ready(function () {
                        $("#opAnuncio").addClass("highlight");
                    });
                </script>
            </div>            
            <div class="col-lg-9">
                <h2>Alterar Anuncio: ${idExibirAnuncio} </h2>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${movelAlterar.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${movelAlterar.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="850" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="850" height="500">
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
                    <div class="col-md-12" align="center">
                        <br><a href="AnuncioServlet?action=ALTERARFOTOSANUNCIO&idAnuncio=${idExibirAnuncio}" class="btn btn-block btn-dark" role="button">Alterar Fotos</a>
                    </div>
                    <form class="form"  action="AnuncioServlet"  method="POST" role="form">
                        <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
                        <input type="hidden" name="idAnuncioMovel"  value="${movelAlterar.id}" >
                        <input type="hidden" name="tipoAnuncio"  value="movel" >
                        <div class="form-group">
                            <br><hr>
                            <label for=tipo class="col-sm-3 control-label">Tipo Movel:</label>
                            <div class="col-sm-9">
                                <c:set var="lista" value="${listaCatMovel}"/>
                                <select class="selectpicker form-control" name="tipo" id="select">
                                    <c:forEach var="lista" items="${lista}">
                                        <option value="${lista.id}" <c:if test="${lista.id == movelAlterar.tipo}">selected</c:if> > ${lista.descricao}</option>
                                    </c:forEach>
                                </select>                               
                            </div>
                        </div>
                        <div class="form-group">
                            <label for=titulo class="col-sm-3 control-label">Titulo:</label>
                            <div class="col-sm-9">
                                <input type="text" name="titulo" id="titulo" value="${movelAlterar.titulo}" class="form-control"  > 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="col-sm-3 control-label">Descrição:</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="5" id="descricao" name="descricao">${movelAlterar.descricao}</textarea>
                                <span class="help-block">Descreva seu anuncio</span>
                            </div>
                        </div>                     
                        <div class="form-group">
                            <label for="valor" class="col-sm-3 control-label">Preço:</label>
                            <div class="col-sm-6">
                                <input type="number" name="valor" id="valor" value="${movelAlterar.preco}" class="form-control" min="0" step="any" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rua" class="col-sm-3 control-label">Logradouro:</label>
                            <div class="col-sm-9">
                                <input type="text" name="rua" id="rua" value="${movelAlterar.rua}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="num" class="col-sm-3 control-label">Número:</label>
                            <div class="col-sm-2">
                                <input type="number" name="num" id="num" value="${movelAlterar.numero}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cep" class="col-sm-3 control-label">CEP:</label>
                            <div class="col-sm-9">
                                <input type="text" name="cep" id="cep" value="${movelAlterar.cep}" class="form-control" maxlength="9" onkeyup="numeros(this)" OnKeyPress="formatar('#####-###', this)" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cidade" class="col-sm-3 control-label">Cidade:</label>
                            <div class="col-sm-9">
                                <input type="text" name="cidade" id="cidade" value="${movelAlterar.cidade}" class="form-control"  >
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="estado" class="col-sm-3 control-label">Estado:</label>
                            <div class="col-sm-2">
                                <input type="text" name="estado" id="estado" value="${movelAlterar.estado}" class="form-control" maxlength="2" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comple" class="col-sm-3 control-label">Complemento:</label>
                            <div class="col-sm-9">
                                <input type="text" name="comple" id="comple" value="${movelAlterar.complemento}" class="form-control"  >
                            </div>
                        </div>
                        <hr>
                        <div class="form-group col-md-12">
                            <input type="submit" value="Alterar"  class="form-control btn-primary btn btn-outline-dark" />
                        </div>
                    </form>
                    <div class="form-group col-md-12">
                        <form  action="AnuncioServlet" method="POST"  >
                            <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                            <input type="hidden" name="status"  value="${movelAlterar.status}" >
                            <input type="submit" value="Cancelar"  class="form-control btn-primary btn btn-outline-dark" />
                        </form>
                    </div>
                </div><!-- /form -->
            </div>
        </div>
    </div>               
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->


<!-- ############################# EXIBIR MATERIAL ################################ -->
<!-- ############################# EXIBIR MATERIAL ################################ -->    
<c:if test="${materialExibir != null}">
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
                <c:choose>
                    <c:when test="${!(empty(user))}">
                        <h2>${materialExibir.titulo}</h2><br><hr>
                    </c:when>
                    <c:otherwise>
                        <h2>{materialExibir.titulo}</h2><br><hr>
                    </c:otherwise>    
                </c:choose>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${materialExibir.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${materialExibir.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="850" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="850" height="500">
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
                        <h3 class="card-title">${materialExibir.titulo}</h3>
                        <h4><fmt:formatNumber value="${materialExibir.preco}" type="currency"/></h4>
                        <p class="card-text"> ${materialExibir.descricao}
                        <br><hr>
                        <br><label>Tipo Material:</label> ${materialExibir.tipoDesc}                       
                        <br><label>Logradouro: </label> ${materialExibir.rua}
                        <br><label>Número: </label> ${materialExibir.numero}
                        <br><label>CEP: </label> ${materialExibir.cep}
                        <br><label>Cidade: </label> ${materialExibir.cidade}
                        <br><label>Estado: </label> ${materialExibir.estado}
                        <br><label>Complemento: </label> ${materialExibir.complemento}
                        </p>                       
                    </div>                      
                </div>                                     
                <div class="col-md-12">                    
                </div>
                <c:if test="${!empty(user)}">
                    <c:if test="${materialExibir.idAnunciante == user.id}">
                        <c:if test="${materialExibir.status != 5}">
                            <br><div class="form-group">
                                <form class="form-inline" action="AnuncioServlet" method="POST">
                                    <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    <c:if test="${materialExibir.status == 2}">
                                        <input type="submit" value="Marcar Como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${materialExibir.id}" class="form-control btn btn-primary col-md-4 btn btn-outline-dark" />
                                    </c:if>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${materialExibir.idAnunciante != user.id}">
                        <input type="button" value="Seguir anúncio" id="seguirImovel" class=" btn btn-primary col-md-6 btn btn-outline-dark botaoSeguir" readonly="true"/>
                        <a href="UserServlet?action=PERFIL&idUser=${materialExibir.idAnunciante}">
                            <button type="button" value="Ver Perfil do Anunciante" id="perfilAnunciante" class=" btn btn-md col-md-4 btn-outline-dark botaoSeguir" readonly="true">
                                Ver Perfil do Anunciante
                            </button>
                        </a>
                    </c:if>
                </c:if>
                <div>
                    <%@include file="comentario.jsp" %>
                </div>
                <div class="col-md-12"></div>
            </div>
        </div>                        
    </div>        
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->


<!-- ############################# ALTERAR MATERIAL ################################ -->
<!-- ############################# ALTERAR MATERIAL ################################ -->    
<c:if test="${materialAlterar != null}">     
    <div class="container">       
        <div class="row">
            <div class="col-lg-3">            
                <!-- Página com foto e as opções do perfil -->
                <%@include file="opcoes.jsp" %>
                <!-- Script para dar destaque na opção navegada -->
                <script>
                    $(document).ready(function () {
                        $("#opAnuncio").addClass("highlight");
                    });
                </script>
            </div>            
            <div class="col-lg-9">
                <h2>Alterar Anuncio: ${idExibirAnuncio} </h2>
                <div class="card mt-4">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <c:forEach var="foto" items="${materialAlterar.fotos}" varStatus="j">
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
                            <c:forEach var="foto" items="${materialAlterar.fotos}" varStatus="i">
                                <c:choose>
                                    <c:when test="${i.index == 0}">
                                        <div class="carousel-item active">
                                            <img src="${foto}" alt="" width="850" height="500">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${foto}" alt="" width="850" height="500">
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

                    <div class="col-md-12" align="center">
                        <br><a href="AnuncioServlet?action=ALTERARFOTOSANUNCIO&idAnuncio=${idExibirAnuncio}" class="btn btn-block btn-dark" role="button">Alterar Fotos</a>
                    </div>

                    <form class="form"  action="AnuncioServlet"  method="POST" role="form">
                        <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
                        <input type="hidden" name="idAnuncioMaterial"  value="${materialAlterar.id}" >
                        <input type="hidden" name="tipoAnuncio"  value="material" >
                        <div class="form-group">
                            <br><hr>
                            <label for=tipo class="col-sm-3 control-label">Tipo Material:</label>
                            <div class="col-sm-9">
                                <c:set var="lista" value="${listaCatMaterial}"/>
                                <select class="selectpicker form-control" name="tipo" id="select">
                                    <c:forEach var="lista" items="${lista}">
                                        <option value="${lista.id}" <c:if test="${lista.id == materialAlterar.tipo}">selected</c:if> > ${lista.descricao}</option>
                                    </c:forEach>
                                </select>                               
                            </div>
                        </div>
                        <div class="form-group">
                            <label for=titulo class="col-sm-3 control-label">Titulo:</label>
                            <div class="col-sm-9">
                                <input type="text" name="titulo" id="titulo" value="${materialAlterar.titulo}" class="form-control"  > 
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="col-sm-3 control-label">Descrição:</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="5" id="descricao" name="descricao">${materialAlterar.descricao}</textarea>
                                <span class="help-block">Descreva seu anuncio</span>
                            </div>
                        </div>                     
                        <div class="form-group">
                            <label for="valor" class="col-sm-3 control-label">Preço:</label>
                            <div class="col-sm-6">
                                <input type="number" name="valor" id="valor" value="${materialAlterar.preco}" class="form-control" min="0" step="any" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rua" class="col-sm-3 control-label">Logradouro:</label>
                            <div class="col-sm-9">
                                <input type="text" name="rua" id="rua" value="${materialAlterar.rua}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="num" class="col-sm-3 control-label">Número:</label>
                            <div class="col-sm-2">
                                <input type="number" name="num" id="num" value="${materialAlterar.numero}" class="form-control"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cep" class="col-sm-3 control-label">CEP:</label>
                            <div class="col-sm-9">
                                <input type="text" name="cep" id="cep" value="${materialAlterar.cep}" class="form-control" maxlength="9" onkeyup="numeros(this)" OnKeyPress="formatar('#####-###', this)" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cidade" class="col-sm-3 control-label">Cidade:</label>
                            <div class="col-sm-9">
                                <input type="text" name="cidade" id="cidade" value="${materialAlterar.cidade}" class="form-control"  >
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="estado" class="col-sm-3 control-label">Estado:</label>
                            <div class="col-sm-2">
                                <input type="text" name="estado" id="estado" value="${materialAlterar.estado}" class="form-control" maxlength="2" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comple" class="col-sm-3 control-label">Complemento:</label>
                            <div class="col-sm-9">
                                <input type="text" name="comple" id="comple" value="${materialAlterar.complemento}" class="form-control"  >
                            </div>
                        </div>
                        <hr>
                        <div class="form-group col-md-12">
                            <input type="submit" value="Alterar"  class="form-control btn-primary btn btn-outline-dark" />
                        </div>
                    </form>
                    <div class="form-group col-md-12">
                        <form  action="AnuncioServlet" method="POST"  >
                            <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                            <input type="hidden" name="status"  value="${materialAlterar.status}" >
                            <input type="submit" value="Cancelar"  class="form-control btn-primary btn btn-outline-dark" />
                        </form>
                    </div>
                </div><!-- /form -->
            </div>
        </div>
    </div>  
</c:if>
<!-- ################################# FIM ##################################### -->
<!-- ################################# FIM ##################################### -->    

</div>
</div> 
</body>
</html> 