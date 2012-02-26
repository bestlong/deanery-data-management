<%@ page import="decanat.grails.PlanHours; decanat.grails.Plan" %>
<table style="margin-left: 5%; margin-right: 5%" width="90%;">
    <tr>
        <td width="17%">
            ${subject.subject.name}
        </td>
        <td width="12%" align="left">
            ${subject.subject.chair.name}
        </td>
        <td width="2%" align="right">
            ${subject.creditCount}
        </td>
        <td width="10%" align="right">
            <table width="90%" style="margin-right: 5px;">
                <tr align="left">
                    <td width="27%">
                        ${subject.lectureCount + subject.seminarCount + subject.practiceCount + subject.labCount + subject.samCount}
                    </td>
                    <td width="15%">
                        ${subject.lectureCount == 0 ? '_' : subject.lectureCount}
                    </td>
                    <td width="15%">
                        ${subject.seminarCount == 0 ? '_' : subject.seminarCount}
                    </td>
                    <td width="14%">
                        ${subject.practiceCount == 0 ? '_' : subject.practiceCount}
                    </td>
                    <td width="14%">
                        ${subject.labCount == 0 ? '_' : subject.labCount}
                    </td>
                    <td width="14%">
                        ${subject.samCount == 0 ? '_' : subject.samCount}
                    </td>
                </tr>

            </table>
        </td>
        <td width="11%" align="right">
            <table width="90%" style="margin-right: 5px;">
                <tr align="left">
                    <td width="17%">
                        ${subject.exam == "" ? "__" : subject.exam}
                    </td>
                    <td width="17%">
                        ${subject.zach == "" ? "__" : subject.zach}
                    </td>
                    <td width="17%">
                        ${subject.cProj == "" ? "__" : subject.cProj}
                    </td>
                    <td width="16%">
                        ${subject.cWork == "" ? "__" : subject.cWork}
                    </td>
                    <td width="16%">
                        ${subject.rgr == "" ? "__" : subject.rgr}
                    </td>
                    <td width="16%">
                        ${subject.contrWork == "" ? "__" : subject.contrWork}
                    </td>
                </tr>
            </table>
        </td>
        <g:set var="iterator" value="${subject.hours.iterator()}"/>
        <g:each in="${1..plan.semestrCount}" var="num">
            %{--<g:set var="aa" value="${g.hourBySubjAndSem("11", "ff")}"/>--}%
            <g:if test="${g.hourBySubjAndSem(subject.id, num) as int == 0}">
                <g:render template="/template/print/emptyHour"/>
            </g:if>
            <g:else>
                <g:render template="/template/print/fullHour" model="['hour':iterator.next()]"/>
            </g:else>
        </g:each>
    </tr>
</table>