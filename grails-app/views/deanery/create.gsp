%{--TODO если введены поля неправильно, то должны отображаться соответсвующие сообщения а не Поле [name] класса [class decanat.grails.Deanery] не может быть пустым--}%
<%@ page import="decanat.grails.Deanery" %>
<html>
<head>
    <title> Добавление деканатов</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Deanery"/>

    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>

<div>
    <h4 class="subtitle">Добавить новый деканат:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${deaneryInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="deanery" action="save" width="300">
        <table class="editTable" align="center">
            <tr class="title">
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${deaneryInstance?.name}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${deaneryInstance?.shortName}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Проректор</td>
                <td>
                    <g:textField name="prorektor" value="${deaneryInstance?.prorektor}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Декан</td>
                <td>
                    <g:textField name="dean" value="${deaneryInstance?.dean}" class="normal_input"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <a href="<g:createLink controller="deanery" action="index"/>">Отмена</a>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>
