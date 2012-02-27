<%@ page import="decanat.grails.domain.Role; decanat.grails.domain.User" %>
<%--
  author: evgeniy
  Date: 27.06.11
  Time: 23:44
--%>

<html>
<head>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.domain.User"/>
     <title>
        Редикторовать пользователя
    </title>
</head>
<body>
<script type="text/javascript ">
    $(function() {
        $("input:submit, a, button", ".action").button();
    });
</script>

<div>
    <h4 class="subtitle">Редактировать пользователя ${user?.username}:</h4>

    <g:if test="${params?.login}">
    <g:set var="userEdit" value="${User.findByUsername(params?.login)}"/>
        </g:if>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${user}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="user" action="update" width="300">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">Роль*</td>
                <td align="left">
                    <g:hiddenField name="id" value="${user.id}"/>
                    <g:select from="${Role.list()}" optionValue="description" optionKey="id" name="user.role"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Логин*</td>
                <td>
                    <g:textField name="username" value="${user?.username}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Пароль*</td>
                <td>
                    <g:passwordField name="passwd"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="user" action="index" >Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>


