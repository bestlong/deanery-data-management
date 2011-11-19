<g:while test="${status != hour.semestr}">
    gggggg ${status}
    <g:render template="/template/print/emptyHour" model="['status':status]"/>
    <g:set var="status" value="${status+1}"/>
</g:while>
<g:if test="${status == hour.semestr}">
    kkkkk
    <g:render template="/template/print/fullHour" model="['hour':hour, 'status':status]"/>
</g:if>