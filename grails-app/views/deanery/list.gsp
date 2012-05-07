<%@ page import="decanat.grails.Deanery" %>
<!doctype html>
<head>
    <title>Деканаты</title>
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
                    {},
                    {},
                    {},
                    {},
                    {bSortable:false },
                    {bSortable:false }
                ]
            });
            $(function () {
                $("input:submit, a, button", ".action").button();

            });
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
<sec:ifAnyGranted roles="ROLE_PROREKTOR">
    <g:if test="${deanery!=null}">
        <div  align=center>
            <h3>
                Работает
                <a href="/plan/deanery/list" style="color: #2582A4">
                    фильтр</a> по деканату : ${deanery?.name}
            </h3>
        </div>
    </g:if>
</sec:ifAnyGranted>

    <div align="left">
        <div align="left" class="action">
            <g:link controller="deanery" action="create">
                Новый
            </g:link>
          </div>
    </div>

    <div id="container" align="center">
        <g:render template="/template/deanery/deaneryList" model="${[deaneryCollection: deaneryList, searchConfig: searchConfig]}"/>
    </div>

    <content tag="search">
        <g:render template="/template/deanery/searchTemplate"/>
    </content>

</body>
</html>
