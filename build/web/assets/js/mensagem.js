/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
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
                console.log(resp);
                $('#respostaAjax ul li').append(resp);
            },
            error: function (resp) {
                console.log(resp);
                $('#respostaAjax').html(resp);
            }
        });
        //Limpa os campos do formulário
        this.reset();
    });

    $('#BTN_LISTAR_MENSAGEM').click(function (e) {
        var dados = {
            action: "LIST_MENSAGEM",
            ID_ORIGEM: 3,
            ID_DESTINO: 9
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
    });
});