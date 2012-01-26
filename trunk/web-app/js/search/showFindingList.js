/**
 * Created by IntelliJ IDEA.
 * User: corwin
 * Date: 07.07.11
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
//function showFindingList(rootJSON) {
//    var res = "<thead>" +
//            "<tr>" +
//            "<th>Логин</th>" +
//            "<th>Роль</th>" +
//            "<th>ФИО</th>" +
//            "<th>e-mail</th>" +
//            "<th>Редактирование</th>" +
//            "</tr>" +
//            "</thead>" +
//            "<tbody id=\"forPages\">";
//
//    for (i = 0; i < rootJSON.length; i++) {
//        res += "<tr>" +
//                "<td>" +
//                rootJSON[i].login +
//                "</td>" +
//                "<td>" +
//                rootJSON[i].role +
//                "</td>" +
//                "<td>" +
//                rootJSON[i].realName +
//                "</td>" +
//                "<td>" +
//                rootJSON[i].email;
//        res += "</td><td><a href=\"usersProfile.html\">" +
//                "<input type=\"image\" src=\"/decanat-grails/images/ctrl/edit.jpg\"//>" +
//                "</a>" +
//                "<input type=\"image\" src=\"/decanat-grails/images/ctrl/del.jpg\" onclick=\"if (confirm('точно удалить?')) $('#submitButton').click();\"//>" +
//                "</td>" +
//                "</tr>";
//    }
//    res += '</tbody>';
//    return res;
//}

function successHandler(data) {
//    var obj = jQuery.parseJSON(data);
//    jQuery('#contentTable').html(showFindingList(obj.result));
    $("#contentTable")
            .tablesorter({widthFixed: true, widgets: ['zebra']})
            .tablesorterPager({container: $("#pager")});
}
