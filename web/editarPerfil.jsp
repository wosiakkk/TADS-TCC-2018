<%-- 
    Document   : editarPerfil
    Created on : 09/07/2018, 19:39:31
    Author     : onurb
--%>

<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:set var="msg" value="Fa�a login para acessar esta p�gina!" scope="request"/>
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
        

            <h3>Informa��es do perfil</h3>

            
            <input id="idUser" type="hidden" name="idUser" value="${userSearch.id}"/>
            <input id="codEndereco" type="hidden" name="codEndereco" value="${userSearch.cdEndereco}"/>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Nome Completo:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="nome" id="nome" type="text" value="<c:out value="${user.getNome()}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Telefone Fixo:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="telefone" id="telefone" type="text" value="<c:out value="${user.tel}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Celular:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="celular" id="celular" type="text" value="<c:out value="${user.cel}"/>">
                    </div>
                </div>
                    
                    <h4>Endere�o:</h4><br>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Logradouro:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="logradouro" id="logradouro" type="text" value="<c:out value="${user.logradouro}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">N�mero:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="numero" id="numero" type="number" value="<c:out value="${user.numero}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Complemento:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="complemento" id="complemento" type="text" value="<c:out value="${user.complemento}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Cep:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="cep" id="cep" type="text" value="<c:out value="${user.CEP}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Cidade:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="cidade" id="cidade" type="text" value="<c:out value="${user.cidade}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Estado:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="estado" id="estado" type="text" value="<c:out value="${user.estado}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="descricao">Uma breve descri��o sobre voc�:</label>
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
</div>
</div>
</body>
</html>


