<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление новых предметов</title>
    <meta name="layout" content="main"/>
    <g:javascript>
        $(function () {
            $("#tabs").tabs();
            $("input[type=text]").numeric({ decimal:false, negative:false }, null);
            $('#searchSubject').hide();
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide();
            $("#selectSubj").button();
        });

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
                    { sWidth:"25%" }
                ]
            });
        }

        function showOrHide(show) {
            $('#selectSubj').qtip('destroy');
            $("#searchPart").toggle(show);
            $("#selectPart").toggle(!show);
            $("#searchSubject").toggle(show);
            $("#menuBox").toggle(!show);
        }

        function chooseSubj(id, name) {
            $("#subjName").html(name);
            $("#subjId").val(id);
            showOrHide(false);
        }
    </g:javascript>
    %{--<g:render template="/template/planSubject/validation"/>--}%
</head>

<body>
<div>
    <content tag="search">
        <g:render template="/template/subject/searchTemplate" model="['controller': 'addSubjects']"/>
        <g:render template="/template/plan/menu" model="['active': 2, 'plan': plan]"/>
    </content>

    <div id="searchPart" style="display: none;">
        <h4 class="subtitle">для выбора предмета нажмите на его имя:</h4>
        <g:render template="/template/subject/selectSubject" model="${[res: res]}"/>
        <div align="center" class="action">
            <a onclick="showOrHide(false)">Отмена</a>
        </div>
    </div>

    <div id="selectPart">
        <h4 class="subtitle">Добавление предметов:</h4>
        <g:form controller="addSubjects" action="save" width="500" name="formSave">
            <div>
                <div id="tabs" style="background-color: white; background: none">
                    <ul>
                        <li><a href="#tabs-1" style="font-size: 14px; background: none">Предмет</a></li>
                        <li><a href="#tabs-2" style="font-size: 14px; background: none">Семестры</a></li>
                    </ul>

                    <div id="tabs-1">
                        <g:render template="/template/planSubject/addSubjectInGeneral"/>
                    </div>

                    <div id="tabs-2">
                        <g:render template="/template/subject/addSubjects"
                                  model="['semesterCount': plan?.semestrCount]"/></div>
                </div>
                <table width="30%" align="center">
                    <tr>
                        <td align="center" class="action">
                            <a href="<g:createLink controller="addSubjects" action="index" id="${plan.id}"/>">Отмена</a>
                        </td>
                        <td align="center" class="action">
                            <g:submitButton name="save" value="Сохранить"/>
                        </td>
                    </tr>
                </table>
            </div>
        </g:form>
    </div>
</div>

</body>
</html>