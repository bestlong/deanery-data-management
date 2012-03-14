<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.03.12
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>


<g:hiddenField name="planId" value="${plan.id}"/>
<table id="controlType" align="left">
    <tr>
        <td class="caption">Предмет*</td>
        <td>
            <span style="font-size: 14px;" id="subjName">${subject?.subject?.name}</span>
            <a id="selectSubj" href="#" onclick="showOrHide(true)" class="action">Выбрать предмет</a>
            <g:hiddenField name="subjId" value="${subject?.subject?.id ?: ''}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество кредитов*</td>
        <td>
            <g:textField name="creditCount" value="${subject?.creditCount}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество лекций*</td>
        <td>
            <g:textField name="lectureCount" value="${subject?.lectureCount}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество семинаров*</td>
        <td>
            <g:textField name="seminarCount" value="${subject?.seminarCount}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество практических занятий*</td>
        <td>
            <g:textField name="practiceCount" value="${subject?.practiceCount}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество лабораторных*</td>
        <td>
            <g:textField name="labCount" value="${subject?.labCount}"/>
        </td>
    </tr>
    <tr>
        <td class="caption">Количество самостоятельных работ*</td>
        <td>
            <g:textField name="samCount" value="${subject?.samCount}"/>
        </td>
    </tr>
</table>

<br/><br/><br/>   <br/><br/><br/>    <br/><br/><br/>   <br/><br/><br/>
<br/><br/><br/>   <br/><br/><br/>    <br/><br/><br/>   <br/><br/><br/>

