<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.07.11
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">
    function switchBox(id, val) {
        document.getElementById(id).setAttribute('class', val ? 'visible' : 'invisible');

    }
    function switchDiv(id) {
        for (i = 1; i <=${semestr}; i++) {
            document.getElementById('d' + i.toString()).setAttribute('class', 'nonDisplay');
        }
        document.getElementById(id).setAttribute('class', 'display');

    }

    $(document).ready(function () {
        for (i = 1; i <=${semestr}; i++) {
            switchBox('l' + i.toString(), document.getElementById('c' + i.toString()).checked);
        }
    });
</script>
<style>
.visible {
    visibility: visible;
}

.invisible {
    visibility: hidden;
}

.display {
    display: inline-table;
}

.nonDisplay {
    display: none;

}
</style>

<div class="subtitle">Разделение между семестрами:</div>
<table id="addSubjTable">
    <tr>
        <g:set var="idx" value="${1}"/>
        <g:while test="${idx <= semestr}">
            <td>
                <g:if test="${hours?.containsKey(idx) && hours?.get(idx)}">
                    <h3>${idx} <g:checkBox name="c${idx}" value="true" onclick="javascript: switchBox('l${idx}', this.checked );"/></h3>
                </g:if>
                <g:else>
                    <h3>${idx} <g:checkBox name="c${idx}" onclick="javascript: switchBox('l${idx}', this.checked );"/></h3>
                </g:else>

            </td>
            <g:set var="idx" value="${idx + 1}"/>
        </g:while>

    </tr>
</table>

<div id="tabs">
    <ul>
        <g:each in="${1..semestr}" var ="cp">
            <li id="l${cp}">
                <a href="#" onclick="javascript: switchDiv('d${cp}')">
                    <span>${cp} семестр</span>
                </a>
            </li>
        </g:each>
    </ul>
    <br/>

</div>



<g:set var="idx" value="${1}"/>
<g:while test="${idx <= semestr}">
    <div class="nonDisplay" id="d${idx}" align="center">
        <table id="controlType" align="center">
            <tr>
                <td colspan="2" align="center">
                    <div class="subtitle">${idx} семестр</div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <div class="subtitle">Количество за 2 недели:</div>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Лекций</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="lectureCount${idx}" value="${hours?.get(idx)?.lectureCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="lectureCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Семинаров</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="seminarCount${idx}" value="${hours?.get(idx)?.seminarCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="seminarCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Практик</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="practiceCount${idx}" value="${hours?.get(idx)?.practiceCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="practiceCount${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Лабораторных</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:textField name="labCount${idx}" value="${hours?.get(idx)?.labCount}"/>
                    </g:if>
                    <g:else>
                        <g:textField name="labCount${idx}"/>
                    </g:else>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <div class="subtitle">Вид контроля:</div>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Экзамен</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="exam${idx}"
                                    checked="${newControls?.get(idx)?.exam == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="exam${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Зачёт</td>
                <td align="center">
                    <g:if test="${semestr<=hours?.size()?:0-1}">
                        <g:checkBox name="zach${idx}"
                                    checked="${newControls?.get(idx)?.zach == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="zach${idx}"/>
                    </g:else>
                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Курсовая работа</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="cWork${idx}"
                                    checked="${newControls?.get(idx)?.cWork == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="cWork${idx}"/>
                    </g:else>

                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Курсовой проект</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="cProj${idx}"
                                    checked="${newControls?.get(idx)?.cProj == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="cProj${idx}"/>
                    </g:else>

                </td>
            </tr>
            <tr>
                <td class="caption" align="center">РГР</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="rgr${idx}" checked="${newControls?.get(idx)?.rgr == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="rgr${idx}"/>
                    </g:else>

                </td>
            </tr>
            <tr>
                <td class="caption" align="center">Контрольная работа</td>
                <td align="center">
                    <g:if test="${hours?.containsKey(idx)}">
                        <g:checkBox name="contrWork${idx}"
                                    checked="${newControls?.get(idx)?.contrWork == 1 ? 'true' : 'false'}"/>
                    </g:if>
                    <g:else>
                        <g:checkBox name="contrWork${idx}"/>
                    </g:else>

                </td>
            </tr>
        </table>

    </div>
    <g:set var="idx" value="${idx + 1}"/>
</g:while>

