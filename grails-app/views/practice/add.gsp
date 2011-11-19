<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление новой практики</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>
<div id="latest-post">

    <h4 class="subtitle">Добавление практик:</h4>
    <g:form controller="practice" action="save" width="300" name="formSave">
        <g:hiddenField name="planId" value="${params.id}"/>
        <table id="controlType" align="center">
            <tr>
                <td class="caption">Название*</td>
                <td>
                    <g:textField name="name"/>
                    <g:hiddenField name="practiceId"/>
                </td>
            </tr>

            <tr>
                <td class="caption">№ Семестра*</td>
                <td>
                    <g:select name="semestr" from="${1..plan.semestrCount}" style="width: 100px"></g:select>

                </td>
            </tr>

            <tr>
                <td class="caption">Количество недель*</td>
                <td>

                    <g:select name="weeks" from="${1..20}" style="width: 100px"></g:select>
                </td>
            </tr>

        </table>

        <div align="center" class="action">
            <a href="<g:createLink controller="practice" action="index" id="${plan.id}"/>">Отмена</a>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>
 <content tag="search">
    <g:render template="/template/plan/menu" model="[ 'active' : 3, 'plan': plan ]"/>
</content>
</body>
</html>