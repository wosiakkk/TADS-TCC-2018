<%-- 
    Document   : vendas
    Created on : 25/08/2018, 15:56:33
    Author     : Marcos
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>
<div class="col-lg-12">
    <h1>&Aacute;rea de Vendas</h1>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
</div>
<div id="pageContent" class="row">

</div>

<script type="text/javascript">
    buscarAnunciosAprovados("#pageContent");
</script>
