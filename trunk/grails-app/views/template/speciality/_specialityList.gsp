<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

<div id="updateSpecialityDiv">
    <g:render template="/template/multipleDeleteItem"/>
    <div class="subtitle" align="center">Список специальностей</div>
    <table id="specialityList" class="display">
        <thead>
        <tr>
            <th><a onclick="selectUnselectAll()">Выделить все</a></th>
            <th>Код</th>
            <th>Код специальности</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="speciality">
            <tr id="${speciality.referenceCount == 0 ? 'tr'+speciality.id : ''}" name="${speciality.referenceCount == 0 ? 'itemTr' : ''}">
                <td align="center">
                    <g:if test="${speciality.referenceCount == 0}">
                        <tooltip:tip code="tooltip.del">
                            <g:hiddenField name="id" value="${speciality.id}"/>
                            <g:checkBox name="multipleDelete${speciality.id}" onclick="changeBackground(${speciality.id})"/>
                        </tooltip:tip>
                    </g:if>
                </td>
                <td>
                    ${speciality.code}
                </td>
                <td>
                    ${speciality.specialityCode}
                </td>
                <td>
                    ${speciality.name}
                </td>
                <td>
                    ${speciality.shortName}
                </td>
                <td>
                    <table>
                        <tr>
                            <td align="left" style="margin: 5px">
                                <tooltip:tip code="tooltip.edit">
                                    <a href="<g:createLink action="edit" controller="speciality"
                                                           id="${speciality?.id}"/>">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </td>
                            <td align="right" style="margin: 5px">
                                <g:if test="${speciality.referenceCount == 0}">
                                    <tooltip:tip code="tooltip.del">
                                        <a href="#" onclick="deleteDialog(${speciality?.id})" class="delBtn">
                                            <input type="image"
                                                   src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                        </a>
                                    </tooltip:tip>
                                </g:if>
                                <g:else>
                                    <tooltip:tip code="tooltip.unable.remove">
                                        <input type="image"
                                               src="<g:createLinkTo dir="/images/ctrl" file="delete_disabled.gif"/>"/>
                                    </tooltip:tip>
                                </g:else>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>