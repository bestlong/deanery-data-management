<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Role" contentType="text/html;charset=UTF-8" %>
<%@ page import="decanat.grails.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.User"/>
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

<div id="latest-post">
    %{--<div >--}%
    <h4 class="subtitle">Редактировать пользователя ${user?.userRealName}:</h4>
%{--</div>--}%

    <g:if test="${params?.login}">
    <g:set var="userEdit" value="${User.findByUsername(params?.login)}"/>
        </g:if>
    <g:hasErrors>
        <div style="color: red;">
            <g:renderErrors bean="${user}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form controller="user" action="update" width="300">
        <table id="editTable" align="center">
            <tr>
                <td class="caption">Роль*</td>
                <td align="left">
                    <g:hiddenField name="id" value="${user.id}"/>
                    <g:select from="${Role.list()}" optionValue="description" optionKey="id" name="user.role"
                               style="width:150px; padding: 3px; margin: 0"/>
                </td>
            </tr>
            <tr>
                <td class="caption">ФИО*</td>
                <td>
                    <g:textField name="userRealName" value="${user?.userRealName}" />
                </td>
            </tr>
            <tr>
                <td class="caption">Email*</td>
                <td>
                    <g:textField name="email" value="${user?.email}" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="subtitle">Информация для авторизации:</div>
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


