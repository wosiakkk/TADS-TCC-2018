<%-- 
    Document   : alteraFotoAnuncio
    Created on : 05/10/2018, 23:38:10
    Author     : Diego
--%>

<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:set var="msg" value="Faça login para acessar esta página!" scope="request"/>
    <c:redirect url="index.jsp" />
</c:if>
<link href="assets/css/feed-style.css" rel="stylesheet">

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<!-- Script para dar destaque na opção navegada -->
<script>  
    $(document).ready(function () {
  $("#opPerfil").addClass("highlight");
});
</script>

<c:set var="quantFotos" value="${5 - fn:length(anuncio.fotos)}"/>
<div class="col-md-9">
              <h2 align="center">Alterar Fotos do Anúncio</h2><br>
        <div class="form-group">
            <form class="form-horizontal" name="form" id="form" action="AnuncioServlet?action=UPDATEFOTOSANUNCIO" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" id="action" value="UPDATEFOTOSANUNCIO">
                <input type="hidden" name="idAnuncio" id="idAnuncio" value="${anuncio.idAnuncio}">
                <c:forEach var="foto" varStatus="i" items="${anuncio.fotos}">
                        
                    <div class="row">
                        <div class="col-md-12">
                <input type="hidden" name="idFoto" id="idFoto" value="${foto.idFoto}">
                    <br><img src="${foto.caminho}" alt="" width="850" height="500">
                        </div>
                    <div class="col-md-1">
                        <br><label class="control-label" for="foto${i.index}">Alterar:</label>
                    </div>
                    <div class="col-md-7">
                        <br><input type="file" class="form-control-file border" name="${foto.idFoto}">
                    </div>
                    <div class="checkbox" align="center">
                    <br>ou  <label style="color: crimson"><input type="checkbox" name="checkExcluir" value="${foto.idFoto}"> Excluir</label>
                    </div>
                    </div>
                </c:forEach>
                        <div class="col-md-12" >   
                    <c:if test="${quantFotos > 0}"> 
                        <br><h4 align="center">Adicionar Foto (Limite por anúncio: 5)</h4>
                        <c:forEach begin="1" varStatus="o" end="${quantFotos}">
                            <div class="col-md-7">
                            <br><input type="file" id="${o.index}" class="form-control-file border" <c:if test="${o.index > 1}" > style="display: none" </c:if> name="fotoNova"> 
                            </div>
                        </c:forEach>
                        <div class="col-md-12">
                            <br><button type="button" name="button" id="button" class="btn btn-dark col-md-7" onclick="myFunction()">Adicionar Imagem</button>
                    </div>
                    </c:if>
                    </div>
                    <div class="row" >   
                       
                        <div class="col-md-2" ></div>   
                    <div class="col-md-4" >   
                    <br><input type="submit" class="form-control btn btn-primary" value="Confirmar">
                    </div>
                    <div class="col-md-4" >   
                        <br><a href="javascript:window.history.go(-1)" class="btn btn-primary btn-block" >Cancelar</a>
                    </div>
                    
                    </div>
            </form>
        </div>
        </div>
    <script>
        i = 2;
        function myFunction() {
            var x = document.getElementById(i);
            x.style.display = 'inline';
            i++;
        }
    </script>

</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>



