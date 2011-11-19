<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Chair; decanat.grails.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление предметов</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Subject"/>
</head>
<body>

<script type="text/javascript ">
    $(function() {
        $("input:submit, a, button", ".action").button();
    });
</script>

<div id="latest-post">
    <h4 class="subtitle">Добавить новый предмет в систему:</h4>
    <g:form controller="subject" action="save" width="300" >
        <table id="editTable" align="center">
            <tr>
                <td class="caption">Кафедра*</td>
                <td>
                    <g:select from="${Chair.list()}" optionValue="name" optionKey="id" name="subject.chair"
                               style="width:150px; padding: 3px; margin: 0"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${subject?.name}" />
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
            <g:link controller="subject" action="index" >Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>


