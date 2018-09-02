<%-- 
    Document   : cadastrar
    Created on : 25/03/2018, 18:55:52
    Author     : onurb
--%>

<!-- Cabe�alho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Fa�a login para acessar esta p�gina!"></c:param>
    </c:redirect>
</c:if>
<form class="form-horizontal"  action="AnuncioServlet?action=ADDMATERIAL"  method="POST" role="form" enctype="multipart/form-data">
    <h2>Cadastro de Anuncio: Material</h2>
    <div class="form-group">
        <label for="select" class="col-sm-3 control-label">Selecione o Tipo de Material</label>
        <div class="col-sm-9">
            <select class="selectpicker" name="select" id="select">
                <option value="0">SELECIONE</option>

                <option value="1">Livros Did�ticos</option>
                <option value="2">Livros de Literatura</option>
                <option value="3">Apostilas</option>
                <option value="4">Outros</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for=titulo class="col-sm-3 control-label">Titulo:</label>
        <div class="col-sm-9">
            <input type="text" name="titulo" id="titulo" placeholder="" class="form-control" autofocus required> 
        </div>
    </div>
    <div class="form-group">
        <label for="descricao" class="col-sm-3 control-label">Descri��o:</label>
        <div class="col-sm-9">
            <input type="text" name="descricao" id="descricao" placeholder="" class="form-control" autofocus required> 
            <span class="help-block">Descreva seu anuncio</span>
        </div>
    </div>
    <div class="form-group">
        <label for="valor" class="col-sm-3 control-label">Pre�o:</label>
        <div class="col-sm-6">
            <input type="number" name="valor" id="valor" placeholder="" class="form-control" min="0" autofocus>
            <span class="help-block">Insira um valor caso deseje.</span>
        </div>
    </div>
    <div class="form-group">
        <label for="file" class="col-sm-3 control-label">Selecione uma Imagem</label>
        <div class="col-sm-9">
            <input type="file" name="file" id="file" placeholder="" class="form-control" autofocus required>
        </div>
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
    <script>
        i = 1;
        function myFunction() {
            var x = document.getElementById(i);
            x.style.display = 'inline'
            i++;
        }
    </script>
    <div class="form-group">
        <label for="file" class="col-sm-3 control-label"></label>
        <div class="col-sm-9">
            <button type="button" name="button" id="button" class="form-control" onclick="myFunction()">Adicionar Imagem</button>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-9 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-block">Cadastrar</button>
        </div>
    </div>
    <br><br><br>
</form>   <!-- /form -->
<!-- Rodap� -->
<%@include file="footer.jsp" %>
