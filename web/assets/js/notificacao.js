$(document).ready(function () {
    //requisicao para contagem de novas notificacoes sempre que o head é carregado
    $.ajax({
        method: 'post',
        url: 'NotificacaoServlet?action=CONTAGEM',
        data: {
            idAjax: $('input[name=idOculta]').val()
        },
        success: function (data) {
            $("#notification_li").append(data);
        }
    });

    $("#notificationLink").click(function () {
        //exibição da caixa de notificação e ocultação do contador
        $("#notificationContainer").fadeToggle(300);
        $("#notification_count").fadeOut("slow");

        //requisição para definir como lida as novas notificações ao clicar para visualiza-las
        $.ajax({
            method: 'post',
            url: 'NotificacaoServlet?action=LER',
            data: {
                idAjax: $('input[name=idOculta]').val()
            },
            success: function () {
                console.log("Atualizados");
            },
            error: function () {
                console.log("Não atualizou notificações");
            },
            //apos atulizar os tatus exibir todas na caixa de notificações com uma nova requisição
            complete: function () {
                $.ajax({
                    method : 'post',
                    url : 'NotificacaoServlet?action=LISTAR',
                    data : {
                         idAjax : $('input[name=idOculta]').val()
                    },
                    success : function(dat){
                        console.log("requisicao listar com sucesso");
                        $("#notificationsBody").append(dat);
                    },
                     error: function () {
                        console.log("Erro ao listar as notificações");
                     }
                });     
            }
        });
        return false;
    });

//Função executada ao clicar fora do popup 
    $(document).click(function () {
        $("#notificationContainer").hide();
        //limpar o conteudo apra nao duplicar na div de exibição
        $("#notificationsBody").empty();
    });

//requisição ao clicar em ''ver todas''
   $("#linkPagNoti").click(function (){
       window.location.href = "notificacoes.jsp";
    });
    

//Funcao de clicar no popup
   /* $("#notificationContainer").click(function () {
        return false;
    });*/
});