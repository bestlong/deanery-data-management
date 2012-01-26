/**
 * Created by IntelliJ IDEA.
 * User: drStout
 * Date: 06.07.11
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */

function hideIndicator() {
    //$('ajax_spinner').hide();
    $('#ajax_spinner').hide();
}

var focus;
$('input[type=text]').live('focus', function () {
    focus = $(this);
});

function showIndicator() {
    var pos = $("#page").offset();
    var width = $("#page").width();
    var height = $("#page").height();
    $("#ajax_spinner").css({ "left": (pos.left + width / 2) + "px", "top":pos.top + height / 2 + "px" });
    $('#ajax_spinner').show();
}


$(document).ready(function() {
//    showIndicator();
    $(document).ajaxStart(function() {

        if (focus && focus != null) {
            var pos = focus.offset();
//            var width = focus.width();
            $("#ajax_spinner").css({ "left": (pos.left - 10) + "px", "top":pos.top - 5 + "px" });
        } else {
            var pos = $("#page").offset();
            var width = $("#page").width();
            var height = $("#page").height();
            $("#ajax_spinner").css({ "left": (pos.left + width / 2) + "px", "top":pos.top - 5 + "px" });
        }
        $('#ajax_spinner').show();
    });

    $(document).ajaxStop(function() {
        $('#ajax_spinner').fadeOut(500);
    });
});
