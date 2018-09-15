$(document).ready(function () {
    $('#formulario').validate({
        rules: {
            senhaAtual: {
                required: true,
                minlength: 3
            },
            novaSenha: {
                required: true,
                minlength: 3
            },
            confsenha: {
                required: true,
                equalTo: "#novaSenha"
            },
        },
        highlight: function (element) {
            $(element).css('background', '#ffdddd');
        },
        unhighlight: function (element) {
            $(element).css('background', '#ffffff');
        },
        messages: {
            senhaAtual: {
                required: "O campo senha atual é obrigatório.",
                minlength: "O senha atual deve conter no mínimo 3 caracteres."
            },
            novaSenha: {
                required: "O campo nova senha é obrigatório.",
                minlength: "O campo nova senha deve conter no mínimo 3 caracteres."
            },
            confsenha: {
                required: "O campo confirmação de senha é obrigatório.",
                equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
            },
        }
    });
});