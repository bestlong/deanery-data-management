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
                        "bJQueryUI": true,
                        "sPaginationType": "full_numbers",
                        "iDisplayLength": 15,
                        "bLengthChange": false,
                        "bProcessing": true,
                        "oLanguage": {
                            "sInfo": "Всего: _TOTAL_. Показано с _START_ по _END_",
                            "sInfoEmpty": "Нет данных для отображения",
                            "sSearch": "Поиск",
                            "sLengthMenu":"Отображать по _MENU_",
                            "sInfoFiltered": "(найдено из _MAX_)",
                            "sZeroRecords": "По Вашему запросу ничего не найдено.",
                            "oPaginate":
                            {
                                "sFirst": "К началу",
                                "sPrevious": "Назад",
                                "sLast": "В конец",
                                "sNext": "Далее"
                            }
                        },
                        bAutoWidth: false,
                        aoColumns   : [
                            { sWidth: "25%" },
                            { sWidth: "20%" },
                            { sWidth: "20%" },
                            { sWidth: "10%" },
                            { sWidth: "10%" },
                            { sWidth: "15%", bSortable: false }
                        ]
                    });
        }

        $(function() {
            $("#dialog").hide();
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/chair/remove/'}' + iid);
            $("#dialog").dialog();
        }

        $(function() {
            $("input:submit, a, button", ".action").button();
            $("a", ".demo").click(function() {
                return false;
            });
        });

    </script>

    <style type="text/css">
    .edBtn, .delBtn {
        width: 18px;
        height: 18px;
    }
    </style>
</head>

<body>

<div align="left">
    <tooltip:tip code="tooltip.chair.add">
        <a href="<g:createLink action="create" controller="chair"/>">
            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
        </a>
    </tooltip:tip>
</div>

<div id="container" align="center">
    <g:render template="/template/chair/chairList" model="${[chairCollection: chairList, searchConfig: searchConfig]}"/>
</div>

<content tag="search">
    <g:render template="/template/chair/searchTemplate"/>
</content>

<content tag="deleteConfirmation">
    <g:render template="/template/deleteConfirmation" model="['askMessage':'Вы точно хотите удалить эту кафедру?']"/>
</content>

</body>
</html>
