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

<c:if test="${imovelAlterar != null}">   
    <div class="form-group">
<form class="form"  action="AnuncioServlet"  method="POST" role="form">
    <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
    <input type="hidden" name="idAnuncioImovel"  value="${imovelAlterar.id}" >
    <input type="hidden" name="tipoAnuncio"  value="imovel" >
    <h2>Anuncio: ${imovelAlterar.id} </h2>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
    <h2> Fotos:</h2></br></br>
<div class="col-md-12"></div>


    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${imovelAlterar.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
    <div class="form-group">
        <label for=tipo class="col-sm-3 control-label">Tipo Imovel:</label>
        <div class="col-sm-9">
            <c:set var="lista" value="${listaCatImovel}"/>
            <select class="selectpicker" name="tipo" id="select">
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
            <input type="text" name="descricao" id="descricao" value="${imovelAlterar.descricao}" class="form-control"  > 
            <span class="help-block">Descreva seu anuncio</span>
        </div>
    </div>
    <div class="form-group">
        <label for="descricao" class="col-sm-3 control-label">Quantidade de pessoas:</label>
        <div class="col-sm-9">
            <input type="text" name="descricaoPessoas" id="descricao" value="${imovelAlterar.quantidade_pessoas}" class="form-control"  > 
            <span class="help-block">Descreva seu anuncio</span>
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
                <input type="text" name="descricaoPet" id="descricao" placeholder="Não" value="0" class="form-control"  >
            </c:if>
            <c:if test="${imovelAlterar.boolean_pet == 1}">
                <input type="text" name="descricaoPet" id="descricao" placeholder="Sim" value="1" class="form-control"  >
            </c:if>!-->
        </div>
    </div>
    <div class="form-group">
        <label for="valor" class="col-sm-3 control-label">Preço:</label>
        <div class="col-sm-6">
            <input type="number" name="valor" id="valor" value="${imovelAlterar.preco}" class="form-control" min="0" step="any" >

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
            <input type="text" name="num" id="num" value="${imovelAlterar.numero}" class="form-control"  >
        </div>
    </div>
    <div class="form-group">
        <label for="cep" class="col-sm-3 control-label">CEP:</label>
        <div class="col-sm-9">
            <input type="text" name="cep" id="cep" value="${imovelAlterar.cep}" class="form-control"  >
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
            <input type="text" name="estado" id="estado" value="${imovelAlterar.estado}" class="form-control"  >
        </div>
    </div>
    <div class="form-group">
        <label for="comple" class="col-sm-3 control-label">Complemento:</label>
        <div class="col-sm-9">
            <input type="text" name="comple" id="comple" value="${imovelAlterar.complemento}" class="form-control"  >
        </div>
    </div>
<div class="form-group col-md-2">
   <input type="submit" value="Alterar"  class="form-control btn-primary" />
</div>

</form>   <!-- /form -->

<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
        <input type="submit" value="Cancelar"  class="form-control btn-primary" />
    </form>
</div>

</div>
<div class="col-md-12"></div>
</c:if>

<c:if test="${imovelExibir != null}">   

    <h2>Anuncio: ${imovelExibir.id} </h2><br>
    <h2> Fotos:</h2></br></br>
<div class="col-md-12"></div>


    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${imovelExibir.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                                <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
            <div class="col-md-12"></div>
        <div class="col-sm-9">
        <label for="titulo">Titulo:</label>
            ${imovelExibir.titulo}
        </div>
    
        <div class="col-sm-12">
        <label>Tipo Imovel:</label>
            ${imovelExibir.tipoDesc}
        </div>
   
   
   
        <div class="col-sm-9">
        <label for="descricao">Descrição:</label>
            ${imovelExibir.descricao}
            
        </div>
   
    
        <div class="col-sm-9">
        <label for="descricao" >Quantidade de pessoas:</label>
           ${imovelExibir.quantidade_pessoas}
           
        </div>
    
    
        <div class="col-sm-9">
        <label for="descricao">Pet:</label>
            <c:if test="${imovelExibir.boolean_pet < 1}">
                Não
            </c:if>
            <c:if test="${imovelExibir.boolean_pet == 1}">
                Sim
            </c:if>
        </div>
   
    
        <div class="col-sm-9">
        <label for="valor" >Preço:</label>
        <fmt:formatNumber value="${imovelExibir.preco}" type="currency"/>

        </div>
   
    
        <div class="col-sm-6">
        <label for="rua">Logradouro:</label>
            ${imovelExibir.rua}
        </div>
    
    
        <div class="col-sm-3">
        <label for="num">Número:</label>
            ${imovelExibir.numero}
        </div>
    
    
        <div class="col-sm-9">
        <label for="cep" >CEP:</label>
            ${imovelExibir.cep}
        </div>
    
   
        <div class="col-sm-4">
        <label for="cidade" >Cidade:</label>
            ${imovelExibir.cidade}
        </div>
    
    
        <div class="col-sm-2">
        <label for="estado" >Estado:</label>
            ${imovelExibir.estado}
        </div>
    
    
        <div class="col-sm-9">
        <label for="comple" >Complemento:</label>
            ${imovelExibir.complemento}
        </div>
    
   

   <!-- /form -->
<div class="col-md-12"></div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="ALTERARANUNCIO" >
        <br><input type="submit" value="Alterar"  class="form-control btn-primary" />
    </form>
</div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="EXCLUIRANUNCIO" >
        <br><input type="submit" value="Excluir"  class="form-control btn-primary" />
    </form>
</div>
<div class="col-md-12"></div>
</c:if>


<c:if test="${movelExibir != null}">   

    <h2>Anuncio: ${idExibirAnuncio} </h2><br>
    <h2> Fotos:</h2></br></br>
<div class="col-md-12"></div>


    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${movelExibir.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                            <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
            <div class="col-md-12"></div>
        <div class="col-sm-9">
        <label for="titulo">Titulo:</label>
            ${movelExibir.titulo}
        </div>
    
        
   
   
   
        <div class="col-sm-9">
        <label for="descricao">Descrição:</label>
            ${movelExibir.descricao}
            
        </div>
   
    
        
    
    
        
    
        <div class="col-sm-9">
        <label for="valor" >Preço:</label>
        <fmt:formatNumber value="${movelExibir.preco}" type="currency"/>

        </div>
   
    
        
    
   

   <!-- /form -->
<div class="col-md-12"></div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="ALTERARANUNCIO" >
        <br><input type="submit" value="Alterar"  class="form-control btn-primary" />
    </form>
</div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="EXCLUIRANUNCIO" >
        <br><input type="submit" value="Excluir"  class="form-control btn-primary" />
    </form>
</div>
<div class="col-md-12"></div>
</c:if>

<c:if test="${movelAlterar != null}">   
    <div class="form-group">
<form class="form"  action="AnuncioServlet"  method="POST" role="form">
    <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
    <input type="hidden" name="idAnuncioMovel"  value="${movelAlterar.id}" >
    <input type="hidden" name="tipoAnuncio"  value="movel" >
    <h2>Anuncio: ${movelAlterar.id} </h2>
    <h2> Fotos:</h2></br></br>
    <div class="col-md-12"></div>



    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${movelAlterar.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
    
    <div class="form-group">
        <label for=titulo class="col-sm-3 control-label">Titulo:</label>
        <div class="col-sm-9">
            <input type="text" name="titulo" id="titulo" value="${movelAlterar.titulo}" class="form-control"  > 
        </div>
    </div>
    <div class="form-group">
        <label for="descricao" class="col-sm-3 control-label">Descrição:</label>
        <div class="col-sm-9">
            <input type="text" name="descricao" id="descricao" value="${movelAlterar.descricao}" class="form-control"  > 
            <span class="help-block">Descreva seu anuncio</span>
        </div>
    </div>
    
    <div class="form-group">
        <label for="valor" class="col-sm-3 control-label">Preço:</label>
        <div class="col-sm-6">
            <input type="number" name="valor" id="valor" value="${movelAlterar.preco}" class="form-control" min="0" step="any" >

        </div>
    </div>
    
<div class="form-group col-md-12">
   <input type="submit" value="Alterar"  class="form-control btn-primary" />
</div>

</form>   <!-- /form -->

<div class="form-group col-md-12">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
        <input type="submit" value="Cancelar"  class="form-control btn-primary" />
    </form>
</div>

</div>
<div class="col-md-12"></div>
</c:if>



<c:if test="${materialExibir != null}">   

    <h2>Anuncio: ${idExibirAnuncio} </h2><br>
    <h2> Fotos:</h2></br></br>
<div class="col-md-12"></div>


    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${materialExibir.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                            <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
            <div class="col-md-12"></div>
        <div class="col-sm-9">
        <label for="titulo">Titulo:</label>
            ${materialExibir.titulo}
        </div>
    
        
   
   
   
        <div class="col-sm-9">
        <label for="descricao">Descrição:</label>
            ${materialExibir.descricao}
            
        </div>
   
    
        
    
    
        
    
        <div class="col-sm-9">
        <label for="valor" >Preço:</label>
        <fmt:formatNumber value="${materialExibir.preco}" type="currency"/>

        </div>
   
    
        
    
   

   <!-- /form -->
<div class="col-md-12"></div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="ALTERARANUNCIO" >
        <br><input type="submit" value="Alterar"  class="form-control btn-primary" />
    </form>
</div>
<div class="form-group col-md-2">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="EXCLUIRANUNCIO" >
        <br><input type="submit" value="Excluir"  class="form-control btn-primary" />
    </form>
</div>
<div class="col-md-12"></div>
</c:if>

<c:if test="${materialAlterar != null}">   
    <div class="form-group">
<form class="form"  action="AnuncioServlet"  method="POST" role="form">
    <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
    <input type="hidden" name="idAnuncioMovel"  value="${movelAlterar.id}" >
    <input type="hidden" name="tipoAnuncio"  value="movel" >
    <h2>Anuncio: ${movelAlterar.id} </h2>
    <h2> Fotos:</h2></br></br>
    <div class="col-md-12"></div>



    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->

        
        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:set var="counter" value="0" />
            <c:forEach var="lista" items="${materialAlterar.fotos}">
                <c:choose>
                    <c:when test="${counter == 0}">

                        <div class="item active">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:when> 
                    <c:otherwise>
                        <div class="item">
                            <img src="${lista}" alt="Fotos do anuncio">
                            <input type="hidden" name="idFoto"  value="" >
                        </div>
                    </c:otherwise>

                </c:choose>     
                <c:set var="counter" value="${counter+1}" />
            </c:forEach>

        </div>
        </br>
        </br>
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
    
    <div class="form-group">
        <label for=titulo class="col-sm-3 control-label">Titulo:</label>
        <div class="col-sm-9">
            <input type="text" name="titulo" id="titulo" value="${materialAlterar.titulo}" class="form-control"  > 
        </div>
    </div>
    <div class="form-group">
        <label for="descricao" class="col-sm-3 control-label">Descrição:</label>
        <div class="col-sm-9">
            <input type="text" name="descricao" id="descricao" value="${materialAlterar.descricao}" class="form-control"  > 
            <span class="help-block">Descreva seu anuncio</span>
        </div>
    </div>
    
    <div class="form-group">
        <label for="valor" class="col-sm-3 control-label">Preço:</label>
        <div class="col-sm-6">
            <input type="number" name="valor" id="valor" value="${materialAlterar.preco}" class="form-control" min="0" step="any" >

        </div>
    </div>
    
<div class="form-group col-md-12">
   <input type="submit" value="Alterar"  class="form-control btn-primary" />
</div>

</form>   <!-- /form -->

<div class="form-group col-md-12">
    <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
        <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
        <input type="submit" value="Cancelar"  class="form-control btn-primary" />
        <br><br><br><br>
    </form>
</div>

</div>
<div class="col-md-12"></div>
</c:if>




<!-- Rodapé -->
<%@include file="comentario.jsp" %>
<%@include file="footer.jsp" %>
