<%--
  author: evgeniy
--%>

<%@ page import="decanat.grails.WorkPlan; stu.cn.ua.enums.PlanWayCreation; stu.cn.ua.enums.PlanType" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script type="text/javascript ">
        $(function () {
            $("input:submit, a, button", ".action").button();
            $('#planType').change(function () {
                var str = $("#planType option:selected").val();
                if (str == '${PlanType.WORK}') {
                    $("#forWorkPlan").show();
                } else {
                    $("#forWorkPlan").hide();
                }
            });
            $('#planWayCreation').change(function () {
                var str = $("#planWayCreation option:selected").val();
                if (str == '${PlanWayCreation.STANDARD_CONSTRUCTOR}') {
                    $("#forConstructorType").hide();
                } else {
                    $("#forConstructorType").show();
                }

            });
            var str = $("#planType option:selected").val();
            if (str == '${PlanType.WORK}') {
                $("#forWorkPlan").show();
            }
        });


    </script>
    <meta name="layout" content="main"/>
    <title>Создание плана</title>
</head>

<body>
<div class="subtitle" align="center">Меню создания плана</div>
<table class="editTable" align="center">
    <tr>
        <td class="caption">Тип плана*</td>
        <td>
            <g:select from="${PlanType.values()}" optionValue="caption" name="planType"
                      style="width:300px; padding: 3px; margin: 0" value="${PlanType.STUDY}"/>
        </td>
    </tr>
</table>

<div id="forWorkPlan" style="display: none">
    <div class="subtitle" align="center">Подменю для рабочих планов</div>
    <table class="editTable" align="center">
        <tr>
            <td class="caption">
                Способ создания плана
            </td>
            <td>
                <g:select from="${PlanWayCreation.values()}" optionValue="caption" name="planWayCreation"
                          style="width:300px; padding: 3px; margin: 0" value="${PlanWayCreation.STANDARD_CONSTRUCTOR}"/>
            </td>
        </tr>
    </table>

    <div id="forConstructorType" style="display: none">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">
                    Рабочие планы
                </td>
                <td>
                    <g:select from="${WorkPlan.list()}" optionValue="name" optionKey="id" name="baseWorkPlan"
                              style="width:300px; padding: 3px; margin: 0"/>
                </td>
            </tr>
        </table>
    </div>
</div>

<div align="center" class="action">
    <g:link controller="subject" action="index">Отмена</g:link>
    <g:submitButton name="next" value="Далее"/>
</div>
</body>
</html>