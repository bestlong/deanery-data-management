<%--
  author: evgeniy
--%>

<%@ page import="stu.cn.ua.enums.PlanClass; decanat.grails.Plan; decanat.grails.WorkPlan; stu.cn.ua.enums.PlanWayCreation; stu.cn.ua.enums.PlanClass" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script type="text/javascript ">
        $(function () {
            $("input:submit, a, button", ".action").button();
            $('#planType').change(function () {
                var str = $("#planType option:selected").val();
                if (str == '${PlanClass.WORK}') {
                    $("#forWorkPlan").show();
                } else {
                    $("#forWorkPlan").hide();
                }
            });
            $('#planWayCreation').change(function () {
                var str = $("#planWayCreation option:selected").val();
                if (str == '${PlanWayCreation.FROM_WORK_PLAN}') {
                    $("#workPlanTr").show();
                    $("#studyPlanTr").hide();
                } else {
                    $("#workPlanTr").hide();
                    $("#studyPlanTr").show();
                }
                $("#forConstructorType").show();

            });
            var str = $("#planType option:selected").val();
            if (str == '${PlanClass.WORK}') {
                $("#forWorkPlan").show();
            }
            str = $("#planWayCreation option:selected").val();
            if (str == '${PlanWayCreation.FROM_WORK_PLAN}') {
                $("#workPlanTr").show();
                $("#studyPlanTr").hide();
            } else {
                $("#workPlanTr").hide();
                $("#studyPlanTr").show();
            }
            $("#forConstructorType").show();


            var myForm = $('#planCreationForm');
            myForm.validate({
                onkeyup:false,
                errorClass:'error_field',
                validClass:'valid',
                onsubmit:true,
                success:function (label) {
                    $('[id=' + label.attr('for') + ']').qtip('destroy');
                },
                errorPlacement:function (error, element) {
                    var str = $("#planType option:selected").val();
                    if (str == '${PlanClass.STUDY}') {
                        myForm.send()
                    }
                    else {
                        if ($(error).text()) {
                            $(element).filter(':not(.valid)').qtip({
                                overwrite:true,
                                content:error,
                                position:{ my:'left center', at:'right center' },
                                show:{
                                    event:false,
                                    ready:true
                                },
                                hide:false,
                                style:{
                                    widget:false,
                                    classes:'ui-tooltip-blue ui-tooltip-jtools ui-tooltip-rounded',
                                    tip:true
                                }
                            });
                        }
                    }
                },
                rules:{
                    planName:{
                        required:true
                    }
                },
                messages:{
                    planName:{
                        required:'Введите название для рабочего плана'
                    }
                }
            });
        });

    </script>
    <meta name="layout" content="main"/>
    <title>Создание плана</title>
</head>

<body>
<g:form controller="planCreation" action="next" name="planCreationForm">
    <div class="subtitle" align="center">Меню создания плана</div>
    <table class="editTable" align="center">
        <tr>
            <td class="caption" width="100px">Тип плана*</td>
            <td align="left">
                <g:select from="${PlanClass.values()}" optionValue="caption" name="planType"
                          style="width:450px; padding: 3px; margin: 0" value="${PlanClass.STUDY}"/>
            </td>
        </tr>
    </table>

    <div id="forWorkPlan" style="display: none">
        <div class="subtitle" align="center">Подменю для рабочих планов</div>
        <table class="editTable" align="center">
            <tr>
                <td class="caption" width="100px">
                    Название плана
                </td>
                <td>
                    <g:textField name="planName" style="width:440px;"/>
                </td>
            </tr>
            <tr>
                <td class="caption">
                    Способ создания плана
                </td>
                <td>
                    <g:select from="${PlanWayCreation.values()}" optionValue="caption" name="planWayCreation"
                              style="width:450px; padding: 3px; margin: 0"
                              value="${PlanWayCreation.STANDARD_CONSTRUCTOR}"/>
                </td>
            </tr>
        </table>

        <div id="forConstructorType" style="display: none">
            <table class="editTable" align="center">
                <tr id="workPlanTr">
                    <td class="caption" width="100px">
                        Рабочие планы
                    </td>
                    <td>
                        <g:select from="${workPlans}" optionValue="name" optionKey="id" name="baseWorkPlan"
                                  style="width:450px; padding: 3px; margin: 0"/>
                    </td>
                </tr>
                <tr id="studyPlanTr">
                    <td class="caption" width="100px">
                        Учебные планы
                    </td>
                    <td align="left">
                        <g:select from="${plans}" optionValue="speciality" optionKey="id" name="baseStudyPlan"
                                  style="width:450px; padding: 3px; margin: 0"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div align="center" class="action">
        <g:link controller="index" action="index">Отмена</g:link>
        <g:submitButton name="next" value="Далее"/>
    </div>
</g:form>
</body>
</html>