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
        <div class="col-md-12 personal-info">
            
            <form id="formEditar" class="form-horizontal" action="UserServlet?action=EDIT"  method="POST" role="form" enctype="multipart/form-data">
        <div  align="center">
            <div class="text-center col-md-6">
                <img src="${userSearch.getFoto()}" class="avatar img-circle" alt="avatar" style="width: 200px; height: 200px">
                <h6>Carregar uma foto nova...</h6>

                <input type="file" name="foto" id="foto" class="form-control">
            </div>
        </div>

        <!-- edit form column -->   
        

            <h3>Informações do perfil</h3>

            
            <input id="idUser" type="hidden" name="idUser" value="${userSearch.id}"/>
            <input id="codEndereco" type="hidden" name="codEndereco" value="${userSearch.cdEndereco}"/>
            <input id="fotoUser" type="hidden" name="fotoUser" value="${userSearch.getFoto()}"/>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Nome Completo:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="nome" id="nome" type="text" value="<c:out value="${userSearch.getNome()}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Telefone Fixo:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="telefone" id="telefone" type="text" value="<c:out value="${userSearch.tel}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Celular:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="celular" id="celular" type="text" value="<c:out value="${userSearch.cel}"/>">
                    </div>
                </div>
                    
                    <h4>Endereço:</h4><br>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Logradouro:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="logradouro" id="logradouro" type="text" value="<c:out value="${userSearch.logradouro}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-2 control-label">Número:</label>
                    <div class="col-lg-2">
                        <input class="form-control" name="numero" id="numero" type="number" value="<c:out value="${userSearch.numero}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Complemento:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="complemento" id="complemento" type="text" value="<c:out value="${userSearch.complemento}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-2 control-label">Cep:</label>
                    <div class="col-lg-2">
                        <input class="form-control" name="cep" id="cep" type="text" value="<c:out value="${userSearch.CEP}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label">Cidade:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="cidade" id="cidade" type="text" value="<c:out value="${userSearch.cidade}"/>">
                    </div>
                </div>
                    <div class="form-group">
                    <label class="col-lg-2 control-label">Estado:</label>
                    <div class="col-lg-1">
                        <input class="form-control" name="estado" id="estado" type="text" value="<c:out value="${userSearch.estado}"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="descricao">Uma breve descrição sobre você:</label>
                    <textarea class="form-control" rows="5" id="descricao" name="descricao" >${userSearch.descricao}</textarea>
                </div>
                   <div class="form-group">
                    <label for="interesses">Interesses ou qualquer outra coisa relacionada a perfil:</label>
                    <textarea class="form-control" rows="5" id="interesses" name="interesses" >${userSearch.interesses}</textarea>
                </div>
                
                <div align="center">
                    <div class="col-md-6 ">
                        <button type="submit" class="btn btn-primary btn-block" onclick="confirm('Deseja mesmo alterar os dados?');">Salvar</button>
                    </div>
                    <div class="col-md-6 ">
                   <br> <a href="home.jsp" class="btn btn-primary btn-block" role="button">Cancelar</a><br>
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


