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

<link href="assets/css/feed-style.css" rel="stylesheet">

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<!-- Script para dar destaque na opção navegada -->
<script>
    $(document).ready(function () {
        $("#opPerfil").addClass("highlight");
    });
</script>

<script>
    function formatar(mascara, documento) {
        var i = documento.value.length;
        var saida = mascara.substring(0, 1);
        var texto = mascara.substring(i)

        if (texto.substring(0, 1) != saida) {
            documento.value += texto.substring(0, 1);
        }

    }

    function numeros(campo)
    {
        if (isNaN(campo.value.substring(campo.value.length - 1)))
            campo.value = campo.value.substr(0, campo.value.length - 1);
    }
</script>

<div class="col-8 menu-fixed-center">
    <h1>Edite seu Perfil:</h1>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
    <div class="container">
        <div class="row">
            <!-- left column -->
            <div class="col-md-9 personal-info">           
                <form id="formEditar" class="form-horizontal" action="UserServlet?action=EDIT"  method="POST" role="form" enctype="multipart/form-data">
                    <div  align="center">
                        <div class="text-center col-md-9">
                            <img src="${userSearch.getFoto()}" class="avatar img-circle" alt="avatar" style="width: 200px; height: 200px">
                            <h6>Carregar uma nova foto...</h6>
                            <input type="file" name="foto" id="foto" class="form-control">
                        </div>
                    </div>    
                    <hr>
                    <h3>Informações do Perfil</h3>
                    <hr>           
                    <input id="idUser" type="hidden" name="idUser" value="${userSearch.id}"/>
                    <input id="codEndereco" type="hidden" name="codEndereco" value="${userSearch.cdEndereco}"/>
                    <input id="fotoUser" type="hidden" name="fotoUser" value="${userSearch.getFoto()}"/>
                    <div class="form-group">

                        <div class="col-lg-12">
                            <label for="nome">Nome Completo:</label>
                            <input class="form-control" name="nome" id="nome" type="text" value="<c:out value="${userSearch.getNome()}"/>">
                        </div>
                    </div>
                    <div class="form-group">                      
                        <div class="col-lg-9">
                            <label>Telefone Fixo:</label>
                            <input class="form-control" name="telefone" id="telefone"  type="text" maxlength="12" onkeyup="numeros(this)" OnKeyPress="formatar('##-####-####', this)" value="<c:out value="${userSearch.tel}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-9">
                            <label>Celular:</label>
                            <input class="form-control" name="celular" id="celular"  type="text" maxlength="13" onkeyup="numeros(this)" OnKeyPress="formatar('##-#####-####', this)" value="<c:out value="${userSearch.cel}"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <label for="descricao">Uma breve descrição sobre você:</label>
                            <textarea class="form-control" rows="5" id="descricao" name="descricao" >${userSearch.descricao}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <label for="interesses">Seus interesses:</label>
                            <textarea class="form-control" rows="5" id="interesses" name="interesses" >${userSearch.interesses}</textarea>
                        </div>
                    </div>            
                    <hr>                   
                    <h4>Endereço:</h4><br>
                    <small>Caso deseje, cadastre o seu endereço</small>
                    <hr>
                    <div class="form-group">                        
                        <div class="col-lg-12">
                            <label>Logradouro:</label>
                            <input class="form-control" name="logradouro" id="logradouro" type="text" value="<c:out value="${userSearch.logradouro}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-3">
                            <label>Número:</label>
                            <input class="form-control" name="numero" id="numero" type="number" value="<c:out value="${userSearch.numero}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-12">
                            <label>Complemento:</label>
                            <input class="form-control" name="complemento" id="complemento" type="text" value="<c:out value="${userSearch.complemento}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-9">
                            <label>CEP:</label>
                            <input class="form-control" name="cep" id="cep" maxlength="9" type="text" onkeyup="numeros(this)" OnKeyPress="formatar('#####-###', this)" value="<c:out value="${userSearch.CEP}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-12">
                            <label>Cidade:</label>
                            <input class="form-control" name="cidade" id="cidade" type="text" value="<c:out value="${userSearch.cidade}"/>">
                        </div>
                    </div>
                    <div class="form-group">                       
                        <div class="col-lg-3">
                            <label>Estado:</label>
                            <input class="form-control" name="estado" id="estado" maxlength="2" type="text" value="<c:out value="${userSearch.estado}"/>">
                        </div>
                    </div>
                    <hr>
                    <div class="col-md-12 ">                  
                        <button type="submit" class="btn btn-outline-dark" onclick="confirm('Deseja mesmo alterar os dados?');">Salvar</button>                                      
                        <a href="home.jsp" class="btn btn-outline-dark" >Cancelar</a>
                    </div>
                    <hr>                             
                </form>           
            </div>                   
        </div>
    </div>
</div>
</div>
</div>
</body>
</html>