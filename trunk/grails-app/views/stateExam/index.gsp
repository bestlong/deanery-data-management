<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main"/>
   <jqvalui:renderValidationScript for="decanat.grails.PlanStateExam"/>
   <title> Гос. экзамен</title>
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
      <g:render template="/template/plan/menu" model="[ 'active' : 4 ]"/>
    </content>
    <h4 class="subtitle">Изменить информацию о гос. экзамене:</h4>
    <g:form controller="stateExam" action="save" width="300" >
        <table class="editTable" align="center">
               <tr>
                <td class="caption">Номер семестра*</td>
                <td>
                     <g:hiddenField name="id" value="${stateExam.id}"/>
                    <g:select name="semestr" from="${1..8}" value="${1}" style="width: 100px">  </g:select>
                </td>
            </tr>
            <tr>
                <td class="caption">Дата проведения*</td>
                <td>
                    <g:hiddenField name="planId" value="${plan.id}"/>

                   <g:textField name="date" value="${stateExam.date}" />
                </td>
            </tr>
            <tr>
                <td class="caption">Форма аттестации*</td>
                <td>
                    <g:textField name="forma" value="${stateExam.forma}" />
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="practice" action="index" id="${plan.id}">Отмена</g:link>
            <g:submitButton name="save" value="Следующий шаг"/>
        </div>
    </g:form>
</div>

</body>
</html>

