<%-- 
    Document   : upload
    Created on : 28/04/2018, 11:02:53
    Author     : onurb
--%>

<!-- Cabe�alho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Fa�a login para acessar esta p�gina!"></c:param>
    </c:redirect>
</c:if>
<h1>Fa&ccedil;a o upload da sua imagem do an&uacute;ncio</h1>
<form enctype='multipart/form-data' method='POST' action='/myservlet'>
    <div class="form-group">
        <label for="file">Selecione um arquivo:</label>
        <input type='file' NAME='mptest'>
    </div>
    <div class="form-group">
        <button class="btn btn-success" type='submit' VALUE='upload'>Enviar</button>
    </div>
</form>
<!-- Rodap� -->
</div>
</div>
</body>
</html>
