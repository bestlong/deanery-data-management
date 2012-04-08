<%@ page import="decanat.grails.domain.User" %>
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
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <th>Деканат</th>
            </sec:ifAnyGranted>
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
                <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                    <td>${user?.deanery?.name}</td>
                </sec:ifAnyGranted>
                <td width="20px">
                    <table>
                        <tr>
                            <td align="left" style="margin: 5px">
                                <tooltip:tip code="tooltip.edit">
                                    <a href="<g:createLink action="edit" controller="user" id="${user?.id}"/>">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </td>
                            <td align="right" style="margin: 5px">
                                <tooltip:tip code="tooltip.del">
                                    <a href="#" onclick="deleteDialog(${user?.id})" class="delBtn">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
