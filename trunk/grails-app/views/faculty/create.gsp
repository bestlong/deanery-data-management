%{--TODO если введены поля неправильно, то должны отображаться соответсвующие сообщения а не Поле [name] класса [class decanat.grails.Faculty] не может быть пустым--}%
<%@ page import="decanat.grails.Faculty" %>
<html>
<head>
    <title> Добавление факультетов</title>
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
    <h4 class="subtitle">Добавить новый факультет:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${facultyInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="faculty" action="save" width="300">
        <table class="editTable" align="center">
            <tr class="title">
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${facultyInstance?.name}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${facultyInstance?.shortName}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Проректор</td>
                <td>
                    <g:textField name="prorektor" value="${facultyInstance?.prorektor}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Декан</td>
                <td>
                    <g:textField name="dean" value="${facultyInstance?.dean}" class="normal_input"/>
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
