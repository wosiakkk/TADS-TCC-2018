<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:set var="msg" value="Faça login para acessar esta página!" scope="request"/>
    <c:redirect url="index.jsp" />
</c:if>
<div class="container">
    <h1>Perfil de ${perfil.nome}</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="${perfil.getFoto()}" class="avatar img-circle" alt="avatar">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <h3>Informações do perfil</h3>

            <form id="formEditar" class="form-horizontal" action="UserServlet?action=EDIT"  method="POST" role="form">
                
                
                <div class="form-group">
                    <label for="descricaoPerfil">Descrição sobre você:</label>
                    <textarea class="form-control" rows="5" id="descricaoPerfil">${perfil.descricao}</textarea>
                </div>
                   <div class="form-group">
                    <label for="interessesPerfil">Interesses:</label>
                    <textarea class="form-control" rows="5" id="interessesPerfil">${perfil.interesses}</textarea>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-lg-9 col-md-9 col-xs-9 col-sm-offset-3">
                        <h5>botões apenas para visualização, depois faremos algo para integrar mensagens e pedidos de amizade</h5>
                        <button type="submit" class="btn btn-primary btn-block">Enviar mensagens</button>
                        <button type="submit" class="btn btn-primary btn-block">Pedido de amizade</button>
                    </div>
                </div>
            </form>
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


