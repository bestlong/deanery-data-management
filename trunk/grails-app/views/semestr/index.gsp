<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Semestr"/>
    <title> Продолжительность семестров</title>
</head>
<body>
<script type="text/javascript ">

    $(function() {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();

    });

</script>

<div>
    <content tag="search">
        <g:render template="/template/plan/menu" model="[ 'active' : 5 ]"/>
    </content>
    <h4 class="subtitle">Продолжительность семестров:</h4>
    <g:form controller="semestr" action="update" width="300">
        <table class="editTable" align="center">
            <g:hiddenField name="planId" value="${plan.id}"/>
            <tr>
                <td class="caption">Номер семестра</td>
                <td class="caption">Количество недель</td>
            </tr>
            <g:set var="idx" value="${1}"/>
            <g:while test="${idx <= plan.semestrCount}">
                <tr>
                    <td class="caption">${idx} семестр*</td>
                    <td>
                        <g:if test="${idx<=semestr.size()}">
                            <g:hiddenField name="id${idx}" value="${semestr.get(idx-1).id}"/>
                            <g:select name="sem${idx}" from="${1..20}" value="${semestr?.get(idx-1)?.weekCount}" style="width: 100px">недель</g:select>
                        </g:if>
                        <g:else>
                            <g:hiddenField name="id${idx}"/>
                            <g:select name="sem${idx}" from="${1..20}" value="${1}" style="width: 100px"/>
                        </g:else>
                    </td>
                </tr>
                <g:set var="idx" value="${idx + 1}"/>
            </g:while>

        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="stateExam" action="index" id="${plan.id}">Отмена</g:link>
            <g:submitButton name="save" value="Завершить"/>
        </div>
    </g:form>
</div>

</body>
</html>

