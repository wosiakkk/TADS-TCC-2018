<%-- 
    Document   : cadastrar
    Created on : 25/03/2018, 18:55:52
    Author     : onurb
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${not empty user.senha}">
    <div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
        <form id="formulario" class="form-horizontal"  action="UserServlet?action=ALTSENHA"  method="POST" role="form">
            <h2>Alteração de senha.</h2>
            <a href="escolhaPreferencias.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>
            <div class="form-group">
                <label for="senhaAtual" class="col-sm-3 control-label">Senha Atual</label>
                <div class="col-sm-9">
                    <input type="password" name="senhaAtual" id="senhaAtual" placeholder="Senha Atual" class="form-control">
                </div>
                <c:if test="${not empty erroSenha}">
                    <span>Sua senha atual está incorreta!</span>
                </c:if>   

            </div>
            <div class="form-group">
                <label for="novaSenha" class="col-sm-3 control-label">Nova Senha</label>
                <div class="col-sm-9">
                    <input type="password" name="novaSenha" id="novaSenha" placeholder="Nova Senha" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="confsenha" class="col-sm-3 control-label">Confirmar nova senha</label>
                <div class="col-sm-9">
                    <input type="password" name="confsenha" id="confsenha" placeholder="Confirmar Senha" class="form-control" required>
                </div>
            </div>    
            <div class="form-group">
                <div class="col-sm-9 col-sm-offset-3">
                    <button type="submit" class="btn btn-primary btn-block">Alterar</button>
                </div>
            </div>
        </form> <!-- ./form -->
    </div>

    <script type="text/javascript" src="assets\js\validarTrocaSenha.js"></script>
</c:if>
<c:if test="${empty user.senha}">
    <h2>Sua senha é gerenciada pelo Google :)</h2>
</c:if>
<!-- Rodapé -->
<%@include file="footer.jsp" %>
