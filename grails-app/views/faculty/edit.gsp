<%@ page import="decanat.grails.Faculty" %>
<html>
<head>
    <title>Редактирование факультетов</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Faculty"/>

    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>

<div>
    <h4 class="subtitle">Редактировать факультет ${curFaculty?.name}:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${curFaculty}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="faculty" action="update" width="300">
        <g:hiddenField name="id" value="${curFaculty?.id}"/>
        <table class="editTable" align="center">
            <tr class="title">
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${curFaculty?.name}"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${curFaculty?.shortName}"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Проректор</td>
                <td>
                    <g:textField name="prorektor" value="${curFaculty?.prorektor}"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Декан</td>
                <td>
                    <g:textField name="dean" value="${curFaculty?.dean}"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <a href="<g:createLink controller="faculty" action="index"/>">Отмена</a>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>
</body>
</html>
