<script type="text/javascript ">
    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide()
    });

    function deleteDialog(iid) {
        $("#delBtn").attr("href", '${request.contextPath + '/index/delete/'}' + iid);
        $("#dialog").dialog();
    }

    function importDialog(iid) {
        $("#importPlanId").val(iid);
        $("#dialogImportDBF").dialog();
    }

    function switchIcon(id) {
        var res = $("#details" + id).attr("src").match("expand");
        var src;
        if (null != res) {
            src = $("#details" + id).attr("src").replace("expand", "collapse");
        } else {
            src = $("#details" + id).attr("src").replace("collapse", "expand");
            $("#wPlans" + id).empty();
        }
        $("#details" + id).attr("src", src);
    }
</script>


<div align="left">
    <div id="post_list" style="margin: 0px;">
        <div class="subtitle" align="center">${msg}</div>
        <g:if test="${totalPlans == 0}">
            <div class="post">
                <h3 class="title">Пока что нету ни одного учебного плана...</h3>
            </div>
        </g:if>
        <g:each in="${res}" var="plan">
            <tr>
                <td colspan="6">
                    <h4 class="title">
                        <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
                            <g:link id="${plan.id}" action="index"
                                    controller="planInit">${plan.speciality.specialityCode} ${plan.speciality.name}</g:link>
                        </sec:ifAnyGranted>
                        <sec:ifAllGranted roles="ROLE_USER">
                            ${plan.speciality.specialityCode} ${plan.speciality.name}
                        </sec:ifAllGranted>
                    </h4>
                </td>
            </tr>
            <tr>
                <td width="20%">
                    <span style="font-size: small;">Форма обучения:</span>
                </td>
                <td width="40%">
                    <span style="font-size: small; text-decoration: underline;">${plan.form}</span>
                </td>
                <td width="10%">
                    <span style="font-size: small;">Cрок обучения:</span>
                </td>
                <td width="30%">
                    <span style="font-size: small; text-decoration: underline;">${plan.termin}</span>
                </td>

                <td rowspan="5" valign="bottom" width="10px" align="right">
                    <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
                        <tooltip:tip code="tooltip.plan.remove">
                            <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                                <input type="image"
                                       src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                            </a>
                        </tooltip:tip>
                    </sec:ifAnyGranted>
                </td>
                <td rowspan="5" valign="bottom" width="10px" align="right">
                    <g:if test="${faculty && plan.stateExam}">
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
                <td rowspan="5" valign="bottom" width="10px" align="right">
                    <tooltip:tip code="tooltip.plan.expand.work.plans">
                        <g:remoteLink action="showWorkPlans" id="${plan.id}" update="wPlans${plan.id}"
                                      onSuccess="switchIcon(${plan.id})">
                            <input id="details${plan.id}" type="image"
                                   src="<g:createLinkTo dir="/images" file="expand.png"/>">
                        </g:remoteLink>
                    </tooltip:tip>
                </td>
                <td rowspan="5" valign="bottom" width="10px" align="right">
                    <tooltip:tip code="tooltip.plan.incsv">
                        <a href="CSVExport/exportPlanToCSV/${plan?.id}" class="chengpl">
                            <img src="<g:createLinkTo dir="/images" file="cvs.png"/>" alt="CSV" class="chengpl">
                        </a>
                    </tooltip:tip>
                </td>
                <td rowspan="5" valign="bottom" width="10px" align="right">
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
            <tr>
                <td>
                    <span style="font-size: small;">Кафедра:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline">${plan.chair.name}
                    </span>
                </td>
                <td>
                    <span style="font-size: small;">Специальность:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline">${plan.speciality.name}</span>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="font-size: small;">Уровень:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline;">${plan.level}</span>
                </td>
                <td>
                    <span style="font-size: small;">Года:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline;">${plan.startYear}-${plan.endYear}</span>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="font-size: small;">Изменен:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline;">${plan.lastUpdated.toLocaleString()}</span>
                </td>
                <td>
                    <span style="font-size: small;">Cеместров:</span>
                </td>
                <td>
                    <span style="font-size: small; text-decoration: underline;">${plan.semestrCount}</span>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    <div id="wPlans${plan.id}"></div>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    <div class="post" style="margin-top: 0px; margin-bottom: 10px">
                    </div>
                </td></tr>
        </g:each>
    </div>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation" model="['askMessage': 'Вы точно хотите удалить этот план?']"/>
    </content>
</div>

