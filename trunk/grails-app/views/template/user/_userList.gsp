<%@ page import="decanat.grails.User" %>
<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

<div id="updateUserDiv">
    <div class="subtitle" align="center">Список пользователей</div>

    <table id="users" class="display">
        <thead>
        <tr>
            <th>Логин</th>
            <th>Роль</th>
            <th>ФИО</th>
            <th>e-mail</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="user">

            <tr>
                <td>
                    ${user?.username}
                </td>
                <td>
                    ${user?.authorities?.description}
                </td>
                <td>
                    ${user?.userRealName}
                </td>
                <td>
                    ${user?.email}
                </td>
                <td>
                    <tooltip:tip code="tooltip.edit">
                        <a href="<g:createLink action="edit" controller="user" id="${user?.id}"/>">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                    <tooltip:tip code="tooltip.del">
                        <a href="#" onclick="deleteDialog(${user?.id})" class="delBtn">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
