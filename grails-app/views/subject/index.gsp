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
            $('#subjects').dataTable({
                        "bJQueryUI":true,
                        "sPaginationType":"full_numbers",
                        "iDisplayLength":20,
                        "bLengthChange":false,
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
                        bAutoWidth:false,
                        aoColumns:[
                            { bSortable:false, sWidth: "5%"},
                            {},
                            {},
                            {},
                            {},
                            { bSortable:false, sWidth: "5%"}
                        ]
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


