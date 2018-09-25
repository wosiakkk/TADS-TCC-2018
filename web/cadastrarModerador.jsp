<%-- 
    Document   : cadastrar
    Created on : 25/03/2018, 18:55:52
    Author     : onurb
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %> 

<link href="assets/css/feed-style.css" rel="stylesheet">

<c:if test="${user.getTipoUsuario()== 3}">  
    
<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<!-- Script para dar destaque na opção navegada -->
<script>
    $(document).ready(function () {
        $("#opCadMod").addClass("highlight");
    });
</script>    
        
    
<div class="col-8 menu-fixed-center">
    <form id="formulario" class="form-horizontal"  action="UserServlet?action=ADDMOD"  method="POST" role="form">
        <hr>
        <h2>Cadastro de Moderador</h2>
        <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
        <hr>
        <div class="form-group">
            <label for="nome" class="col-sm-3 control-label">Nome Completo:</label>
            <div class="col-sm-9">
                <input type="text" name="nome" id="nome" placeholder="Nome completo por extenso" class="form-control">               
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input type="email" name="email" id="email" placeholder="Email" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="senha" class="col-sm-3 control-label">Senha</label>
            <div class="col-sm-9">
                <input type="password" name="senha" id="senha" placeholder="Senha" class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <label for="confsenha" class="col-sm-3 control-label">Confirmar senha</label>
            <div class="col-sm-9">
                <input type="password" name="confsenha" id="confsenha" placeholder="Confirmar Senha" class="form-control" required>
            </div>
        </div>    
        <div class="form-group">
            <div class="col-sm-9 col-sm-offset-3">
                <button type="submit" class="btn btn-outline-dark">Cadastrar</button>
            </div>
        </div>
</form> <!-- ./form -->
<script type="text/javascript" src="assets\js\validarCadastro.js"></script>
</div>
</c:if>
<c:if test="${user.getTipoUsuario()!= 3}">
    <h2>Somente Administradores podem acessar essa área</h2>
</c:if>  

<!-- Rodapé -->
<%@include file="footer.jsp" %>
