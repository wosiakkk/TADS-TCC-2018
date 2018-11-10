
<div class="col-3">
    <div class="menu-fixed-left">
        <div class="center">
            <img class="d-block img-fluid rounded-circle" style="width: 100px; height: 100px" src="${user.getFoto()}">
            <h4><strong style="color: gray"><c:out value="${user.getNome()}"/></strong></h4>
        </div>
        <div class="list-group">
            <c:if test="${user.getTipoUsuario()== 1}">                  
                <a id="opAprovMod" class="list-group-item" href="escolhaPendente.jsp">Aprovar Anúncios</a>
                <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>
            </c:if>
            <c:if test="${user.getTipoUsuario()== 2}">
                <a id="opPerfil" class="list-group-item " href="UserServlet?action=PERFIL&idUser=${user.getId()}">Perfil</a>
                <a id="opPref" class="list-group-item" href="escolhaPreferencias.jsp">Preferências</a> 
                <a id="opAmigo" class="list-group-item" href="escolhaAmigo.jsp">Meus Amigos</a>
                <a id="opMsg" class="list-group-item" href="mensagem.jsp">Mensagens</a>
                <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>                      
                <a id="opAnuncio" class="list-group-item" href="escolhaMeusAnuncios.jsp">Meus Anúncios</a>
                <a id="opRanuncio" class="list-group-item" href="escolhaAnuncio.jsp">Realizar um Anúncio</a>
                <a id="opEst" class="list-group-item" href="gerarRelatorio.jsp">Estatísticas</a>
            </c:if>
            <c:if test="${user.getTipoUsuario()== 3}">
                <a id="opCadAdm" class="list-group-item" href="cadastrarAdm.jsp">Cadastrar um Administrador</a>
                <a id="opCadMod" class="list-group-item" href="cadastrarModerador.jsp">Cadastrar um Moderador</a>
                <a id="opAprovAdm" class="list-group-item" href="escolhaPendente.jsp">Aprovar Anúncios</a> 
                <a id="opVendas" class="list-group-item" href="vendas.jsp">Área de Vendas</a>
                <a id="opEstAdm" class="list-group-item" href="gerarRelatorioAdm.jsp">Estatísticas</a>
            </c:if>
        </div>
    </div>
</div>