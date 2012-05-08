<%--
  author: evgeniy
  Date: 27.06.11
  Time: 23:44
--%>

<%@ page import="decanat.grails.domain.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Специальности</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">

        function initTable() {
            var currId = '';

        <sec:ifAnyGranted roles="ROLE_PROREKTOR">
            var refColumn = 6;
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_DEAN">
            var refColumn = 5;
        </sec:ifAnyGranted>

            $('#specialityList').dataTable({
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
                sAjaxSource:"${request.contextPath}/speciality/table",
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
                                    '<input type="hidden" name="referenceCount" value=' + o.aData[refColumn] + '>';
                            if (o.aData[refColumn] == 0) {
                                res = res + '<input type="checkbox" name ="multipleDelete' + o.aData[0] + '" id="multipleDelete' + o.aData[0] + '" onclick="changeBackground(' + o.aData[0] + ')">';
                            }
                            return res;
                        }},
                    {},
                    {},
                    {},
                    {},
                    <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                    {},
                    </sec:ifAnyGranted>
                    { bSortable:false, sWidth:"5%",
                        "fnRender":function (o, val) {
                            var res = '';
                            res = res + '<table>' +
                                    '<tr>' +
                                    '<td align="left" style="margin-left: 5px; margin-right: 5px">' +
                                    '<span onmouseover="tooltip.show(\'Редактировать выбранный предмет\');" onmouseout="tooltip.hide();">' +
                                    '<a href="/plan/speciality/edit/' + currId + '" class="editBtn">' +
                                    '<img src="/plan/images/ctrl/edit.jpg">' +
                                    '</a>' +
                                    '</span>' +
                                    '</td>';
                            if (o.aData[refColumn] == 0) {
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
                                        '<span onmouseover="tooltip.show(\'Удалить выбраную специальность\');" onmouseout="tooltip.hide();">' +
                                        '<img alt="delete_disabled" src="/plan/images/ctrl/delete_disabled.gif">' +
                                        '</span>' +
                                        '</td>';
                            }
                            res = res + '</tr>' +
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
            $("#delBtn").attr("href", '${request.contextPath + '/speciality/delete/'}' + iid);
            $("#dialog").dialog();
        }

        function multipleDeleteDialog() {
            $("#dialogMultipleDelete").dialog();
            $('#recordsCount').html($("input[type=checkbox]:checked").length)
        }
    </script>
</head>

<body>
<sec:ifAnyGranted roles="ROLE_PROREKTOR">
    <g:if test="${faculty?.id!=0}">
        <div  align=center>
            <h3>
                Работает
                <a href="/plan/faculty/list" style="color: #2582A4">
                    фильтр</a> по факультету : ${faculty?.name}
            </h3>
        </div>
    </g:if>
</sec:ifAnyGranted>
<div>
    <g:form action="multipleDelete" controller="speciality">
        <div align="left" class="action">
            <sec:ifAnyGranted roles="ROLE_DEAN">
                <g:link controller="speciality" action="add">
                    Новая
                </g:link>
            </sec:ifAnyGranted>
            <a id="multipleDelete" onclick="multipleDeleteDialog()">Удалить выделенные</a>
            <g:submitButton name="multipleDeleteSubmit" value="" style="display: none"/>
        </div>
        <g:render template="/template/speciality/specialityList" model="${[res: res]}"/>

        <content tag="search">
            <g:render template="/template/speciality/searchTemplate" model="['controller': 'speciality']"/>
        </content>

        <content tag="deleteConfirmation">
            <g:render template="/template/deleteConfirmation"
                      model="['askMessage': 'Вы точно хотите удалить этот предмет?']"/>
        </content>
    </g:form>
</div>
</body>
</html>


