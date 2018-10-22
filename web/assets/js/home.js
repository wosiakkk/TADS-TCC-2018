/* 
 * Created at: 07/10/2018
 * By: Marcos Silva
 */

$(document).ready(function(){
    $('.timeline').hide();
    
    $.ajax({
        url: "TimelineServlet?action=TIMELINE",
        method: "POST",
        dataType: "HTML",
        success: function (resp) {
            $('#loaderTimeline').hide();
            $('.timeline').append(resp);
            $('.timeline').show();
        },
        error: function (resp) {
            $('#loaderTimeline').hide();
            $('.timeline').html(resp);
            $('.timeline').show();
        }
    });
});