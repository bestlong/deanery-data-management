<%--
  author: evgeniy
  Date: 27.06.11
  Time: 23:44
--%>

<%@ page import="decanat.grails.domain.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Предметы</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">

        function initTable() {
            var currId = '';
            $('#subjects').dataTable({
                "bJQueryUI":true,
                "bFilter":false,
                sPaginationType:"full_numbers",
                iDisplayLength:25,
                "oLanguage":{
                    "sInfo":"Всего: _TOTAL_. Показано с _START_ по _END_",
                    "sInfoEmpty":"Нет данных для отображения",
                    "sSearch":"Поиск",
                    "sLengthMenu":"Отображать по _MENU_",
                    "sInfoFiltered":"(найдено из _MAX_)",
                    "sZeroRecords":"По Вашему запросу ничего не найдено.",
                    "oPaginate":{
                        "sFirst":"К началу",
                        "sPrevious":"Назад",
                        "sLast":"В конец",
                        "sNext":"Далее"
                    }
                },
                bProcessing:true,
                bServerSide:true,
                sAjaxSource:"${request.contextPath}/subject/table",
                bAutoWidth:false,
                aLengthMenu:[
                    [10, 25, 50],
                    [10, 25, 50]
                ],
                aoColumns:[
                    { bSortable:false, sWidth:"5%",
                        "fnRender":function (o, val) {
                            currId = o.aData[0];
                            var res = '';
                            res = res + '<input type="hidden" name="id" value=' + o.aData[0] + '>' +
                                    '<input type="hidden" name="referenceCount" value=' + o.aData[5] + '>';
                            if (o.aData[5] == 0) {
                                res = res + '<input type="checkbox" name ="multipleDelete' + o.aData[0] + '" id="multipleDelete' + o.aData[0] + '" onclick="changeBackground(' + o.aData[0] + ')">';
                            }
                            return res;
                        }},
                    {},
                    {},
                    {},
                    {},
                    { bSortable:false, sWidth:"5%",
                        "fnRender":function (o, val) {
                            var res = '';
                            res = res + '<table>' +
                                    '<tr>' +
                                    '<td align="left" style="margin-left: 5px; margin-right: 5px">' +
                                    '<span onmouseover="tooltip.show(\'Редактировать выбранный предмет\');" onmouseout="tooltip.hide();">' +
                                    '<a href="/plan/subject/edit/' + currId + '" class="editBtn">' +
                                    '<img src="/plan/images/ctrl/edit.jpg">' +
                                    '</a>' +
                                    '</span>' +
                                    '</td>';
                            if (o.aData[5] == 0) {
                                res = res +
                                        '<td align="right" style="margin-left: 5px; margin-right: 5px">' +
                                        '<span onmouseover="tooltip.show(\'Удалить выбранный предмет\');" onmouseout="tooltip.hide();">' +
                                        '<a class="delBtn" onclick="deleteDialog(' + currId + ')">' +
                                        '<img alt="delete" src="/plan/images/ctrl/del.jpg">' +
                                        '</a>' +
                                        '</span>' +
                                        '</td>';
                            } else {
                                res = res +
                                        '<td align="right" style="margin-left: 5px; margin-right: 5px">' +
                                        '<span onmouseover="tooltip.show(\'Удалить выбранный предмет\');" onmouseout="tooltip.hide();">' +
                                        '<img alt="delete_disabled" src="/plan/images/ctrl/delete_disabled.gif">' +
                                        '</span>' +
                                        '</td>';
                            }
                            res = res + '</tr>'+
                                    '</table>';
                            return res;
                        }}
                ],
                fnRowCallback:function (nRow, aData, iDisplayIndex) {
                    $(nRow).attr("id", 'tr' + $(nRow).find("input:hidden[name=id]").val());
                    if ($(nRow).find("input:hidden[name=referenceCount]").val() == 0) {
                        $(nRow).attr("name", 'itemTr');
                    }
                    return nRow;
                }
            });
        }

        $(document).ready(function () {
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide();
            hideDeleteMultiple();
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/subject/delete/'}' + iid);
            $("#dialog").dialog();
        }

        function multipleDeleteDialog() {
            $("#dialogMultipleDelete").dialog();
            $('#recordsCount').html($("input[type=checkbox]:checked").length)
        }
    </script>
</head>

<body>
<div>
    <g:form action="multipleDelete" controller="subject">
        <div align="left" class="action">
            <g:link controller="subject" action="add">
                Новый
            </g:link>
            <a id="multipleDelete" onclick="multipleDeleteDialog()">Удалить выделенные</a>
            <g:submitButton name="multipleDeleteSubmit" value="" style="display: none"/>
        </div>
        <g:render template="/template/subject/subjectList" model="${[res: res]}"/>

        <content tag="search">
            <g:render template="/template/subject/searchTemplate" model="['controller': 'subject']"/>
        </content>

        <content tag="deleteConfirmation">
            <g:render template="/template/deleteConfirmation"
                      model="['askMessage': 'Вы точно хотите удалить этот предмет?']"/>
        </content>
    </g:form>
</div>
</body>
</html>


