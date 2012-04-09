<%--
  Created by IntelliJ IDEA.
  User: drStout
  Date: 11.07.11
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>


<div id="updateChairDiv">
    <g:render template="/template/multipleDeleteItem"/>
    <div class="subtitle" align="center">Список кафедр</div>
    <table id="tableCont" class="display">
        <thead>
        <tr>
            <th><a onclick="selectUnselectAll()">Все</a></th>
            <th>Код</th>
            <th>Название</th>
            <th>Аббревиатура</th>
            <th>Заведующий</th>
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <th>Факультет</th>
            </sec:ifAnyGranted>
            <th>Предметы</th>
            <th>Планы</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${chairCollection}" var="chair">
            <tr id="${chair.referenceCount == 0 ? 'tr'+chair.id : ''}" name="${chair.referenceCount == 0 ? 'itemTr' : ''}">
                <td align="center">
                    <g:if test="${chair.referenceCount == 0}">
                        <tooltip:tip code="tooltip.del">
                            <g:hiddenField name="id" value="${chair.id}"/>
                            <g:checkBox name="multipleDelete${chair.id}" onclick="changeBackground(${chair.id})"/>
                        </tooltip:tip>
                    </g:if>
                </td>
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
                <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                    <td>${chair?.deanery?.shortName}</td>
                </sec:ifAnyGranted>
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
                                            <img src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                        </a>
                                    </tooltip:tip>
                                </g:if>
                                <g:else>
                                    <tooltip:tip code="tooltip.unable.remove">
                                        <img src="<g:createLinkTo dir="/images/ctrl" file="delete_disabled.gif"/>"/>
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

