<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Deanery; stu.cn.ua.enums.Roles; decanat.grails.domain.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/user/init.js"/>
    <g:javascript>
        $(function () {
            $('#roleId').change(function () {
                showHideFaculty();
            });
            showHideFaculty();
        });

        function showHideFaculty(){
                var str = $("#roleId option:selected").text();
                if (str != '${Roles.PROREKTOR.text}') {
                    $("#trFaculty").show();
                } else {
                    $("#trFaculty").hide();
                }
        }
    </g:javascript>
    <title>
        Добавить нового пользователя
    </title>
</head>

<body>

<h4 class="subtitle">Добавить нового пользователя в систему:</h4>

<g:form controller="user" action="save" width="300" method="post" name="addUserForm">
    <table class="editTable" align="center">
        <tr>
            <td class="caption">Роль<span style="color: red">*</span></td>
            <td align="left">
                <g:select from="${roles}" optionValue="description" optionKey="id" name="roleId"
                          value="${authority}"/>
            </td>
        </tr>
        <sec:ifAnyGranted roles="ROLE_PROREKTOR">
            <tr id="trFaculty">
                <td class="caption">Факультет*</td>
                <td>
                    <g:select from="${Deanery.list()}" optionValue="name" optionKey="id" name="facultyId"/>
                </td>
            </tr>
        </sec:ifAnyGranted>
        <tr>
            <td class="caption">Логин<span style="color: red">*</span></td>
            <td>
                <g:textField name="username"/>
            </td>
        </tr>
        <tr>
            <td class="caption">Пароль<span style="color: red">*</span></td>
            <td>
                <g:passwordField name="password"/>
            </td>
        </tr>
        <tr>
            <td class="caption">Повторите пароль<span style="color: red">*</span></td>
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


