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
            <h5>Solicitação de amizade enviada!</h5>
        <a href="home.jsp">Voltar para o Home</a> ou <a href="escolhaAmigo.jsp">Visualizar Lista de Amigos</a><hr>
        <img src="img\logos\logo1.jpg">
        
       </div>
    </div>    
</div>

</body>
</html>
