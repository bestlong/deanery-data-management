<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Редактирование практики</title>
    <meta name="layout" content="main"/>
    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>
<div id="latest-post">

    <div id="selectPart">
        <h4 class="subtitle">Редактирование практики:</h4>
        <g:form controller="practice" action="save" width="300" name="formSave">
            <g:hiddenField name="id" value="${practice.id}"/>
            <g:hiddenField name="planId" value="${plan.id}"/>
            <table id="controlType" align="center">

                <tr>
                    <td class="caption">Название*</td>
                    <td>
                        <g:textField name="name" value="${practice.name}"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">№ Семестра*</td>
                    <td>
                        <g:select name="semestr" from="${1..plan.semestrCount}" style="width: 100px" value="${practice.semestr}"></g:select>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Количество недель*</td>
                    <td>
                        <g:select name="weeks" from="${1..20}" style="width: 100px" value="${practice.weeks}"></g:select>
                    </td>
                </tr>
            </table>


            <div align="center" class="action">
                <a href="<g:createLink controller="practice" action="index"/>">Отмена</a>
                <g:submitButton name="save" value="Сохранить"/>
            </div>
        </g:form>
    </div>

</div>
   <content tag="search">
    <g:render template="/template/plan/menu" model="[ 'active' : 3, 'plan': plan ]"/>
</content>
</body>
</html>