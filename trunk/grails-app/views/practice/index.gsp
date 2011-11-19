<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление практик</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">

        function initTable() {
            $("#practices").dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "iDisplayLength": 15,
                "bLengthChange": false,
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
                    { sWidth: "25%"},
                    { sWidth: "25%"},
                    { setWidth: "25%", bSortable: false}

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

        function deleteDialog(iid, planId) {
            $("#delBtn").attr("href", '${request.contextPath + '/practice/delete/'}' + iid + '?planId=' + planId);
            $("#dialog").dialog();
        }

    </script>
</head>

<body>
<h4 class="subtitle">Добавление практик:</h4>
<content tag="search">
    <g:render template="/template/plan/menu" model="[ 'active' : 3, 'plan': plan ]"/>
</content>

<div align="left">
    <tooltip:tip code="tooltip.practice.add">
        <a href="<g:createLink action="add" controller="practice" id="${plan.id}"/>">
            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
        </a>
    </tooltip:tip>
</div>

<g:render template="/template/practice/practiceList" model="${[res: res]}"/>

<div align="center" class="action">
    <g:link controller="addSubjects" action="index" id="${plan.id}">Отмена</g:link>

    <g:link controller="stateExam" action="index" id="${plan.id}">Следующий шаг</g:link>

</div>
<content tag="deleteConfirmation">
    <g:render template="/template/deleteConfirmation"
            model="['askMessage':'Вы точно хотите удалить эту практику?']"/>
</content>

</body>
</html>


