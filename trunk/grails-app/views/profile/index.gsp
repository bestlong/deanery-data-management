<%@ page import="stu.cn.ua.enums.Roles" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/profile/init.js"/>
    <title>
        Мой профиль
    </title>
</head>

<body>
<div>
    <h4 class="subtitle">Изменить информацию:</h4>
    %{--<g:form controller="profile" action="update" width="300" name="profile">--}%
    <table class="editTable" align="center">
        <tr>
            <td class="caption">Логин*</td>
            <td>
                %{--<g:hiddenField name="id" value="${user.id}"/>--}%
                <g:textField name="username" value="${user?.username}" disabled="true"/>
            </td>
        </tr>
        <sec:ifNotGranted roles="ROLE_PROREKTOR">
            <tr>
                <td class="caption">Деканат</td>
                <td>
                    <g:textField name="faculty" value="${user?.faculty?.name}" disabled="true"/>
                </td>
            </tr>
        </sec:ifNotGranted>
    </table>
    <br/>

    <div align="center" class="action">
        <g:link controller="index" action="index">Отмена</g:link>
        %{--<g:submitButton name="save" value="Сохранить"/>--}%
        <a href="#" onclick="deleteDialog(${user?.id})" class="editPass">Изменить пароль</a>
    </div>
    %{--</g:form>--}%
</div>

<content tag="editPassword">
    <g:render template="/template/profile/editPassword"/>
</content>

</body>
</html>

