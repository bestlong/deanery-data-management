<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 04.08.11
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>

<div id="updatePlanSubjectDiv">
    <table id="subjects" class="display">
        <thead>
        <tr>
            <th>Предмет</th>
            <th>Кредит.</th>
            <th>Лекций</th>
            <th>Семин.</th>
            <th>Практ.</th>
            <th>Лаб.</th>
            <th>Самост.</th>
            <th>Всего</th>
            <th>Редакт.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="subject">
            <tr id="${subject.id}">
                <td>
                    <g:hiddenField name="count${subject.id}" value="${subject.count}"/>
                    ${subject.name}
                </td>
                <td>
                    ${subject.creditCount}
                </td>
                <td>
                    ${subject.lectureCount}
                </td>
                <td>
                    ${subject.seminarCount}
                </td>
                <td>
                    ${subject.practiceCount}
                </td>
                <td>
                    ${subject.labCount}
                </td>
                <td>
                    ${subject.samCount}
                </td>
                <td>
                    ${subject.total}
                </td>
                <td>
                    <tooltip:tip code="tooltip.edit">
                        <a href="<g:createLink action="edit" controller="addSubjects" id="${subject.id}"
                                               params="[planId: subject.planId]"/>"
                           style="margin: 5px; text-decoration: none">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                    <tooltip:tip code="tooltip.del">
                        <a href="#" onclick="deleteDialog(${subject?.id}, ${subject?.planId})" class="delBtn"
                           style="margin: 5px; text-decoration: none">
                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </td>
            </tr>
        </g:each>
        </tbody>
        <tfoot>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </tfoot>
    </table>
</div>