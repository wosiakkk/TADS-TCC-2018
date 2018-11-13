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

<div class="col-sm-9">
    <div class="row">        
        <div class="col-md-9 personal-info">
            <h3>Informações do perfil:</h3>
            <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
            <hr>
            <div>
                <img class="d-block img-fluid rounded-circle" style="width: 200px; height: 200px" src="${perfil.getFoto()}"><hr>

                <c:choose>
                    <c:when test="${perfil.id == 1 || perfil.id == 2}">
                        <a class="btn"> <p class="font-weight-bold">Você não tem interações com a moderação!</p></a>                           
                    </c:when>
                    <c:when test="${perfil.id != user.id}">
                        <br><a href="AnuncioServlet?action=BUSCAANUNCIOUSER&idUsr=${perfil.id}&status=2&nome=${perfil.nome}" class="btn btn-outline-dark">Ver Anúncios</a>                       
                        <c:if test="${amizade == 0}">
                            <a class="btn btn-outline-dark" href="mensagem.jsp?idDestino=${perfil.id}"> Enviar Mensagens</a> 
                            <a class="btn btn-outline-dark" href="UserServlet?action=AMIZADE&idSolicitante=${user.id}&idSolicitado=${perfil.id}&acao=SOLICITAR"> Enviar Pedido de Amizade</a>
                        </c:if>

                        <c:if test="${amizade == 1}">
                            <a class="btn btn-outline-dark" href="#"> Enviar Mensagens</a>
                            <a class="btn"> <p class="font-weight-bold">Pedido de amizade enviado!</p></a>              
                        </c:if>    

                        <c:if test="${amizade == 2}">
                            <a class="btn btn-outline-dark" href="mensagem.jsp?idDestino=${perfil.id}" role="button"> Enviar Mensagens</a>
                            <a class="btn btn-outline-dark"href="UserServlet?action=AMIZADE&idSolicitante=${user.id}&idSolicitado=${perfil.id}&acao=ACEITAR">Aceitar perdido de amizade</a>   
                        </c:if> 
                        <c:if test="${amizade == 3}">
                            <a class="btn btn-outline-dark" href="mensagem.jsp?idDestino=${perfil.id}"> Enviar Mensagens</a> 
                            <a class="btn btn-outline-dark" href="UserServlet?action=AMIZADE&acao=EXCLUIR&idSessao=${user.getId()}&idSolicitante=${perfil.id}"> Excluir Amizade</a>  
                            <a class="btn btn-outline-dark" href="UserServlet?action=AMIZADE&acao=REJEITAREBLOQUEAR&idSessao=${user.getId()}&idSolicitante=${perfil.id}"> Bloquear Amigo</a>  
                        </c:if> 
                        <c:if test="${amizade == 4}">
                            <a class="btn"> <p class="font-weight-bold">Você bloqueou esse Usuário!</p></a>

                        </c:if>   
                        <c:if test="${amizade == 5}">
                            <a class="btn"> <p class="font-weight-bold">Você foi bloqueado por esse usuário!</p></a>                           
                        </c:if> 
                    </c:when>
                </c:choose>
            </div>
            <br>
            <div class="col-12">
                <c:if test="${perfil.id == user.id}">
                    <hr>
                    <br><a href="UserServlet?action=SEARCH" class="btn btn-outline-dark">Editar Perfil</a>                    
                    <br>
                    <div class="card">
                        <div class="card-header text-white">Nome Completo:</div>
                        <div class="card-body">${perfil.nome}</div>
                    </div>
                    <div class="card">
                        <div class="card-header text-white">Descrição do Usuário:</div>
                        <div class="card-body">${perfil.descricao}</div>
                    </div>
                    <div class="card">
                        <div class="card-header text-white">Interesses:</div>
                        <div class="card-body">${perfil.interesses}</div>
                    </div>
                    <c:if test="${perfil.logradouro != null}">
                        <br><div class="card">
                            <div class="card-header text-white">Telefone Fixo:</div>
                            <div class="card-body">${perfil.tel}</div>
                        </div>
                        <div class="card">
                            <div class="card-header text-white">Celular:</div>
                            <div class="card-body">${perfil.cel}</div>
                        </div>

                        <br><h4>Endereço:</h4><br>
                        <div class="card">
                            <div class="card-header text-white">Logradouro:</div>
                            <div class="card-body">${perfil.logradouro}</div>
                            <div class="card-header text-white">Número:</div>
                            <div class="card-body">${perfil.numero}</div>
                            <div class="card-header text-white">Complemento:</div>
                            <div class="card-body">${perfil.complemento}</div>
                            <div class="card-header text-white">CEP:</div>
                            <div class="card-body">${perfil.CEP}</div>
                            <div class="card-header text-white">Cidade:</div>
                            <div class="card-body">${perfil.cidade}</div>
                            <div class="card-header text-white">Estado:</div>
                            <div class="card-body">${perfil.estado}</div>
                        </c:if>
                    </c:if>
                </div>
                <hr>
                <c:if test="${perfil.id != user.id}">
                    <div class="card">
                        <div class="card-header text-white">Nome Completo:</div>
                        <div class="card-body">${perfil.nome}</div>
                    </div>
                    <c:if test="${privacidade.privacidadeDescricao != 3 || privacidade.id == 0}">
                        <c:if test="${privacidade.privacidadeDescricao == 1 || privacidade.id == 0}">
                            <div class="card">
                                <div class="card-header text-white">Descrição do Usuário:</div>
                                <div class="card-body">${perfil.descricao}</div>
                            </div>
                        </c:if>
                        <c:if test="${privacidade.privacidadeDescricao == 2}">
                            <c:if test="${amizade == 3}">
                                <div class="card">
                                    <div class="card-header text-white">Descrição do Usuário:</div>
                                    <div class="card-body">${perfil.descricao}</div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${privacidade.privacidadeInteresses != 3 || privacidade.id == 0}">
                        <c:if test="${privacidade.privacidadeInteresses == 1 || privacidade.id == 0}">
                            <div class="card">
                                <div class="card-header text-white">Interesses:</div>
                                <div class="card-body">${perfil.interesses}</div>
                            </div>
                        </c:if>
                        <c:if test="${privacidade.privacidadeInteresses == 2}">
                            <c:if test="${amizade == 3}">
                                <div class="card">
                                    <div class="card-header text-white">Interesses:</div>
                                    <div class="card-body">${perfil.interesses}</div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${perfil.logradouro != null}">
                        <c:if test="${privacidade.privacidadeTelefone != 3 || privacidade.id == 0}">
                            <c:if test="${privacidade.privacidadeTelefone == 1 || privacidade.id == 0}">
                                <br><div class="card">
                                    <div class="card-header text-white">Telefone Fixo:</div>
                                    <div class="card-body">${perfil.tel}</div>
                                </div>
                                <div class="card">
                                    <div class="card-header text-white">Celular:</div>
                                    <div class="card-body">${perfil.cel}</div>
                                </div>
                            </c:if>
                            <c:if test="${privacidade.privacidadeTelefone == 2}">
                                <c:if test="${amizade == 3}">
                                    <br><div class="card">
                                        <div class="card-header text-white">Telefone Fixo:</div>
                                        <div class="card-body">${perfil.tel}</div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header text-white">Celular:</div>
                                        <div class="card-body">${perfil.cel}</div>
                                    </div>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${privacidade.privacidadeEndereco != 3 || privacidade.id == 0}">
                            <c:if test="${privacidade.privacidadeEndereco == 1 || privacidade.id == 0}">
                                <br><h4>Endereço:</h4><br>
                                <div class="card">
                                    <div class="card-header text-white">Logradouro:</div>
                                    <div class="card-body">${perfil.logradouro}</div>
                                    <div class="card-header text-white">Número:</div>
                                    <div class="card-body">${perfil.numero}</div>
                                    <div class="card-header text-white">Complemento:</div>
                                    <div class="card-body">${perfil.complemento}</div>
                                    <div class="card-header text-white">CEP:</div>
                                    <div class="card-body">${perfil.CEP}</div>
                                    <div class="card-header text-white">Cidade:</div>
                                    <div class="card-body">${perfil.cidade}</div>
                                    <div class="card-header text-white">Estado:</div>
                                    <div class="card-body">${perfil.estado}</div>
                                </c:if>
                                <c:if test="${privacidade.privacidadeEndereco == 2}">
                                    <c:if test="${amizade == 3}">
                                        <br><h4>Endereço:</h4><br>
                                        <div class="card">
                                            <div class="card-header text-white">Logradouro:</div>
                                            <div class="card-body">${perfil.logradouro}</div>
                                            <div class="card-header text-white">Número:</div>
                                            <div class="card-body">${perfil.numero}</div>
                                            <div class="card-header text-white">Complemento:</div>
                                            <div class="card-body">${perfil.complemento}</div>
                                            <div class="card-header text-white">CEP:</div>
                                            <div class="card-body">${perfil.CEP}</div>
                                            <div class="card-header text-white">Cidade:</div>
                                            <div class="card-body">${perfil.cidade}</div>
                                            <div class="card-header text-white">Estado:</div>
                                            <div class="card-body">${perfil.estado}</div>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </c:if>

                        </c:if>
                        <input type="text" value="${perfil.id}" id="idJs" name="idJs" hidden="">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ./row -->
</div> <!-- ./container -->
</body>
</html>


