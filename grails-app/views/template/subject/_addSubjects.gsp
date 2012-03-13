<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.07.11
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>

<g:set var="idx" value="${1}"/>
<g:while test="${idx <= semestr}">
    <div  id="semestrNumber-${idx}" align="center">
        <table id="controlType" align="center">
            <tr>
                <td colspan="2" align="left">
                <div class="h7" >Вид контроля:</div>
                </td>
            </tr>
            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">Экзамен</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">

                        <g:checkBox name="exam${idx}"
                                    checked="${newControls?.get(idx)?.exam == 1 ? 'true' : 'false'}"/>

                    </g:if>
                    <g:else>
                        <g:checkBox name="exam${idx}" align="left"/>
                    </g:else>
                </td>
            </tr>

            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">Зачёт</td>
                <td align="left">
                    <g:if test="${semestr<=hours?.size()?:0-1}">
                        <g:checkBox name="zach${idx}"
                                    checked="${newControls?.get(idx)?.zach == 1 ? 'true' : 'false'}"/>
           </g:if>

            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">Курсовая работа</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="cWork${idx}"
                                    checked="${newControls?.get(idx)?.cWork == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="cWork${idx}"/>
                    </g:else>

                </td>
            </tr>
            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">Курсовой проект</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="cProj${idx}"
                                    checked="${newControls?.get(idx)?.cProj == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="cProj${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">РГР</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="rgr${idx}" checked="${newControls?.get(idx)?.rgr == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="rgr${idx}"/>
                    </g:else>

                </td>
            </tr>
            <tr class="planSubjectRowHeight">
                <td class="planSubjectCaption" align="left">Контрольная работа</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="contrWork${idx}"
                                    checked="${newControls?.get(idx)?.contrWork == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="contrWork${idx}"/>
                    </g:else>

                </td>
            </tr>

            <tr>
                <td align="left">
                    <div class="h7">Количество за 2 недели:</div>
                </td>
            </tr>
            <tr>
                <td class="planSubjectCaption" align="left">Лекций</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="lectureCount${idx}" value="${hours?.get(idx)?.lectures}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="lectureCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="planSubjectCaption" align="left">Семинаров</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="seminarCount${idx}" value="${hours?.get(idx)?.seminars}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="seminarCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="planSubjectCaption" align="left">Практик</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="practiceCount${idx}" value="${hours?.get(idx)?.practices}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="practiceCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="planSubjectCaption" align="left">Лабораторных</td>
                <td align="left">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="labCount${idx}" value="${hours?.get(idx)?.labs}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="labCount${idx}"/>
                    </g:else>
                </td>
            </tr>

        </table>

    </div>
    <g:set var="idx" value="${idx + 1}"/>
</g:while>

