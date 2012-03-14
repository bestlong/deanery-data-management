<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление новых предметов</title>
    <meta name="layout" content="main"/>
    <g:render template="/template/planSubject/validation"/>
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