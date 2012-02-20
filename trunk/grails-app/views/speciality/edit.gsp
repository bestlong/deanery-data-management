<%--
  Created by IntelliJ IDEA.
  User: corwin
  Date: 13.07.11
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Редактирование специальности</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Speciality"/>

</head>
<body>
<script type="text/javascript ">
    $(function() {
        $("input:submit, a, button", ".action").button();
    });
</script>

<div>
    <h4 class="subtitle">Редактировать специальность:</h4>

    <g:form controller="speciality" action="update" width="300">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">Код*</td>
                <td>
                    <g:hiddenField name="id" value="${speciality.id}"/>
                    <g:textField name="kod" value="${speciality?.kod}" />
                </td >
            </tr>
            <tr>
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${speciality?.name}" />
                </td>
            </tr>
            <tr>
                <td class="caption">Короткое имя*</td>
                <td>
                    <g:textField name="shortName" value="${speciality?.shortName}"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="speciality" action="index" >Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>