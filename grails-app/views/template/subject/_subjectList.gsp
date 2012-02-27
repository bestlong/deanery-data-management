<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

<div id="updatePlanSubjectDiv">
    <div class="subtitle" align="center">Список предметов</div>
    <table id="subjects" class="display">
        <thead>
        <tr>
            <th>Кафедра</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <th>Редактирование</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="subject">
            <tr>
                <td>
                    ${subject.chair.name}
                </td>
                <td>
                    ${subject.name}
                </td>
                <td>
                    ${subject.shortName}
                </td>
                <td>
                    <tooltip:tip code="tooltip.edit">
                        <a href="<g:createLink action="edit" controller="subject" id="${subject.id}"/>">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                    <tooltip:tip code="tooltip.del">
                        <a href="#" onclick="deleteDialog(${subject?.id})" class="delBtn">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
