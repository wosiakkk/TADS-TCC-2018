<%-- 
    Document   : preferencias
    Created on : 07/10/2018, 17:12:28
    Author     : Marcos
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>

<script src="assets/js/preferencias.js" type="text/javascript"></script>
<link href="assets/css/feed-style.css" rel="stylesheet">

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<div class="col-9">
    <h1>Defina aqui que tipo de an&uacute;ncios voc&ecirc; quer ver na sua timeline:</h1>
    <hr>
    <form id="formPreferencias" method="POST" role="form">
        <div class="form-group">
            <h3>Que tipo de produto voc&ecirc; prefere</h3>
            <div class="form-check">
                <label for="movel" class="form-check-label">
                    <input id="movel" name="movel" value="1" class="produto form-check-input" type="checkbox">
                M&oacute;veis</label>
            </div>
            <div class="form-check">
                <label for="imovel" class="form-check-label produto">
                    <input id="imovel" name="imovel" value="2" class="produto form-check-input" type="checkbox">
                Im&oacute;veis</label>
            </div>
            <div class="form-check">
                <label for="material" class="form-check-label produto">
                    <input id="material" name="material" value="3" class="produto form-check-input" type="checkbox">
                Material</label>
            </div>
        </div>
        <div id="opcoesMovel" class="form-group">
            <h3>Que tipo de m&oacute;vel você prefere?</h3>
            <div class="form-check">
                <input id="cozinha" name="cozinha" value="1" class="form-check-input" type="checkbox">
                <label for="cozinha" class="form-check-label">M&oacute;veis para cozinha</label>
            </div>
            <div class="form-check">
                <input id="sala" name="sala" value="2" class="form-check-input" type="checkbox">
                <label for="sala" class="form-check-label">M&oacute;veis para sala</label>
            </div>
            <div class="form-check">
                <input id="quarto" name="quarto" value="3" class="form-check-input" type="checkbox">
                <label for="quarto" class="form-check-label">M&oacute;veis para o quarto</label>
            </div>
            <div class="form-check">
                <input id="outros" name="outros" value="4" class="form-check-input" type="checkbox">
                <label for="outros" class="form-check-label">M&oacute;veis para outros c&ocirc;modos</label>
            </div>
        </div>
        <div id="opcoesImovel" class="form-group">
            <h3>O que tipo de im&oacute;vel voc&ecirc; gostaria?</h3>
            <div class="form-check">
                <input id="casa" name="casa" value="1" class="form-check-input" type="checkbox">
                <label for="casa" class="form-check-label">Casa</label>
            </div>
            <div class="form-check">
                <input id="apartamento" name="apartamento" value="2" class="form-check-input" type="checkbox">
                <label for="apartamento" class="form-check-label">Apartamento</label>
            </div>
            <div class="form-check">
                <input id="kitnet" name="kitnet" value="3" class="form-check-input" type="checkbox">
                <label for="kitnet" class="form-check-label">Kitnet</label>
            </div>
            <div class="form-check">
                <input id="sobrado" name="sobrado" value="4" class="form-check-input" type="checkbox">
                <label for="sobrado" class="form-check-label">Sobrado</label>
            </div>
            <div class="form-check">
                <input id="morar_sozinho" name="morar_sozinho" value="0" class="form-check-input" type="checkbox">
                <label for="morar_sozinho" class="form-check-label">Im&oacute;veis para morar sozinho?</label>
            </div>
            <div class="form-check">
                <input id="pets" name="pets" value="0" class="form-check-input" type="checkbox">
                <label for="pets" class="form-check-label">Im&oacute;vel que permitem pets?</label>
            </div>
        </div>
        <div id="opcoesMaterial" class="form-group">
            <h3>Que tipos de materiais voc&ecirc; procura?</h3>
            <div class="form-check">
                <input id="livros_didaticos" name="livros_didaticos" value="1" class="form-check-input" type="checkbox">
                <label for="livros_didaticos" class="form-check-label">Livros Did&aacute;ticos</label>
            </div>
            <div class="form-check">
                <input id="livros_literatura" name="livros_literatura" value="2" class="form-check-input" type="checkbox">
                <label for="livros_literatura" class="form-check-label">Livros de Literatura</label>
            </div>
            <div class="form-check">
                <input id="apostilas" name="apostilas" value="3" class="form-check-input" type="checkbox">
                <label for="apostilas" class="form-check-label">Apostila</label>
            </div>
            <div class="form-check">
                <input id="outros" name="outros" value="4" class="form-check-input" type="checkbox">
                <label for="outros" class="form-check-label">Outros</label>
            </div>
        </div>
        <button type="submit" name="gravar" class="btn btn-outline-dark">Gravar Prefer&ecirc;ncias</button>
        <a href="escolhaPreferencias.jsp">
            <button type="button" name="cancelar" class="btn btn-outline-dark">Cancelar</button>
        </a>
    </form>
</div>