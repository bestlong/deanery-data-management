<%--
  Created by IntelliJ IDEA.
  User: drStout
  Date: 11.07.11
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>


<div id="updateChairDiv">
    <div class="subtitle" align="center">Список кафедр</div>
    <table id="tableCont" class="display">
        <thead>
        <tr>
            <th>Код</th>
            <th>Название</th>
            <th>Аббревиатура</th>
            <th>Заведующий</th>
            <th>Предметы</th>
            <th>Планы</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${chairCollection}" var="chair">
            <tr>
                <td>
                    ${chair?.codeChair}
                </td>
                <td>
                    ${chair?.name}
                </td>
                <td>
                    ${chair?.shortName}
                </td>
                <td>
                    ${chair?.head}
                </td>
                <td>
                     <a href="<g:createLink action="specialitiesSubjects" controller="subject" params="${[id: chair?.id]}"/>">
                         Показать
                    </a>
                </td>
                <td>
                     <a href="<g:createLink action="chairPlans" controller="index" params="${[id: chair?.id]}"/>">
                         Показать
                    </a>
                </td>
                <td class="edit" width="10px">
                    <table>
                        <tr>
                            <td align="left" style="margin-left: 5px; margin-right: 5px">
                                <tooltip:tip code="tooltip.chair.edit">
                                    <g:link class="edBtn" controller="chair" action="edit" params="${[id: chair?.id]}">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </g:link>
                                </tooltip:tip>
                            </td>
                            <td align="right" style="margin-left: 5px; margin-right: 5px">

                                <g:if test="${0 == chair.referenceCount}">
                                    <tooltip:tip code="tooltip.chair.remove">
                                        <a href="#" class="delBtn" onclick="deleteDialog(${chair?.id})">
                                            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                        </a>
                                    </tooltip:tip>
                                </g:if>
                                <g:else>
                                    <tooltip:tip code="tooltip.unable.remove">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="delete_disabled.gif"/>"/>
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

