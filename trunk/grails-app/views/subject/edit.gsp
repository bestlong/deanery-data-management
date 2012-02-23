<%--
  Created by IntelliJ IDEA.
  User: corwin
  Date: 13.07.11
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Chair" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Редактирование предметов</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Subject"/>

</head>

<body>
<script type="text/javascript ">
    $(function () {
        $("input:submit, a, button", ".action").button();
    });
</script>

<div>
    <h4 class="subtitle">Редактировать предмет:</h4>
    <g:form controller="subject" action="update" width="300">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">Кафедра*</td>
                <td>
                    <g:hiddenField name="id" value="${subject.id}"/>
                    <g:select from="${Chair.list()}" optionValue="name" optionKey="id" name="subject.chair"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${subject?.name}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Короткое имя*</td>
                <td>
                    <g:textField name="shortName" value="${subject?.shortName}"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="subject" action="index">Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>