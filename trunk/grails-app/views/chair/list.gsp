<%@ page import="decanat.grails.Chair" %>
<html>
<head>
    <title>Кафедры</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">

        $(document).ready(function () {
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
        });

        function initTable() {
            $("#tableCont").dataTable({
                "bJQueryUI":true,
                "sPaginationType":"full_numbers",
                "iDisplayLength":15,
                "bLengthChange":false,
                "bProcessing":true,
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
                    {bSortable:false },
                    {},
                    {},
                    {},
                    {},
                    {bSortable:false},
                    {bSortable:false},
                    {bSortable:false}
                ]
            });
        }

        $(function () {
            $("#dialog").hide();
            $("input:submit, a, button", ".action").button();
            hideDeleteMultiple();
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/chair/remove/'}' + iid);
            $("#dialog").dialog();
        }

        function multipleDeleteDialog() {
            $("#dialogMultipleDelete").dialog();
            $('#recordsCount').html($("input[type=checkbox]:checked").length)
        }
    </script>

    <style type="text/css">
    .edBtn, .delBtn {
        width: 18px;
        height: 18px;
    }
    </style>
</head>

<body>
<g:form action="multipleDelete" controller="chair">

    <div align="left">
        <div align="left" class="action">
            <g:link controller="chair" action="create">
                Новый
            </g:link>
            <a id="multipleDelete" onclick="multipleDeleteDialog()">Удалить выделенные</a>
            <g:submitButton name="multipleDeleteSubmit" value="" style="display: none"/>
        </div>
    </div>

    <div id="container" align="center">
        <g:render template="/template/chair/chairList"
                  model="${[chairCollection: chairList, searchConfig: searchConfig]}"/>
    </div>

    <content tag="search">
        <g:render template="/template/chair/searchTemplate"/>
    </content>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation"
                  model="['askMessage': 'Вы точно хотите удалить эту кафедру?']"/>
    </content>

</g:form>
</body>
</html>
