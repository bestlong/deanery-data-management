<%@ page import="decanat.grails.Chair" %>
<html>
<head>
    <title> Добавление кафедр</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Chair"/>

    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>

<div>
    <h4 class="subtitle">Добавить новую кафедру:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${chairInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="chair" action="save" width="300">
        <table class="editTable" align="center">
            <tr class="title">
                <td class="caption">Код*</td>
                <td>
                    <g:textField name="codeChair" value="${chairInstance?.codeChair}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${chairInstance?.name}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${chairInstance?.shortName}" class="normal_input"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Заведующий*</td>
                <td>
                    <g:textField name="head" value="${chairInstance?.head}" class="normal_input"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <a href="<g:createLink controller="chair" action="index"/>">Отмена</a>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>
