<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

%{--<%@ page import="decanat.grails.User" contentType="text/html;charset=UTF-8" %>--}%
<div id="updateSpecialityDiv">
    <table id="specialityList" class="display">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Короткое имя</th>
            <th>Код</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="speciality">
            <tr>
                <td>
                    <a href="#" onclick="chooseSpeciality('${speciality?.id}', '${speciality?.name}')" class="delBtn">
                        ${speciality.name}
                    </a>
                </td>
                <td>
                    ${speciality.shortName}
                </td>
                <td>
                    ${speciality.kod}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>