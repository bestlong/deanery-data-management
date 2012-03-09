<div id="updatePlanPractiseDiv">
    %{--<div class="subtitle" align="center">Список практик</div>--}%
    <table id="practices" class="display">
        <thead>
        <tr>
            <th>Название</th>
            <th>№ Семестра</th>
            <th>Количество недель</th>
            <th>Ред.</th>

        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="practice">
            <tr id="${practice.id}">
                <td>
                    <g:hiddenField name="count${practice.id}"/>
                    ${practice.name}
                </td>
                <td>
                    ${practice.semestr}
                </td>
                <td>
                    ${practice.weekCount}
                </td>
                <td>
                    <table width="100%">
                        <tr align="center">
                            <td align="left" style="margin: 5px">
                                <tooltip:tip code="tooltip.edit">
                                    <a href="<g:createLink action="edit" controller="practice" id="${practice.id}"
                                                           params="[planId: practice.planId]"/>">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </td>
                            <td align="right" style="margin: 5px">
                                <tooltip:tip code="tooltip.del">
                                    <a href="#" onclick="deleteDialog(${practice?.id}, ${practice?.planId})" class="delBtn">
                                        <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                    </a>
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