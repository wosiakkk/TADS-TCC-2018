$(document).ready(function() { 
    //checar se o usuário já é seguidor para alterar o botão
    $.ajax({
        method: 'post',
        url: 'AnuncioServlet?action=VRFSEGUIDOR',
        data: {
            idAnuncioAjax: $('input[name=idAnuncioOculta]').val(),
            idSeguidorAjax: $('input[name=idUserOculta]').val()
        },
        success: function (data) {
            if(data == 'true'){
                $('#seguirImovel').val('Você está seguindo este anúncio');
            }else{
                 $('#seguirImovel').val('Seguir anúncio');
            }
        }
     });
     
   $('#seguirImovel').click(function (){
        if($('#seguirImovel').val() == "Seguir anúncio"){
            $.ajax({
                 method: 'post',
                 url: 'AnuncioServlet?action=ADDSEGUIDOR',
                 data: {
                     idAnuncioAjax: $('input[name=idAnuncioOculta]').val(),
                     idSeguidorAjax: $('input[name=idUserOculta]').val()
                 },
                 success: function (data) {
                    $('#seguirImovel').val('Você está seguindo este anúncio');
                 }
             });
        }else{
            $.ajax({
                 method: 'post',
                 url: 'AnuncioServlet?action=RMVSEGUIDOR',
                 data: {
                     idAnuncioAjax: $('input[name=idAnuncioOculta]').val(),
                     idSeguidorAjax: $('input[name=idUserOculta]').val()
                 },
                 success: function (data) {
                    $('#seguirImovel').val('Seguir anúncio');
                 }
             });
        }
    });
    
});