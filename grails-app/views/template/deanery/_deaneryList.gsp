<%--
  Created by IntelliJ IDEA.
  User: Mustang
  Date: 26.03.12
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>

<div id="updateDeaneryDiv">
    <div class="subtitle" align="center">Список Деканатов</div>
    <table id="tableCont" class="display">
        <thead>
        <tr>
            <th>Название</th>
            <th>Аббревиатура</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${deaneryCollection}" var="deanery">
            <tr id="${0 ? 'tr'+deanery.id : ''}" name="${0 ? 'itemTr' : ''}">
                <td>
                    ${deanery?.name}
                </td>
                <td>
                    ${deanery?.shortName}
                </td>
                <td class="edit" width="10px">
                    <table>
                        <tr>
                            <td align="left" style="margin-left: 5px; margin-right: 5px">
                                <tooltip:tip code="tooltip.deanery.edit">
                                    <g:link class="edBtn" controller="deanery" action="edit" params="${[id: deanery?.id]}">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
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