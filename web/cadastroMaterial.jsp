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
<div class="form-group col-md-8">
<form class="form-horizontal"  action="AnuncioServlet?action=ADDMATERIAL"  method="POST" role="form" enctype="multipart/form-data">
    <h2>Cadastro de Anuncio: Material</h2>
   
        <div>
        <label for="select" class=" control-label">Selecione o Tipo de Material</label>
            <c:set var="lista" value="${listaCatMaterial}"/>
            <select class="selectpicker form-control" name="select" id="select">
                <option value="0">SELECIONE</option>
                <c:forEach var="lista" items="${lista}">
                    <option value="${lista.id}"> ${lista.descricao}</option>
                </c:forEach>
            </select>
        </div>
    
        <label for=titulo class="control-label">Titulo:</label>
        <div >
            <input type="text" name="titulo" id="titulo" placeholder="" class="form-control" autofocus required> 
        </div>
    
    
        <label for="descricao" class="control-label">Descrição:</label>
        <div >
            <input type="text" name="descricao" id="descricao" placeholder="" class="form-control" autofocus required> 
            <span class="help-block">Descreva seu anuncio</span>
        </div><br>
    
    
        <label for="valor" class="control-label">Preço:</label>
        <div>
            <input type="number" name="valor" id="valor" placeholder="" class="form-control" min="0" step="any" >
            <span class="help-block">Insira um valor.</span>
        </div>    
        
        <br><label for="rua" class="col-sm-3 control-label">Logradouro:</label>
        <div class="col-sm-9">
            <input type="text" name="rua" id="rua" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="num" class="col-sm-3 control-label">Número:</label>
        <div class="col-sm-2">
            <input type="number" name="num" id="num" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="cep" class="col-sm-3 control-label">CEP:</label>
        <div class="col-sm-9">
            <input type="text" name="cep" id="cep" maxlength="8" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="cidade" class="col-sm-3 control-label">Cidade:</label>
        <div class="col-sm-9">
            <input type="text" name="cidade" id="cidade" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="estado" class="col-sm-3 control-label">Estado:</label>
        <div class="col-sm-2">
            <input type="text" name="estado" id="estado" maxlength="2" placeholder="" class="form-control" autofocus required>
        </div>
        <br><label for="comple" class="col-sm-3 control-label">Complemento:</label>
        <div class="col-sm-9">
            <input type="text" name="comple" id="comple" placeholder="" class="form-control" autofocus>
        </div>
        
    
        <br><label for="file" class="control-label">Selecione uma Imagem</label>
        <div >
            <input type="file" name="file" id="file" placeholder="" class="form-control" autofocus required>
        </div>
    
    <div class="form-group" id="1" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div>
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="2" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="3" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <div class="form-group" id="4" style="display:none;">
        <label for="file" class="col-sm-3 control-label"></label> 
        <div >
            <input type="file" name="file"  placeholder="" class="form-control" autofocus >
        </div>
    </div>
    <script>
        i = 1;
        function myFunction() {
            var x = document.getElementById(i);
            x.style.display = 'inline'
            i++;
        }
    </script>
    
        <label for="file" class="control-label"></label>
        <div >
            <button type="button" name="button" id="button" class="btn btn-block btn-dark" onclick="myFunction()">Adicionar Imagem</button>
        </div>
    

    
        <div class="col-sm-offset-3">
            <br><button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
        </div>
        <div>
            <br><a href="home.jsp" class="btn btn-primary btn-block" role="button">Cancelar</a>
        </div>
    
    <br><br><br>
</form>   <!-- /form -->
    </div>
<!-- Rodapé -->
</div>
</div>
</body>
</html>
