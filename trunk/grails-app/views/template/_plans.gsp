<script type="text/javascript ">

    $(function() {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide()
    });

    function deleteDialog(iid) {
        $("#delBtn").attr("href", '${request.contextPath + '/index/delete/'}' + iid);
        $("#dialog").dialog();
    }
</script>

<div id="latest-post">

    <div id="post_list">
        <div class="subtitle" align="center">${msg}</div>
        <g:if test="${totalPlans==0}">
            <div class="post">
                <h1 class="title">Нет ни одного плана</h1>
            </div>
        </g:if>
        <table>
            <g:each in="${res}" var="plan">
                <tr>
                    <td>
                        <div class="post">
                            <h1 class="title">
                                <g:ifAllGranted role="ROLE_ADMIN">
                                <g:link id="${plan.id}" action="index"
                                        controller="selectSpeciality">${plan.speciality.kod} ${plan.speciality.name}</g:link>
                                </g:ifAllGranted>
                                <g:ifAllGranted role="ROLE_USER">
                                    ${plan.speciality.kod} ${plan.speciality.name}
                                </g:ifAllGranted>
                            </h1>

                            <p class="meta">
                                <small>Форма обучения:
                                    <span>${plan.form}</span>
                                    <br/>
                                    Кафедра:
                                    <span>${plan.chair.name}</span>
                                    <br/>
                                    Уровень:
                                    <span>${plan.level}</span>

                                    <br/>
                                    Изменен:
                                    <span>${plan.lastUpdated.toLocaleString()}</span>

                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <g:ifAllGranted role="ROLE_ADMIN">
                                        <tooltip:tip code="tooltip.plan.remove">
                                            <a href="#" class="delPlan" onclick="deleteDialog(${plan?.id})">
                                                <input type="image"
                                                       src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                            </a>
                                        </tooltip:tip>
                                    </g:ifAllGranted>
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
                                </small>
                            </p>
                        </div>
                    </td>
                </tr>
            </g:each>
        </table>

    </div>

    <content tag="deleteConfirmation">
        <g:render template="/template/deleteConfirmation" model="['askMessage':'Вы точно хотите удалить этот план?']"/>
    </content>
</div>