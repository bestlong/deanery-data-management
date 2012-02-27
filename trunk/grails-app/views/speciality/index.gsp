<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Специальности</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript">

        function initTable() {
            $('#specialityList').dataTable({
                        "bJQueryUI":true,
                        "sPaginationType":"full_numbers",
                        "iDisplayLength":15,
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
                    }
            );
        }


        $(document).ready(function () {
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide();
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/speciality/delete/'}' + iid);
            $("#dialog").dialog();
        }

    </script>

</head>

<body>

<div  style="width: 100%">
    <div align="left">
        <tooltip:tip code="tooltip.add.speciality">
            <a href="<g:createLink action="add" controller="speciality"/>">
                <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>"/>
            </a>
        </tooltip:tip>
    </div>

    <g:render template="/template/speciality/specialityList" model="${[res: res]}"/>

    <content tag="search">
        <g:render template="/template/speciality/searchTemplate" model="['controller': 'speciality']"/>
    </content>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation"
                  model="['askMessage': 'Вы точно хотите удалить эту специальность?']"/>
    </content>
</div>
</body>
</html>


