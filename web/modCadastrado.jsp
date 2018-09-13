<%-- 
    Document   : editarPerfil
    Created on : 09/07/2018, 19:39:31
    Author     : onurb
--%>

<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:set var="msg" value="Faça login para acessar esta página!" scope="request"/>
    <c:redirect url="index.jsp" />
</c:if>

<div class="container">
    <div class="jumbotron col-sm-9 ">
        <div class="col-sm-8">
            <h5>O moderador foi cadastrado!</h5>
        <a href="home.jsp">Voltar para o Home</a> ou <a href="cadastrarModerador.jsp">Cadastrar mais um moderador</a><hr>
        <img src="img\logos\logo1.jpg">
        
       </div>
    </div>    
</div>
    
<script type="text/javascript">
    $(document).ready(function () {
        setCopyright();
    });
</script>
</body>
</html>
