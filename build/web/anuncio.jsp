<%-- 
Document   : cadastrar
Created on : 25/03/2018, 18:55:52
Author     : onurb
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>

<c:if test="${imovelAlterar != null}"> 
    
        <div class="col-md-12">
            <div class="form-group">
                <form class="form"  action="AnuncioServlet"  method="POST" role="form" >
                    <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
                    <input type="hidden" name="idAnuncioImovel"  value="${imovelAlterar.id}" >
                    <input type="hidden" name="tipoAnuncio"  value="imovel" >
                    <h2>Anuncio: ${imovelAlterar.id} </h2>
                    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
                    <hr>
                    <h2> Fotos:</h2></br>
                    <div class="col-md-12"></div>
                    <div class="col-md-12">
                        <div class="col-sm-2"></div>
                        <div class="col-md-8">
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
                        </div>
                        </div>
            <div class="form-group">
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
                <label for="descricao" class="col-sm-3 control-label">Descri&ccedil&atildeo:</label>
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
                    <label for="opcoes" class="col-sm-10 control-label">Ã? autorizado pets na residencia?:</label>
                    <div id="opcoes">
                        <label class="radio-inline"><input type="radio" <c:if test="${imovelAlterar.boolean_pet == 1}">checked</c:if> name="descricaoPet" value="1">Sim</label>
                        <label class="radio-inline"><input  type="radio" <c:if test="${imovelAlterar.boolean_pet < 1}">checked</c:if>  name="descricaoPet" value="0">NÃ£o</label>
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
                <label for="valor" class="col-sm-3 control-label">PreÃ§o:</label>
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
                <label for="num" class="col-sm-3 control-label">NÃºmero:</label>
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
            <div class="form-group col-md-12">
                <input type="submit" value="Alterar"  class="form-control btn-primary" />
            </div>
            </form>
            </div><!-- /form -->
            </div>
            <div class="form-group col-md-12">
                <form  action="AnuncioServlet" method="POST" accept-charset="iso-8859-1" >
                    <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                    <input type="submit" value="Cancelar"  class="form-control btn-primary" />
                </form>
            </div>
        
    
    <div class="col-md-12"></div>
</c:if>
<c:if test="${imovelExibir != null}">
    
    <div class="container">
<c:choose>
        <c:when test="${!(empty(user))}">
            <h2>${imovelExibir.titulo}</h2><br><hr>
        </c:when>
        <c:otherwise>
            <h2>{imovelExibir.titulo}</h2><br><hr>
        </c:otherwise>    
    </c:choose>
      <div class="row">

        <div class="col-lg-3">
          <h2 class="my-4">${imovelExibir.nomeAnunciante}</h2>
          <div class="list-group">
              <c:choose>
        <c:when test="${!(empty(user))}">
            <a href="UserServlet?action=PERFIL&idUser=${imovelExibir.idAnunciante}" class="list-group-item">Visitar Perfil</a>
        </c:when>
        <c:otherwise>
            <h2>Anuncio de ${imovelExibir.nomeAnunciante}</h2><br><hr>
        </c:otherwise>    
            </c:choose>
            <a href="#" class="list-group-item">Enviar Mensagem</a>
            <a href="#" class="list-group-item">Opção 3</a>
          </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

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
              <h3 class="card-title">${imovelExibir.titulo}</h3>
              <h4><fmt:formatNumber value="${imovelExibir.preco}" type="currency"/></h4>
              <p class="card-text"> ${imovelExibir.descricao}
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
              <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
              4.0 stars
            </div>
          </div>
          <!-- /.card -->

          
    <!-- /form -->
    <div class="col-md-12"></div>
    <c:if test="${!empty(user)}">
        <c:if test="${imovelExibir.idAnunciante == user.id}">
        <br><div class="form-group">
            <form class="form-inline" action="AnuncioServlet" method="POST">
                <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 " />
                <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4" />
                <input type="submit" value="Marcar Como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${imovelExibir.id}" class="form-control btn btn-primary col-md-4" />
            </form>
        </div>
            </c:if>
    </c:if>
    <div class="col-md-12"></div>
        </div>
        </div>
        </div>
    
</c:if>
    
<c:if test="${movelExibir != null}">   
    
    <div class="container">
