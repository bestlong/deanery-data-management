<%@ page import="decanat.grails.domain.Role; decanat.grails.domain.User" %>
<%--
  author: evgeniy
  Date: 27.06.11
  Time: 23:44
--%>

<html>
<head>
    <g:javascript>
        function editPassDialog(iid) {
            $("#editPass").attr("href", '/plan/user/edit/' + iid);
            $("#dialog").dialog();
        }
    </g:javascript>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/user/init.js"/>
     <title>
        Редактировать пользователя
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
    <g:form controller="user" action="update" width="300" name="editUserForm">
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
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="user" action="index" >Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
            <a href="#" onclick="editPassDialog(${user?.id})" class="editPass">Изменить пароль</a>
        </div>
    </g:form>
</div>

<content tag="editPassword">
    <g:render template="/template/user/editPasword"/>
</content>

</body>
</html>


