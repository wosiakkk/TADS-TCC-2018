/* 
 * Created at: 07/10/2018
 * By: Marcos Silva
 */

$(document).ready(function(){
    $('#timeline_container').hide();
    
    $.ajax({
        url: "TimelineServlet?action=TIMELINE",
        method: "POST",
        dataType: "HTML",
        success: function (resp) {
            $('#loaderTimeline').hide();
            $('#timeline_container').append(resp);
            $('#timeline_container').show();
        },
        error: function (resp) {
            $('#loaderTimeline').hide();
            $('#timeline_container').html(resp);
            $('#timeline_container').show();
        }
    });
});