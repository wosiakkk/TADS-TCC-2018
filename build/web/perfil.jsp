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

            <form id="formEditar" class="form-horizontal" action="#"  method="POST" role="form">
                
                
                <div class="form-group">
                    <label for="descricaoPerfil">Descrição sobre você:</label>
                    <textarea class="form-control" rows="5" id="descricaoPerfil">${perfil.descricao}</textarea>
                </div>
                   <div class="form-group">
                    <label for="interessesPerfil">Interesses:</label>
                    <textarea class="form-control" rows="5" id="interessesPerfil">${perfil.interesses}</textarea>
                </div>
               <c:if test="${perfil.id != user.id}">
                    <div class="form-group">
                        <div class="col-sm-9 col-lg-9 col-md-9 col-xs-9 col-sm-offset-3">
                            <a type="btn" href="#"> Enviar mensagens</a> <br><hr>
                             <a type="btn" href="UserServlet?action=AMIZADE&idSolicitante=${user.id}&idSolicitado=${perfil.id}"> Enviar Pedido de Amizade</a>
                        </div>
                    </div>
                </c:if>
                    <input type="text" value="${perfil.id}" id="idJs" name="idJs" hidden="">
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


