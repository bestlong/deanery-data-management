<script type="text/javascript ">

    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide()
        $("#dialogcsv").hide()
    });

    function chengplan(iid) {
        $("#chengpl").attr("href", '${request.contextPath + '/CSVExport/exportPlanToCSV/'}' + iid);
        $("#dialogcsv").dialog();
    }

    function chengworkplan(iid) {
        $("#chengpl").attr("href", '${request.contextPath + '/CSVExport/exportWorkPlanToCSV/'}' + iid);
        $("#dialogcsv").dialog();
    }

    function deleteDialog(iid) {
        $("#delBtn").attr("href", '${request.contextPath + '/index/delete/'}' + iid);
        $("#dialog").dialog();
    }

    function switchIcon(id){
        var res = $("#details"+id).attr("src").match("expand");
        var src;
        if (null != res){
            src = $("#details"+id).attr("src").replace("expand", "collapse");
        } else {
            src = $("#details"+id).attr("src").replace("collapse", "expand");
            $("#wPlans"+id).empty();
        }
        $("#details"+id).attr("src", src);
    }
</script>




<div>
    <div id="post_list">
        <div class="subtitle" align="center">${msg}</div>
        <g:if test="${totalPlans == 0}">
            <div class="post">
                <h3 class="title">Пока что нету ни одного учебного плана...</h3>
            </div>
        </g:if>
        <g:each in="${res}" var="plan">
            <div class="post">
                <table style="font-size: 16px; width: 100%">
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
                        <td width="80%">
                            <span style="font-size: small; text-decoration: underline;">${plan.form}</span>
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
                        <td rowspan="5" valign="bottom" width="10px" align="right">
                            <tooltip:tip code="tooltip.plan.expand.work.plans">
                                <g:remoteLink action="showWorkPlans" id="${plan.id}" update="wPlans${plan.id}" onSuccess="switchIcon(${plan.id})">
                                    <input id="details${plan.id}" type="image" src="<g:createLinkTo dir="/images" file="expand.png"/>">
                                </g:remoteLink>
                            </tooltip:tip>
                        </td>
                        <td rowspan="5" valign="bottom" width="10px" align="right">
                            <tooltip:tip code="tooltip.plan.incsv">

                                <a href="#" onclick="chengplan(${plan?.id})" class="chengpl" >
                                    <img src="<g:createLinkTo dir="/images" file="cvs.png"/>" alt="CSV" class="chengpl">
                                </a>
                            </tooltip:tip>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span style="font-size: small;">Кафедра:</span>
                        </td>
                        <td>
                            <span style="font-size: small; text-decoration: underline">${plan.chair.name}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span style="font-size: small;">Уровень:</span>
                        </td>
                        <td>
                            <span style="font-size: small; text-decoration: underline;">${plan.level}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span style="font-size: small;">Изменен:</span>
                        </td>
                        <td>
                            <span style="font-size: small; text-decoration: underline;">${plan.lastUpdated.toLocaleString()}</span>
                        </td>
                    </tr>
                </table>
                <div id="wPlans${plan.id}">

                </div>
            </div>
        </g:each>
    </div>



    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation" model="['askMessage': 'Вы точно хотите удалить этот план?']"/>
    </content>
    <content tag="chengplan">
        <g:render template="/CSVExport/exportCSVDialog" model="['askMessagecsv': 'Вы желаете экспортировать план?']"/>
    </content>
</div>