
<table style="margin-left: 100px;" cellspacing="3">
    <tr>
        <td align="center" style="text-decoration: underline; font-size: 14px" class="subtitle">
            Список рабочих планов:
        </td>
    </tr>
    <g:if test="${!plans}">
        <tr>
            <td>
                <h5 class="title">Пока что нету ни одного рабочего плана...</h5>
            </td>
        </tr>
    </g:if>
    <g:each in="${plans}" var="plan">
        <tr>
            <td width="400px" style="font-size: 12px">
                <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
                    <g:link id="${plan.id}" action="index"
                            controller="planInit">${plan.name}</g:link>
                </sec:ifAnyGranted>
                <sec:ifAllGranted roles="ROLE_USER">
                    ${plan.name}
                </sec:ifAllGranted>
            </td>
            <td >
                <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
                    <tooltip:tip code="tooltip.plan.remove">
                        <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                            <img src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                        </a>
                    </tooltip:tip>
                </sec:ifAnyGranted>
            </td>
            <td >
                 <a href="CSVExport/exportWorkPlanToCSV/${plan?.id}" class="chengpl" >
                    <img src="<g:createLinkTo dir="/images" file="cvs.png"/>" alt="CSV" class="chengpl">
                </a>
             </td>
            <td >
                <g:if test="${univer && plan.stateExam}">
                    <tooltip:tip code="tooltip.plan.print">
                        <a style="align: right"
                           href="<g:createLink action="print" controller="index"
                                               id="${plan?.id}"/>">
                            <img src="<g:createLinkTo dir="/images" file="excel.gif"/>">
                        </a>
                    </tooltip:tip>
                </g:if>
                <g:else>
                    <tooltip:tip code="tooltip.plan.notPrint">
                        <img src="<g:createLinkTo dir="/images" file="excel.gif"/>">
                    </tooltip:tip>
                </g:else>
            </td>
            <td>
                <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
                    <tooltip:tip code="tooltip.plan.dbf.import">
                        <a href="#" class="delPlan" onclick="importDialog(${plan?.id})">
                            <input type="image"
                                   src="<g:createLinkTo dir="/images" file="dbf.PNG"/>"/>
                        </a>
                    </tooltip:tip>
                </sec:ifAnyGranted>
            </td>
        </tr>
    </g:each>
</table>
