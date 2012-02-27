<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

<div id="updatePlanSubjectDiv">
    <table id="subjects" class="display">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Кафедра</th>
            <th>Короткое имя</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="subject">
            <tr>
                <td>
                    <a href="#" onclick="chooseSubj('${subject?.id}', '${subject?.name}')" class="delBtn">
                        ${subject.name}
                    </a>
                </td>
                <td>
                    ${subject.chair.name}
                </td>
                <td>
                    ${subject.shortName}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
