<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main"/>
<g:javascript src="/init/profile/init.js"/>
<title>
    Мой профиль
</title>
</head>

<div id="latest-post">
    %{--<div >--}%
    <h4 class="subtitle">Изменить информацию:</h4>
    <g:form controller="profile" action="update" width="300" name="profile">
        <table id="editTable" align="center">
            <tr>
                <td class="caption">Логин*</td>
                <td>
                    <g:hiddenField name="id" value="${user.id}"/>
                    <g:textField name="username" value="${user.username}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Полное имя*</td>
                <td>
                    <g:textField name="userRealName" value="${user.userRealName}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">E-mail*</td>
                <td>
                    <g:textField name="email" value="${user.email}"/>
                </td>

            </tr>

        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="index" action="index">Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
            <a href="#" onclick="deleteDialog(${user?.id})" class="editPass">Изменить пароль</a>

        </div>
    </g:form>
</div>

<content tag="editPassword">
    <g:render template="/template/profile/editPassword"/>
</content>

</body>
</html>

