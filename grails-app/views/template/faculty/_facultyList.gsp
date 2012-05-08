<%--
  Created by IntelliJ IDEA.
  User: Mustang
  Date: 26.03.12
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>


<div id="updateFacultyDiv">
    <div class="subtitle" align="center">Список Факультетов</div>
    <table id="tableCont" class="display">
        <thead>
        <tr>
            <th>Название</th>
            <th>Аббревиатура</th>
            <th>Проректор</th>
            <th>Декан</th>
            <th>Фильтр</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${facultyCollection}" var="faculty">
            <tr id="${0 ? 'tr'+faculty.id : ''}" name="${0 ? 'itemTr' : ''}">
                <td>
                    ${faculty?.name}
                </td>
                <td>
                    ${faculty?.shortName}
                </td>
                <td>
                    ${faculty?.prorektor}
                </td>
                <td>
                    ${faculty?.dean}
                </td>
                <td width="5px">
                    <g:if test="${faculty?.id!=idFaculty}">
                    <a style="align: right"
                       href="../Faculty/setFilterToFaculty?id=${faculty?.id}">
                        Установить
                    </a>
                    </g:if>
                    <g:if test="${faculty?.id==idFaculty}">
                        <a style="align: right"
                           href="../Faculty/removeFilterToFaculty?id=${faculty?.id}" >
                            Снять
                        </a>
                    </g:if>

                </td>
                <td class="edit" width="10px">
                    <table>
                        <tr>
                            <td align="left" style="margin-left: 5px; margin-right: 5px">
                                <tooltip:tip code="tooltip.faculty.edit">
                                    <g:link class="edBtn" controller="faculty" action="edit" params="${[id: faculty?.id]}">
                                        <img src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </g:link>
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