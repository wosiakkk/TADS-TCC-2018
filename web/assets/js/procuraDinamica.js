$(function () {
    $("#search").autocomplete({
        appendTo: "#anchor",
        autoFocus: true,
        source: function (request, response) {
            $.ajax({
                url: "SearchController",
                dataType: 'json',
                data: request,
                success: function (data) {
                    if (typeof Array.prototype.forEach != 'function') {
                        Array.prototype.forEach = function (callback) {
                            for (var i = 0; i < this.length; i++) {
                                callback.apply(this, [this[i], i, this]);
                            }
                        };
                    }

                    var values = data;
                    var newArray = new Array(values.length);
                    var i = 0;
                    values.forEach(function (entry) {
                        var newObject = {
                            label: entry.nome
                        };
                        newArray[i] = newObject;
                        i++;
                    });
                    if(values.length > 0) {
                        response(newArray);
                    }
                    else {
                        response(new Array("Nenhum usuário encontrado."));
                    }
                        
                }
            });
        },
        minLength: 1
    });
});