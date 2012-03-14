<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.07.11
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>

<g:javascript>
    var semesterArray = new Array(${semesterCount});

        $(function () {
            showNeededSemesters();
        });

    function showNeededSemesters(){
        refreshSemesterArray();
        if (isAllUnchecked()){
            $('#semesterInfo').hide();
        }
        else {
            $('#semesterInfo').show();
            for (var i=1; i<=${semesterCount}; i++){
                if (semesterArray[i]){
                    $('div[name=controlType'+i+']').show();
                }
                else {
                    $('div[name=controlType'+i+']').hide();
                }
            }
        }
    }

    function isAllUnchecked(){
        for (var i=1; i<=${semesterCount}; i++){
            if (semesterArray[i]){
                return false;
            }
        }
        return true;
    }

    function refreshSemesterArray(){
        for (var i=1; i<=${semesterCount}; i++){
            semesterArray[i] = $('#semester'+i).attr('checked')=='checked'
        }
    }
</g:javascript>

<table width="40%" align="center">
    <tr>
        <td colspan="${semesterCount}" align="center">
            Номер семестра
        </td>
    </tr>
    <tr>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                ${index}
            </td>
        </g:each>
    </tr>
    <tr>

        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <g:if test="${hours?.containsKey(index) && hours?.get(index)}">
                    <g:checkBox name="semester${index}" value="${true}" onclick="showNeededSemesters()"/>
                </g:if>
                <g:else>
                    <g:checkBox name="semester${index}" onclick="showNeededSemesters()"/>
                </g:else>
            </td>
        </g:each>
    </tr>
</table>

<table id="semesterInfo" align="left" width="90%">
    <tr>
        <td colspan="${semesterCount}" align="left">
            <div class="h7" style="color: #5890D1">Вид контроля:</div>
            <hr/>
        </td>
    </tr>
    <tr>
        <td></td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    ${index}
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Экзамен</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="exam${index}"
                                value="${newControls?.get(index)?.exam == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Зачёт</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="zach${index}"
                                value="${newControls?.get(index)?.zach == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Курсовая работа</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="cWork${index}"
                                checked="${newControls?.get(index)?.cWork == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Курсовой проект</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="cProj${index}"
                                checked="${newControls?.get(index)?.cProj == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">РГР</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="rgr${index}" checked="${newControls?.get(index)?.rgr == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Контрольная работа</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:checkBox name="contrWork${index}"
                                checked="${newControls?.get(index)?.contrWork == 1 ? true : false}"/>
                </div>
            </td>
        </g:each>
    </tr>
    <tr><td><div><br/></div></td></tr>
    <tr>
        <td colspan="${semesterCount}" align="left">
            <div style="color: #5890D1" class="h7">Количество за 2 недели:</div>
            <hr/>
        </td>
    </tr>
    <tr>
        <td></td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    ${index}
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Лекций</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:if test="${hours?.containsKey(index)}">
                        <g:textField name="lectureCount${index}" value="${hours?.get(index)?.lectureCount}" style="width: 20px"/>
                    </g:if>
                    <g:else>
                        <g:textField name="lectureCount${index}" style="width: 20px"/>
                    </g:else>

                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Семинаров</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:if test="${hours?.containsKey(index)}">
                        <g:textField name="seminarCount${index}" style="width: 20px" value="${hours?.get(index)?.seminarCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="seminarCount${index}" style="width: 20px"/>
                    </g:else>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Практик</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:if test="${hours?.containsKey(index)}">
                        <g:textField name="practiceCount${index}" style="width: 20px" value="${hours?.get(index)?.practiceCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="practiceCount${index}" style="width: 20px"/>
                    </g:else>
                </div>
            </td>
        </g:each>
    </tr>
    <tr>
        <td class="planSubjectCaption" align="left" style="font-size: 16px">Лабораторных работ</td>
        <g:each in="${1..semesterCount}" var="index">
            <td align="center">
                <div name="controlType${index}">
                    <g:if test="${hours?.containsKey(index)}">
                        <g:textField name="labCount${index}" style="width: 20px" value="${hours?.get(index)?.labCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="labCount${index}" style="width: 20px"/>
                    </g:else>
                </div>
            </td>
        </g:each>
    </tr>
</table>

<br/><br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/><br/>

<g:set var="idx" value="${1}"/>
<g:while test="${idx <= semestr}">
    <div id="semestrNumber-${idx}" align="center">
        <table id="controlType" align="center">
            <tr>
                <td colspan="2" align="left">
                    <div class="h7">Вид контроля:</div>
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
                    <g:if test="${semestr <= hours?.size() ?: 0 - 1}">
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

