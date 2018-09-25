<%-- 
    Document   : vendas
    Created on : 25/08/2018, 15:56:33
    Author     : Marcos
--%>

<!-- Cabeçalho -->
<%@include file="head.jsp" %>
<c:if test="${(empty(user))}">
    <c:redirect url="index.jsp">
        <c:param name="msg" value="Faça login para acessar esta página!"></c:param>
    </c:redirect>
</c:if>
<link href="assets/css/feed-style.css" rel="stylesheet">
<div class="col-lg-12">
    <h1>&Aacute;rea de Vendas</h1>
    <a href="home.jsp"><i class="fa fa-arrow-circle-left"></i> Voltar</a>
    <hr>
</div>

<!-- Página com foto e as opções do perfil -->
<%@include file="opcoes.jsp" %>

<div class="col-lg-7 col-md-7 col-xs-12">
    <div id="pageContent" class="row">

    </div>
</div>

<div class="col-lg-2 col-md-2 col-xs-12 menu">
    <form id="formFiltro" role="form">
        <div class="form-group">
            <label for="ordem">Ordenar por:</label>
            <select id="ordem" name="ordem" class="form-control" disabled>
                <option></option>
                <option value="1">Maior valor</option>
                <option value="2">Menor valor</option>
                <option value="3">T&iacutetulo do an&uacutencio</option>
            </select>
        </div>
        <div class="form-group">
            <label for="min">De: </label>
            <input id="min" name="min" class="form-control" type="number" placeholder="0.00" disabled>
            <label for="max">At&eacute: </label>
            <input id="max" name="max" class="form-control" type="number" placeholder="0.00" disabled>
        </div>
        <div class="checkbox">
            <label>
                <input id="movel" name="movel" type="checkbox" value="0"/> M&oacuteveis
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input id="imovel" name="imovel" type="checkbox" value="0"/> Im&oacuteveis
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input id="material" name="material" type="checkbox" value="0"/> Materiais
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input id="pet" name="pet" type="checkbox" disabled/> Pets?
            </label>
        </div> 
        <button type="submit" class="btn btn-primary">
            Filtrar
        </button>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        buscarAnunciosAprovados("#pageContent");
    });
    
    $('input[type=checkbox]').change(function(){
       if(this.checked) {
           $(this).val('1');
       } 
       else {
           $(this).val('0');
       }
    });
    
    $("#formFiltro").submit(function(event){
        event.preventDefault();
        
        var dados = {
            imovel: $('input[name=imovel]').val(),
            movel: $('input[name=movel]').val(),
            material: $('input[name=material]').val(),
            action: 'FILTROANUNCIO'
        };
        
        $.ajax({
            url: 'AnuncioServlet',
            method: 'POST',
            data: dados,
            dataType: 'HTML',
            success: function(resp) {
                $("#pageContent").html(resp);
            },
            error: function(resp) {
                console.log(resp);
                alert("Ocorreu um erro ao filtrar os anúcnios. Por favor, tente novamente mais tarde.");
            }
        });
    });
</script>
