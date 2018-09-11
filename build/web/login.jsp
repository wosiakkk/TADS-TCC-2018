<%-- 
    Document   : login
    Created on : 25/03/2018, 18:49:51
    Author     : onurb
--%>

<!-- Cabe�alho -->
<%@ include file="head.jsp" %>
<div class="col-md-12 col-sm-12 col-xs-12">
    <c:choose>
        <c:when test="${(!empty(falhaLogin))}">
            <div class="alert alert-warning col-lg-12 col-sm-12 col-md-12 col-xs-12" role="alert">
                <h4 class="text-center">
                    Login ou senha inv&aacute;lidos ou n&atilde;o encontrados.<br>
                    Tente novamente ou <a href="MainPageServlet?action=CLIENTE">cadastre-se</a>!
                </h4>
            </div>
        </c:when>
        <c:when test="${(!empty(param.falhaLogin))}">
            <div class="alert alert-warning col-lg-12 col-sm-12 col-md-12 col-xs-12" role="alert">
                <h4 class="text-center">
                    Login ou senha inv&aacute;lidos ou n&atilde;o encontrados.<br>
                    Tente novamente ou <a href="MainPageServlet?action=CLIENTE">cadastre-se</a>!
                </h4>
            </div>
        </c:when>
    </c:choose>
    <br>
    <div class="container">
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm">
                <form id="frmLogin" action="LoginServlet" method="POST">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input name="login" type="email" class="form-control" id="emailLogin" required="true">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Senha:</label>
                        <input name="senha" type="password" class="form-control" id="pwd" required="true">
                    </div>
                    <div class="checkbox">
                        <label><input id="remember" type="checkbox"> Lembre-se de mim</label>
                    </div>
                    <button type="submit" class="btn btn-success">Logar</button>
                </form>
                <small>Voc� tamb�m pode logar com sua conta Google! </small><div class="g-signin2" data-onsuccess="onSignIn"></div>
                <small>Ou, se preferir pode criar uma conta, <a href="MainPageServlet?action=CLIENTE">clique aqui e cadastre-se</a>!</small>
            </div>
            <div class="col-sm"></div>
        </div>
    </div>
</div>
<!-- Script para chamar a API Goolge -->
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token;

        var auth2 = gapi.auth2.getAuthInstance();
        auth2.disconnect();
        console.log('id_token: ' + googleUser.getAuthResponse().id_token);
        var redirectUrl = 'LoginGoogle'
        var form = $('<form action="' + redirectUrl + '" method="post">' +
                '<input type="text" name="id_token" value="' +
                googleUser.getAuthResponse().id_token + '" />' +
                '<input type="text" name="foto" value="' +
                googleUser.getBasicProfile().getImageUrl() + '" />' +
                '</form>');
        $('body').append(form);
        form.submit();
    }
</script>

<!-- Importando plugin JSCookie para trabalhar com cookies do navegador -->
<script src="assets/jquery/plugins/js-cookie.js" type="text/javascript"></script>
<script type="text/javascript">
    var cookieValue = getCookieValue("emailUser");
    if (typeof cookieValue !== "undefined" || cookieValue !== false) {
        $('#remember').prop('checked', 'true');
        //Se já existe um cookie setado, recupera os dados do mesmo e coloca no input
        $("#emailLogin").val(cookieValue);
    }
    //Adiciona evento no formulário de login para salvar o cookie caso 
    $('#frmLogin').submit(function (event) {
        var inputChecked = $('#remember').prop('checked');
        var emailUser = $('#emailLogin').val();
        if (inputChecked && emailUser !== getCookieValue("emailUser")) {
            setCookie("emailUser", emailUser, 365);
        } else {
            //Se o campo "Lembre-se de mim" não estiver marcado então remove o cookie caso exista
            removeCookie("emailUser");
        }
    });
</script>

<!-- Rodap� -->
<%@ include file="footer.jsp" %>
