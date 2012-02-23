<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/user/init.js"/>
     <title>
        Добавить нового пользователя
    </title>
</head>

<body>

<h4 class="subtitle">Добавить нового пользователя в систему:</h4>

<g:form controller="user" action="save" width="300" method="post">
    <table class="editTable" align="center">
        <tr>
            <td class="caption">Роль*</td>
            <td align="left">
                <g:select from="${Role.list()}" optionValue="description" optionKey="id" name="user.role"
                          value="${authority}"/>
            </td>
        </tr>
        <tr>
            <td class="caption">ФИО*</td>
            <td>
                <g:textField name="userRealName"/>
            </td>
        </tr>
        <tr>
            <td class="caption">Email*</td>
            <td>
                <g:textField name="email"/>
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
                <g:textField name="username"/>
            </td>
        </tr>
        <tr>
            <td class="caption">Пароль*</td>
            <td>
                <g:passwordField name="passwd"/>
            </td>
        </tr>
        <tr>
            <td class="caption">Повторите пароль*</td>
            <td>
                <g:passwordField name="passwdRetype"/>
            </td>
        </tr>
    </table>
    <br/>

    <div align="center" class="action">
        <a href="<g:createLink controller="user" action="index"/>">Отмена</a>
        <g:submitButton name="save" value="Сохранить"/>
    </div>
</g:form>

</body>
</html>


