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
        <div class="col-md-3" align="center">
            <div class="text-center">
                <img src="${perfil.getFoto()}" class="avatar img-circle" alt="avatar" width="200" height="250">
            </div>
            <c:if test="${perfil.id == user.id}">
            <br><a href="UserServlet?action=SEARCH" class="btn btn-primary btn-block" role="button">Editar Perfil</a>
            </c:if>
        </div>

        <!-- edit form column -->
        <div class="col-md-1"></div>
        <div class="col-md-6 personal-info">

            <h3>Informações do perfil</h3>
            
            <div class="card">
                <div class="card-header bg-info text-white">Nome Completo:</div>
                <div class="card-body">${perfil.nome}</div>
            </div>
                <c:if test="${perfil.logradouro != null}">
            <br><div class="card">
                <div class="card-header bg-info text-white">Telefone Fixo:</div>
                <div class="card-body">${userSearch.tel}</div>
            </div>
            <div class="card">
                <div class="card-header bg-info text-white">Celular:</div>
                <div class="card-body">${userSearch.cel}</div>
            </div>
            <br><h4>Endereço:</h4><br>
            <div class="card">
                <div class="card-header bg-info text-white">Logradouro:</div>
                <div class="card-body">${userSearch.logradouro}</div>
                <div class="card-header bg-info text-white">Número:</div>
                <div class="card-body">${userSearch.numero}</div>
                <div class="card-header bg-info text-white">Complemento:</div>
                <div class="card-body">${userSearch.complemento}</div>
                <div class="card-header bg-info text-white">CEP:</div>
                <div class="card-body">${userSearch.CEP}</div>
                <div class="card-header bg-info text-white">Cidade:</div>
                <div class="card-body">${userSearch.cidade}</div>
                <div class="card-header bg-info text-white">Estado:</div>
                <div class="card-body">${userSearch.estado}</div>
            </c:if>
            <br><div class="card">
                <div class="card-header bg-info text-white">Descricao do Usuário:</div>
                <div class="card-body">${perfil.descricao}</div>
            </div>
            <br><div class="card">
                <div class="card-header bg-info text-white">Interesses:</div>
                <div class="card-body">${perfil.interesses}</div>
            </div>
                <c:if test="${perfil.id != user.id}">
                    <div class="col-md-6 personal-info">
                        <br><a href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${perfil.id}"class="btn btn-primary btn-block" role="button">Ver Anuncios</a>
                    </div>            
                    <div class="form-group">
                        <div class="col-sm-9 col-lg-9 col-md-9 col-xs-9 col-sm-offset-3">
                            

                            <c:if test="${amizade == 0}">
                                <a type="btn" href="mensagem.jsp?idDestino=${perfil.id}"> Enviar mensagens</a> <br><hr>
                                <a type="btn" href="UserServlet?action=AMIZADE&idSolicitante=${user.id}&idSolicitado=${perfil.id}&acao=SOLICITAR"> Enviar Pedido de Amizade</a>
                            </c:if>

                            <c:if test="${amizade == 1}">
                                <a type="btn" href="#"> Enviar mensagens</a> <br><hr>
                                <a href="#">Pedido de amizade enviado.</a>   
                            </c:if>    
                            
                            <c:if test="${amizade == 2}">
                                <a type="btn" href="mensagem.jsp?idDestino=${perfil.id}"> Enviar mensagens</a> <br><hr>
                                <a href="UserServlet?action=AMIZADE&idSolicitante=${user.id}&idSolicitado=${perfil.id}&acao=ACEITAR">Aceitar perdido de amizade.</a>   
                            </c:if> 
                              <c:if test="${amizade == 3}">
                                <a type="btn" href="mensagem.jsp?idDestino=${perfil.id}"> Enviar mensagens</a> <br><hr>  
                                <a type="btn" href="UserServlet?action=AMIZADE&acao=EXCLUIR&idSessao=${user.getId()}&idSolicitante=${perfil.id}"> Excluir Amizade</a> <br><hr>  
                                <a type="btn" href="UserServlet?action=AMIZADE&acao=REJEITAREBLOQUEAR&idSessao=${user.getId()}&idSolicitante=${perfil.id}"> Bloquear Amigo</a> <br><hr>  
                            </c:if> 
                            <c:if test="${amizade == 4}">
                                <h5>Você bloqueou esse Usuário</h5>   
                            </c:if>   
                                <c:if test="${amizade == 5}">
                                <h5>Você foi bloqueado por esse usuário</h5>   
                            </c:if>  
                                    
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


