<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

%{--<%@ page import="decanat.grails.User" contentType="text/html;charset=UTF-8" %>--}%
<div id="updateSpecialityDiv">
    <div class="subtitle" align="center">Список специальностей</div>
    <table id="specialityList" class="display">
        <thead>
        <tr>
            <th>Код</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <th>Редактирование</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="speciality">
            <tr>
                <td>
                    ${speciality.kod}
                </td>
                <td>
                    ${speciality.name}
                </td>
                <td>
                    ${speciality.shortName}
                </td>
                <td>
                    <tooltip:tip code="tooltip.edit">
                        <a href="<g:createLink action="edit" controller="speciality" id="${speciality?.id}"/>">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                    <tooltip:tip code="tooltip.del">
                        <a href="#" onclick="deleteDialog(${speciality?.id})" class="delBtn">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>