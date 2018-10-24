/* 
 * Created at: 07/10/2018
 * By: Marcos Silva
 */

$(document).ready(function(){
    iniciar();
    
    $('.produto').change(function(){
        if($(this.checked)) {
            ordemExibir('movel');
        }
        else if($(this.checked)) {
            ordemExibir('imovel');
        }
        else if($(this.checked)) {
            ordemExibir('material');
        }
    });
});

function iniciar() {
    $('#opcoesMovel').hide();
    $('#opcoesImovel').hide();
    $('#opcoesMaterial').hide();
}

function ordemExibir(key) {
    console.log(key);
    if(key === 'movel') {
        console.log(key);
        $('opcoesMovel').show();
        $('opcoesImovel').hide();
        $('opcoesMaterial').hide();
    }
    else if(key === 'imovel') {
        console.log(key);
        $('opcoesImovel').show();
        $('opcoesMovel').hide();
        $('opcoesMaterial').hide();
    }
    else if(key === 'material') {
        console.log(key);
        $('opcoesMaterial').show();
        $('opcoesMovel').hide();
        $('opcoesImovel').hide();
    }
}