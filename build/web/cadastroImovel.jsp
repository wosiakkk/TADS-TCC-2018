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

<div class="form-group col-md-2"></div>
    <div class="form-group col-md-10">
<form class="form-horizontal"  action="AnuncioServlet?action=ADDIMV"  method="POST" role="form" enctype="multipart/form-data">
    <h2>Cadastro de Anuncio: Imóvel</h2>
        <label for="select" class="col-sm-9 control-label">Qual a categoria do seu Imovel ?</label>
        <div class="col-sm-9">
            <c:set var="lista" value="${listaCatImovel}"/>
            <select class="selectpicker form-control" name="catImovel" id="select">
                <option value="0">SELECIONE</option>
                <c:forEach var="lista" items="${lista}">
                    <option value="${lista.id}"> ${lista.descricao}</option>
                </c:forEach>
            </select>
        </div>
    
        <br><label for=titulo class="col-sm-3 control-label">Titulo:</label>
        <div class="col-sm-9">
            <input type="text" name="titulo" id="titulo" placeholder="" class="form-control" autofocus required> 
        </div>
        <br><label for="descricao" class="col-sm-9 control-label">Descrição:</label>
        <div class="col-sm-9">
            <input type="text" name="descricao" id="descricao" placeholder="" class="form-control" autofocus required> 
            <span class="help-block">Descreva seu anuncio</span>
        </div>
        <br><label for="quantidade" class="col-sm-10 control-label">Quantidade de pessoas que podem dividr:</label>
        <div class="col-sm-2">
            <input type="text" name="quantidade" id="quantidade" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="opcoes" class="col-sm-10 control-label">É autorizado pets na residencia?:</label>
        <div id="opcoes">
            <label class="radio-inline"><input type="radio" name="optpet" value="1">Sim</label>
            <label class="radio-inline"><input type="radio" name="optpet" value="0">Não</label>
        </div>
        <br><label for="valor" class="col-sm-3 control-label">Preço:</label>
        <div class="col-sm-6">
            <input type="number" name="valor" id="valor" placeholder="" class="form-control" step="0.01" autofocus>
            <span class="help-block">Insira um valor caso deseje.</span>
        </div>
    
        <br><label for="rua" class="col-sm-3 control-label">Logradouro:</label>
        <div class="col-sm-9">
            <input type="text" name="rua" id="rua" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="num" class="col-sm-3 control-label">Número:</label>
        <div class="col-sm-2">
            <input type="text" name="num" id="num" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="cep" class="col-sm-3 control-label">CEP:</label>
        <div class="col-sm-9">
            <input type="text" name="cep" id="cep" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="cidade" class="col-sm-3 control-label">Cidade:</label>
        <div class="col-sm-9">
            <input type="text" name="cidade" id="cidade" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="estado" class="col-sm-3 control-label">Estado:</label>
        <div class="col-sm-2">
            <input type="text" name="estado" id="estado" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="comple" class="col-sm-3 control-label">Complemento:</label>
        <div class="col-sm-9">
            <input type="text" name="comple" id="comple" placeholder="" class="form-control" autofocus>
        </div>
        <br><label for="file" class="col-sm-9 control-label">Selecione uma Imagem</label>
        <div class="col-sm-9">
            <input type="file" name="file" id="file" placeholder="" class="form-control" autofocus required>
        </div>
    
    <div class="form-group" id="1" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div class="col-sm-9" >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="2" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div class="col-sm-9" >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="3" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div class="col-sm-9" >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="4" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div class="col-sm-9" >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    
        <br><label for="file" class="col-sm-3 control-label"></label>
        <div class="col-sm-9">
            <button type="button" name="button" id="button" class="btn btn-block btn-dark" onclick="myFunction()">Adicionar Imagem</button>
        </div>
        <div class="col-sm-9 col-sm-offset-3">
            <br><button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
        </div>
        
        <div class="col-sm-9">
            <br><a href="home.jsp" class="btn btn-primary btn-block" role="button">Cancelar</a>
        </div>
    
    <script>
        i = 1;
        function myFunction() {
            var x = document.getElementById(i);
            x.style.display = 'inline';
            i++;
        }
    </script>
</form>   <!-- /form -->
</div> <!-- ./row -->
<div class="col-sm-3"></div>
<!-- Rodapé -->
</div> <!-- ./row -->
</div> <!-- ./container -->
<!-- Importando: 1-Bootstrap -->
<script src="assets/bootstrap/js/bootstrap.bundle.js" type="text/javascript"></script>

</body>
</html>

