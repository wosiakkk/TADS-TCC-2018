/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    //Carrega as mensagens salvas
    listarMensagens();
    
    $('#mensagemAjax').submit(function (event) {
        //Cacela comportamento padrão do formulário de recarregar a página
        event.preventDefault();

        //Pega os dados do formulário
        var dados = $(this).serialize();

        //Configura, faz e trata o retorno da requisão
        $.ajax({
            url: 'MensagemServlet',
            data: dados,
            method: 'POST',
            dataType: 'html',
            success: function (resp) {
                listarMensagens();
            },
            error: function (resp) {
                console.log(resp);
                $('#respostaAjax').html(resp);
            }
        });
        //Limpa os campos do formulário
        this.reset();
    });

});

function listarMensagens() {

    var dados = {
        action: "LIST_MENSAGEM",
        ID_ORIGEM: $('input[name=ID_ORIGEM]').val(),
        ID_DESTINO: $('input[name=ID_DESTINO').val()
    };
    $.ajax({
        url: 'MensagemServlet',
        data: dados,
        method: 'POST',
        dataType: 'html',
        success: function (resp) {
            $('#respostaAjax').html(resp);
        },
        error: function (resp) {
            console.log(resp);
            $('#respostaAjax').html(resp);
        }
    });
}