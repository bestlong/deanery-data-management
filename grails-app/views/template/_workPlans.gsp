<table style="margin-left: 100px;" cellspacing="3">
    <tr>
        <td align="center" style="text-decoration: underline; font-size: 14px" class="subtitle">
            Список рабочих планов:
        </td>
    </tr>
    <g:each in="${plans}" var="plan">
        <tr>
            <td width="400px" style="font-size: 12px">
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <g:link id="${plan.id}" action="index"
                            controller="selectSpeciality">${plan.name}</g:link>
                </sec:ifAllGranted>
                <sec:ifAllGranted roles="ROLE_USER">
                    ${plan.name}
                </sec:ifAllGranted>
            </td>
            <td >
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <tooltip:tip code="tooltip.plan.remove">
                        <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                            <input type="image"
                                   src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </sec:ifAllGranted>
            </td>
            <td >
                <g:if test="${univer && plan.stateExam}">
                    <tooltip:tip code="tooltip.plan.print">
                        <a style="align: right"
                           href="<g:createLink action="print" controller="index"
                                               id="${plan?.id}"/>">
                            <input type="image"
                                   src="<g:createLinkTo dir="/images" file="excel.gif"/>">
                        </a>
                    </tooltip:tip>
                </g:if>
                <g:else>
                    <tooltip:tip code="tooltip.plan.notPrint">
                        <input type="image"
                               src="<g:createLinkTo dir="/images" file="excel.gif"/>">
                    </tooltip:tip>
                </g:else>
            </td>
        </tr>
    </g:each>
</table>