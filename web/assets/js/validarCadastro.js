$(document).ready(function(){
  $('#formulario').validate({
    rules: {
      nome: {
        required: true,
        minlength: 3
      },
      email: {
        required: true,
        email: true
      },
      senha: {
        required: true
      },
      confsenha: {
        required: true,
        equalTo: "#senha"
      },         
    },    
    highlight: function(element) {
        $(element).css('background', '#ffdddd');
    },    
    unhighlight: function(element) {
        $(element).css('background', '#ffffff');
    },    
    messages: {
      nome: {
        required: "O campo nome é obrigatório.",
        minlength: "O campo nome deve conter no mínimo 3 caracteres."
      },
      email: {
        required: "O campo email é obrigatório.",
        email: "O campo email deve conter um email válido."
      },
      senha: {
        required: "O campo senha é obrigatório."
      },
      confsenha: {
        required: "O campo confirmação de senha é obrigatório.",
        equalTo: "O campo confirmação de senha deve ser identico ao campo senha."
      },     
    }
  });
});