<c:choose>
        <c:when test="${!(empty(user))}">
            <h2>${movelExibir.titulo}</h2><br><hr>
        </c:when>
        <c:otherwise>
            <h2>{movelExibir.titulo}</h2><br><hr>
        </c:otherwise>    
    </c:choose>
      <div class="row">

        <div class="col-lg-3">
          <h2 class="my-4">${movelExibir.nomeAnunciante}</h2>
          <div class="list-group">
              <c:choose>
        <c:when test="${!(empty(user))}">
            <a href="UserServlet?action=PERFIL&idUser=${movelExibir.idAnunciante}" class="list-group-item">Visitar Perfil</a>
        </c:when>
        <c:otherwise>
            <h2>Anuncio de ${movelExibir.nomeAnunciante}</h2><br><hr>
        </c:otherwise>    
            </c:choose>
            <a href="#" class="list-group-item">Enviar Mensagem</a>
            <a href="#" class="list-group-item">Opção 3</a>
          </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

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
              <h3 class="card-title">${movelExibir.titulo}</h3>
              <h4><fmt:formatNumber value="${movelExibir.preco}" type="currency"/></h4>
              <p class="card-text"> ${movelExibir.descricao}
        <br><label>Tipo Movel:</label> ${movelExibir.tipoDesc}
        
        
    </p>
              <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
              4.0 stars
            </div>
          </div>
          <!-- /.card -->

          
    <!-- /form -->
    <div class="col-md-12"></div>
    <c:if test="${!empty(user)}">
        <c:if test="${movelExibir.idAnunciante == user.id}">
        <br><div class="form-group">
            <form class="form-inline" action="AnuncioServlet" method="POST">
                <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 " />
                <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4" />
                <input type="submit" value="Marcar como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${movelExibir.id}" class="form-control btn btn-primary col-md-4" />
            </form>
        </div>
        </c:if>
    </c:if>
    <div class="col-md-12"></div>
        </div>
        </div>
        </div>
    
    <div class="col-md-12"></div>
</c:if>
<c:if test="${movelAlterar != null}">   
    <div class="form-group">
        <form class="form"  action="AnuncioServlet"  method="POST" role="form">
            <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
            <input type="hidden" name="idAnuncioMovel"  value="${movelAlterar.id}" >
            <input type="hidden" name="tipoAnuncio"  value="movel" >
            <h2>Anuncio: ${idExibirAnuncio} </h2><br>
            <h2> Fotos:</h2></br></br>
            <div class="col-md-12"></div>
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
            
            <div class="col-sm-9">
                <label for=tipo class="control-label">Tipo Móvel:</label>
                    <c:set var="lista" value="${listaCatMovel}"/>
                    <select class="selectpicker form-control" name="tipo" id="select">
                        <c:forEach var="lista" items="${lista}">
                            <option value="${lista.id}" <c:if test="${lista.id == movelAlterar.tipo}">selected</c:if> > ${lista.descricao}</option>
                        </c:forEach>
                    </select>
                    <!--<input type="text" name="tipo" id="titulo" value="${imovelAlterar.tipoDesc}" class="form-control"  > !-->
                </div>
            <div class="form-group">
                <label for=titulo class="col-sm-3 control-label">Titulo:</label>
                <div class="col-sm-9">
                    <input type="text" name="titulo" id="titulo" value="${movelAlterar.titulo}" class="form-control"  > 
                </div>
            </div>
            <div class="form-group">
                <label for="descricao" class="col-sm-3 control-label">Descri&ccedil&atildeo:</label>
                <div class="col-sm-9">
                    <input type="text" name="descricao" id="descricao" value="${movelAlterar.descricao}" class="form-control"  > 
                    <span class="help-block">Descreva seu anuncio</span>
                </div>
            </div>
            <div class="form-group">
                <label for="valor" class="col-sm-3 control-label">PreÃ§o:</label>
                <div class="col-sm-6">
                    <input type="number" name="valor" id="valor" value="${movelAlterar.preco}" class="form-control" min="0" step="any" >
                </div>
            </div>
            <div class="form-group col-md-12">
                <input type="submit" value="Alterar"  class="form-control btn-primary" />
            </div>
        </form>   <!-- /form -->
        <c:if test="${!empty(user)}">
            <div class="form-group col-md-12">
                <form  action="AnuncioServlet" method="POST">
                    <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                    <input type="submit" value="Cancelar"  class="form-control btn-primary" />
                </form>
            </div>
        </c:if>
    </div>
    <div class="col-md-12"></div>
</c:if>
<c:if test="${materialExibir != null}">
    
     <div class="container">
