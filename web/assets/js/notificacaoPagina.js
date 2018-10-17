/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //requisicao para contagem de novas notificacoes sempre que o head é carregado
    $.ajax({
        method: 'post',
        url: 'NotificacaoServlet?action=LISTARPAGINA',
        data: {
            idAjax: $('input[name=idOculta]').val()
        },
        success: function (data) {
            $("#notibody").append(data);
        }
    });
 });

