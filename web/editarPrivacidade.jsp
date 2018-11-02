<%-- 
    Document   : editarPrivacidade
    Created on : Oct 16, 2018, 2:36:52 PM
    Author     : diego.goncalves
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>

<link href="assets/css/feed-style.css" rel="stylesheet">

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<!-- Script para dar destaque na opção navegada -->
<script>
    $(document).ready(function () {
        $("#opPref").addClass("highlight");
        $("#opPrefAdm").addClass("highlight");
        $("#opPrefMod").addClass("highlight");
    });
</script>

<div class="col-8 menu-fixed-center">
    <c:if test="${privacidadeMensagem != null}">
        <h4>${privacidadeMensagem}</h4>
        <c:remove var="privacidadeMensagem"/>
    </c:if>
    <div class="panel panel-body">             
        <!-- Usuário Comum -->    
        <h4>Escolha suas opções de privacidade:</h4>
        <a href="escolhaPreferencias.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>   
        <hr>           
        <div class="container-fluid col-8">  
            <form action="UserServlet" method="POST" id="formPrivacidade" name="formPrivacidade">
                <input type="hidden" name="action" value="EDITARPRIVACIDADE"/>
                <input type="hidden" name="idUser" value="${user.id}"/>
                <c:if test="${privacidade.id != null}">
                    <input type="hidden" name="idPrivacidade" value="${privacidade.id}"/>
                </c:if>
                <div class="list-group">
                    <div class="list-group-item">
                        <h5><strong>Exibir Telefone:</strong></h5>
                        <hr>
                        <label class="radio-inline">
                            <input type="radio" name="radioTelefone" value="1" <c:if test="${privacidade == null || privacidade.id == 0}"> checked </c:if> <c:if test="${privacidade.privacidadeTelefone == 1}"> checked </c:if>>Todos &nbsp; 
                            </label>
                            <label class="radio-inline">
                                    <input type="radio" name="radioTelefone" value="2" <c:if test="${privacidade.privacidadeTelefone == 2}"> checked </c:if> >Somente para amigos  &nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="radioTelefone" value="3" <c:if test="${privacidade.privacidadeTelefone == 3}"> checked </c:if> >Ninguém  
                            </label>
                        </div>

                        <div class="list-group-item">
                            <h5><strong>Exibir Endereço:</strong></h5>
                            <hr>
                            <label class="radio-inline">
                                <input type="radio" name="radioEndereco" value="1" <c:if test="${privacidade == null || privacidade.id == 0}"> checked </c:if> <c:if test="${privacidade.privacidadeEndereco == 1}"> checked </c:if> >Todos &nbsp;
                            </label>
                            <label class="radio-inline">
                                    <input type="radio" name="radioEndereco" value="2" <c:if test="${privacidade.privacidadeEndereco == 2}"> checked </c:if> >Somente para amigos &nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="radioEndereco" value="3" <c:if test="${privacidade.privacidadeEndereco == 3}"> checked </c:if> >Ninguém 
                            </label>
                        </div>
                        <div class="list-group-item">
                            <h5><strong>Exibir Descrição:</strong></h5>
                            <hr>
                            <label class="radio-inline">
                                <input type="radio" name="radioDescricao" value="1" <c:if test="${privacidade == null || privacidade.id == 0}"> checked </c:if> <c:if test="${privacidade.privacidadeDescricao == 1}"> checked </c:if> >Todos &nbsp;
                            </label>
                            <label class="radio-inline">
                                    <input type="radio" name="radioDescricao" value="2" <c:if test="${privacidade.privacidadeDescricao == 2}"> checked </c:if> >Somente para amigos &nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="radioDescricao" value="3" <c:if test="${privacidade.privacidadeDescricao == 3}"> checked </c:if> >Ninguém 
                            </label>
                        </div>
                        <div class="list-group-item">
                            <h5><strong>Exibir Interesses:</strong></h5>
                            <hr>
                            <label class="radio-inline">
                                <input type="radio" name="radioInteresses" value="1" <c:if test="${privacidade == null || privacidade.id == 0}"> checked </c:if> <c:if test="${privacidade.privacidadeInteresses == 1}"> checked </c:if>>Todos &nbsp;
                            </label>
                            <label class="radio-inline">
                                    <input type="radio" name="radioInteresses" value="2" <c:if test="${privacidade.privacidadeInteresses == 2}"> checked </c:if> >Somente para amigos &nbsp;
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="radioInteresses" value="3" <c:if test="${privacidade.privacidadeInteresses == 3}"> checked </c:if> >Ninguém
                            </label>
                        </div>
                    </div> 
                    <br><input type="submit" value="Alterar Privacidade" class="btn bg-dark btn-block"/><br>
                </form>
                <a href="escolhaPreferencias.jsp" class="btn btn-primary btn-block" >Cancelar</a>
            </div>                                               
        </div>
    <c:remove var="privacidade"/>
</div> <!-- fim .col-lg-6 -->
</div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>
