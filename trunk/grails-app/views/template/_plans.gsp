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

    function switchIcon(){
        var res = $("#details").attr("src").match("expand");
        var src;
        if (null != res){
            src = $("#details").attr("src").replace("expand", "collapse");
        } else {
            src = $("#details").attr("src").replace("collapse", "expand");
            $("#wPlans").empty();
        }
        $("#details").attr("src", src);
    }
</script>

<div>
    <div id="post_list">
        <div class="subtitle" align="center">${msg}</div>
        <g:if test="${totalPlans == 0}">
            <div class="post">
                <h1 class="title">Нет ни одного плана</h1>
            </div>
        </g:if>
        <g:each in="${res}" var="plan">
            <div class="post">
                <table style="font-size: 16px; width: 100%">
                    <tr>
                        <td colspan="6">
                            <h4 class="title">
                                <g:ifAllGranted role="ROLE_ADMIN">
                                    <g:link id="${plan.id}" action="index"
                                            controller="selectSpeciality">${plan.speciality.kod} ${plan.speciality.name}</g:link>
                                </g:ifAllGranted>
                                <g:ifAllGranted role="ROLE_USER">
                                    ${plan.speciality.kod} ${plan.speciality.name}
                                </g:ifAllGranted>
                            </h4>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">
                            <span style="font-size: small;">Форма обучения:</span>
                        </td>
                        <td width="20%">
                            <span style="font-size: small; text-decoration: underline;">${plan.form}</span>
                        </td>
                        <td width="40%"></td>
                        <td rowspan="5" valign="bottom" width="5%" align="right">
                            <g:ifAllGranted role="ROLE_ADMIN">
                                <tooltip:tip code="tooltip.plan.remove">
                                    <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                                        <input type="image"
                                               src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </g:ifAllGranted>
                        </td>
                        <td rowspan="5" valign="bottom" width="5%" align="right">
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
                        <td rowspan="5" valign="bottom" width="5%" align="right">
                            <tooltip:tip code="tooltip.plan.expand.work.plans">
                                <g:remoteLink action="showWorkPlans" id="${plan.id}" update="wPlans" onSuccess="switchIcon()">
                                    <input id="details" type="image" src="<g:createLinkTo dir="/images" file="expand.png"/>">
                                </g:remoteLink>
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
                <div id="wPlans">

                </div>
            </div>
        </g:each>
    </div>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation" model="['askMessage': 'Вы точно хотите удалить этот план?']"/>
    </content>
</div>