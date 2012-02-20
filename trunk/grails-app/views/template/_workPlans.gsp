<table style="margin-left: 100px;" cellspacing="3">
    <tr>
        <td align="center" style="text-decoration: underline; font-size: 14px" class="subtitle">
            Список рабочих планов:
        </td>
    </tr>
    <g:each in="${plans}" var="plan">
        <tr>
            <td width="400px" style="font-size: 12px">
                <g:ifAllGranted role="ROLE_ADMIN">
                    <g:link id="${plan.id}" action="index"
                            controller="selectSpeciality">${plan.name}</g:link>
                </g:ifAllGranted>
                <g:ifAllGranted role="ROLE_USER">
                    ${plan.name}
                </g:ifAllGranted>
            </td>
            <td >
                <g:ifAllGranted role="ROLE_ADMIN">
                    <tooltip:tip code="tooltip.plan.remove">
                        <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                            <input type="image"
                                   src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </g:ifAllGranted>
            </td>
            <td >
                <g:if test="${univer && plan.stateExam}">
                    <tooltip:tip code="tooltip.plan.print">
                        <a style="align: right"
                           href="<g:createLink action="index" controller="printer"
                                               id="${plan?.id}"/>">
                            <input type="image"
                                   src="<g:createLinkTo dir="/images" file="print_printer.png"/>">
                        </a>
                    </tooltip:tip>
                </g:if>
                <g:else>
                    <tooltip:tip code="tooltip.plan.notPrint">
                        <input type="image"
                               src="<g:createLinkTo dir="/images" file="print_printer.png"/>">
                    </tooltip:tip>
                </g:else>
            </td>
        </tr>
    </g:each>
</table>