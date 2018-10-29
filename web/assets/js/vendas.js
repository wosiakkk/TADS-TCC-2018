/* 
 * Created: 03/10/2018
 * Author: Marcos Silva
 */

$(document).ready(function () {
    $('input[type=checkbox]').val('0');
    buscarAnunciosAprovados("#pageContent");
    $('#petsField').hide();
});

$("#formFiltro").validate({
    rules: {
        maxValor: {
            number: true
        },
        minValor: {
            number: true
        }
    },
    highlight: function(element) {
        $(element).css('background', '#ffdddd');
    },    
    unhighlight: function(element) {
        $(element).css('background', '#ffffff');
    },
    messages: {
        maxValor: {
            number: 'Somente valor numerico.'
        },
        minValor: {
            number: 'Somente valor numerico.'
        }
    }
});

$('input#imovel').change(function () {
    if(this.checked) {
        $('#petsField').show();
    } else {
        $('#petsField').hide();
    }
});

$('input[type=checkbox]').change(function () {
    if (this.checked) {
        $(this).val('1');
    } else {
        $(this).val('0');
    }
});

$("#formFiltro").submit(function (event) {
    event.preventDefault();

    $.ajax({
        url: 'AnuncioServlet?action=FILTROANUNCIO',
        method: 'POST',
        data: $(this).serialize(),
        dataType: 'HTML',
        success: function (resp) {
            $("#pageContent").html(resp);
        },
        error: function (resp) {
            console.log(resp);
            alert("Ocorreu um erro ao filtrar os anúcnios. Por favor, tente novamente mais tarde.");
        }
    });
});

$('#limparFiltros').click(function() {
    $("#formFiltro").trigger('reset');
    $('#petsField').hide();
    $("#formFiltro").submit();
});
