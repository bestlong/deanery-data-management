<%@ page import="decanat.grails.Deanery" %>
<html>
<head>
    <title>Редактирование деканатов</title>
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
    <h4 class="subtitle">Редактировать деканат ${curDeanery?.name}:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${curDeanery}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="deanery" action="update" width="300">
        <g:hiddenField name="id" value="${curDeanery?.id}"/>
        <table class="editTable" align="center">
            <tr class="title">
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${curDeanery?.name}"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${curDeanery?.shortName}"/>
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
