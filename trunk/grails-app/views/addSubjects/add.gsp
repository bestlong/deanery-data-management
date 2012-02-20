<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление новых предметов</title>
    <meta name="layout" content="main"/>
    <g:render template="/template/planSubject/validation"/>
</head>

<body>
<div>
    <content tag="search">
        <g:render template="/template/subject/searchTemplate" model="['controller':'addSubjects']"/>
        <g:render template="/template/plan/menu" model="[ 'active' : 2, 'plan': plan ]"/>
    </content>

    <div id="searchPart" style="display: none;">
        <h4 class="subtitle">для выбора предмета нажмите на его имя:</h4>
        <g:render template="/template/subject/selectSubject" model="${[res: res]}"/>
        <div align="center" class="action">
            <a href="" onclick="showOrHide(false)">Отмена</a>
        </div>
    </div>


    <div id="selectPart">
        <h4 class="subtitle">Добавление предметов:</h4>
        <g:form controller="addSubjects" action="save" width="300" name="formSave">
            <g:hiddenField name="planId" value="${plan.id}"/>
            <table id="controlType" align="center">
                <tr>
                    <td class="caption">Предмет*</td>
                    <td>
                        <span style="font-size: 14px;" id="subjName"></span>
                        <a id="selectSubj" href="#" onclick="showOrHide(true)" class="action">Выбрать предмет</a>
                        <g:hiddenField name="subjId"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество кредитов*</td>
                    <td>
                        <g:textField name="creditCount"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество лекций*</td>
                    <td>
                        <g:textField name="lectureCount"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество семинаров*</td>
                    <td>
                        <g:textField name="seminarCount"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество практических занятий*</td>
                    <td>
                        <g:textField name="practiceCount"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество лабораторных*</td>
                    <td>
                        <g:textField name="labCount"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество самостоятельных работ*</td>
                    <td>
                        <g:textField name="samCount"/>
                    </td>
                </tr>

            </table>
            <g:render template="/template/subject/addSubjects" model="[ 'semestr' : plan?.semestrCount]"/>
            <br/><br/><br/>


            <div align="center" class="action">
                <a href="<g:createLink controller="addSubjects" action="index" id="${plan.id}"/>">Отмена</a>
                <g:submitButton name="save" value="Сохранить"/>
            </div>
        </g:form>
    </div>

</div>

</body>
</html>