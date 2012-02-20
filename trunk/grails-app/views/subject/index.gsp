<%--
  author: evgeniy
  Date: 27.06.11
  Time: 23:44
--%>

<%@ page import="decanat.grails.User" contentType="text/html;charset=UTF-8" %>
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
                    { sWidth:"30%" },
                    { sWidth:"30%" },
                    { sWidth:"25%" },
                    { sWidth:"15%", bSortable:false }
                ]
            });
        }


        $(document).ready(function () {
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide();
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/subject/delete/'}' + iid);
            $("#dialog").dialog();
        }

    </script>

</head>

<body>

<div>
    <div align="left">
        <tooltip:tip code="tooltip.add.subject">
            <a href="<g:createLink action="add" controller="subject"/>">
                <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>"/>
            </a>
        </tooltip:tip>
    </div>

    <g:render template="/template/subject/subjectList" model="${[res: res]}"/>

    <content tag="search">
        <g:render template="/template/subject/searchTemplate" model="['controller': 'subject']"/>
    </content>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation"
                  model="['askMessage': 'Вы точно хотите удалить этот предмет?']"/>
    </content>
</div>
</body>
</html>


