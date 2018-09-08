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
    <h1>Edite seu Perfil</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-9 personal-info">
            <form id="formEditar" class="form-horizontal" action="UserServlet?action=EDIT"  method="POST" role="form" enctype="multipart/form-data">
        <div class="col-md-3">
            <div class="text-center">
                <img src="${userSearch.getFoto()}" class="avatar img-circle" alt="avatar" style="width: 200px; height: 200px">
                <h6>Carregar uma foto nova...</h6>

                <input type="file" name="foto" id="foto" class="form-control">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <h3>Informações do perfil</h3>

            
            <input id="idUser" type="hidden" name="idUser" value="${userSearch.id}"/>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Nome Completo:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="nome" id="nome" type="text" value="<c:out value="${user.getNome()}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="descricao">Uma breve descrição sobre você:</label>
                    <textarea class="form-control" rows="5" id="descricao" name="descricao"></textarea>
                </div>
                   <div class="form-group">
                    <label for="interesses">Interesses ou qualquer outra coisa relacionada a perfil:</label>
                    <textarea class="form-control" rows="5" id="interesses" name="interesses"></textarea>
                </div>
                <div class="form-group">
                    <div class="col-sm-9 col-lg-9 col-md-9 col-xs-9 col-sm-offset-3">
                        <button type="submit" class="btn btn-primary btn-block" onclick="confirm('Deseja mesmo alterar os dados?');">Salvar</button>
                    </div>
                </div>
            </form>
        </div>
                    <br><br><br>
    </div>
</div>
<!-- <script type="text/javascript">
    $(document).ready(function () {
        var senha = $('#senha');
        var confirmaSenha = $('#confirmaSenha');
        $('#confirmaSenha').change(function () {
            $(senha).attr('required', 'true');
            $(confirmaSenha).attr('required', 'true');

            if ($(senha).val() !== $(confirmaSenha).val()) {
                $('#alertaSenha').css('display', 'block');
            } else {
                $('#alertaSenha').css('display', 'none');
            }
        });

        $('#formEditar').submit(function (event) {
            if ($(senha).val() !== $(confirmaSenha).val()) {
                $('#alertaSenha').css('display', 'block');
                event.preventDefault();
                $(window).scrollTop($('#alertaSenha').scrollTop());
            }
        });
    });
</script> -->



<script type="text/javascript">
    $(document).ready(function () {
        setCopyright();
    });
</script>
</body>
</html>


