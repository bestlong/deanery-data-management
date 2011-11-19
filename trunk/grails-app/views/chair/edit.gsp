<%--
  Created by IntelliJ IDEA.
  User: drStout
  Date: 12.07.11
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Chair" %>
<html>
<head>
    <title>Редактирование кафедр</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Chair"/>

    <script type="text/javascript ">
        $(function() {
            $("input:submit, a, button", ".action").button();
        });
    </script>
</head>

<body>

<div id="latest-post">
    <h4 class="subtitle">Редактировать кафедру ${curChair?.name}:</h4>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${curChair}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="chair" action="update" width="300">
        <g:hiddenField name="id" value="${curChair?.id}"/>
        <table id="editTable" align="center">
            <tr class="title">
                <td>Имя</td>
                <td>
                    <g:textField name="name" value="${curChair?.name}"/>
                </td>
            </tr>
            <tr class="title">
                <td>Короткое имя</td>
                <td>
                    <g:textField name="shortName" value="${curChair?.shortName}"/>
                </td>
            </tr>
            <tr class="title">
                <td class="caption">Заведующий*</td>
                <td>
                    <g:textField name="head" value="${curChair?.head}" class="normal_input"/>
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