<c:choose>
        <c:when test="${!(empty(user))}">
            <h2>${materialExibir.titulo}</h2><br><hr>
        </c:when>
        <c:otherwise>
            <h2>{materialExibir.titulo}</h2><br><hr>
        </c:otherwise>    
    </c:choose>
      <div class="row">

        <div class="col-lg-3">
          <h2 class="my-4">${materialExibir.nomeAnunciante}</h2>
          <div class="list-group">
              <c:choose>
        <c:when test="${!(empty(user))}">
            <a href="UserServlet?action=PERFIL&idUser=${materialExibir.idAnunciante}" class="list-group-item">Visitar Perfil</a>
        </c:when>
        <c:otherwise>
            <h2>Anuncio de ${materialExibir.nomeAnunciante}</h2><br><hr>
        </c:otherwise>    
            </c:choose>
            <a href="#" class="list-group-item">Enviar Mensagem</a>
            <a href="#" class="list-group-item">Opção 3</a>
          </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

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
              <h3 class="card-title">${materialExibir.titulo}</h3>
              <h4><fmt:formatNumber value="${materialExibir.preco}" type="currency"/></h4>
              <p class="card-text"> ${materialExibir.descricao}
        <br><label>Tipo Material:</label> ${materialExibir.tipoDesc}
        
        
    </p>
              <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
              4.0 stars
            </div>
          </div>
          <!-- /.card -->

          
    <!-- /form -->
    <div class="col-md-12"></div>
    <c:if test="${!empty(user)}">
        <c:if test="${materialExibir.idAnunciante == user.id}">
        <br><div class="form-group">
            <form class="form-inline" action="AnuncioServlet" method="POST">
                <input type="submit" value="Alterar" formaction="AnuncioServlet?action=ALTERARANUNCIO" class="form-control btn btn-primary col-md-4 " />
                <input type="submit" value="Excluir" formaction="AnuncioServlet?action=EXCLUIRANUNCIO" class="form-control btn btn-primary col-md-4" />
                <input type="submit" value="Marcar Como Vendido" formaction="AnuncioServlet?action=INFORMARVENDAANUNCIO&idAnuncio=${materialExibir.id}" class="form-control btn btn-primary col-md-4" />
            </form>
        </div>
        </c:if>
    </c:if>
    <div class="col-md-12"></div>
        </div>
        </div>
        </div>
    
    <div class="col-md-12"></div>
    
   
</c:if>
<c:if test="${materialAlterar != null}">   
    <div class="form-group">
        <form class="form"  action="AnuncioServlet"  method="POST" role="form">
            <input type="hidden" name="action"  value="ALTERARANUNCIOID" >
            <input type="hidden" name="idAnuncioMaterial"  value="${materialAlterar.id}" >
            <input type="hidden" name="tipoAnuncio"  value="material" >
            <h2>Anuncio: ${idExibirAnuncio} </h2><br>
            <h2> Fotos:</h2></br></br>
            <div class="col-md-12"></div>
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
            <div class="col-sm-9">
                    <c:set var="lista" value="${listaCatMaterial}"/>
                    <select class="selectpicker form-control" name="tipo" id="select">
                        <c:forEach var="lista" items="${lista}">
                            <option value="${lista.id}" <c:if test="${lista.id == materialAlterar.tipo}">selected</c:if> > ${lista.descricao}</option>
                        </c:forEach>
                    </select>
                    <!--<input type="text" name="tipo" id="titulo" value="${imovelAlterar.tipoDesc}" class="form-control"  > !-->
                </div>
                <div class="form-group">
                    <label for=titulo class="col-sm-3 control-label">Titulo:</label>
                    <div class="col-sm-9">
                        <input type="text" name="titulo" id="titulo" value="${materialAlterar.titulo}" class="form-control"  > 
                </div>
            </div>
            <div class="form-group">
                <label for="descricao" class="col-sm-3 control-label">Descri&ccedil&atildeo:</label>
                <div class="col-sm-9">
                    <input type="text" name="descricao" id="descricao" value="${materialAlterar.descricao}" class="form-control"  > 
                    <span class="help-block">Descreva seu anuncio</span>
                </div>
            </div>
            <div class="form-group">
                <label for="valor" class="col-sm-3 control-label">PreÃ§o:</label>
                <div class="col-sm-6">
                    <input type="number" name="valor" id="valor" value="${materialAlterar.preco}" class="form-control" min="0" step="any" >
                </div>
            </div>
            <div class="form-group col-md-12">
                <input type="submit" value="Alterar"  class="form-control btn-primary" />
            </div>
        </form>   <!-- /form -->
        <c:if test="${!empty(user)}">
            <div class="form-group col-md-12">
                <form  action="AnuncioServlet" method="POST">
                    <input type="hidden" name="action"  value="CANCELARALTERARANUNCIO" >
                    <input type="submit" value="Cancelar"  class="form-control btn-primary" />
                    <br><br><br><br>
                </form>
            </div>
        </c:if>
    </div>
    <div class="col-md-12"></div>
</c:if>




<!-- Rodapé -->
<%@include file="comentario.jsp" %>
 <!-- ./row -->
</div> <!-- ./container -->
</body>
</html> 