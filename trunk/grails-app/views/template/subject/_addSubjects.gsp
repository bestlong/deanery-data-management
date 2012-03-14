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
