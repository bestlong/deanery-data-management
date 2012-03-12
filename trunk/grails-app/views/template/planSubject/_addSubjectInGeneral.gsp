
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.03.12
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>

<g:form controller="addSubjects" action="save" width="300" name="formSave">
    <g:hiddenField name="planId" value="${plan.id}"/>
    <table id="controlType" align="left">
        <tr>
            <td class="caption">Предмет*</td>
            <td>
                <span style="font-size: 14px;" id="subjName"></span>
                <a id="selectSubj" href="#" onclick="showOrHide(true)" class="action">Выбрать предмет</a>
                <g:hiddenField name="subjId"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество кредитов*</td>
            <td>
                <g:textField name="creditCount"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество лекций*</td>
            <td>
                <g:textField name="lectureCount"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество семинаров*</td>
            <td>
                <g:textField name="seminarCount"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество практических занятий*</td>
            <td>
                <g:textField name="practiceCount"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество лабораторных*</td>
            <td>
                <g:textField name="labCount"/>
            </td>
        </tr>

        <tr>
            <td class="caption">Количество самостоятельных работ*</td>
            <td>
                <g:textField name="samCount"/>
            </td>
        </tr>

    </table>

    <br/><br/><br/>   <br/><br/><br/>    <br/><br/><br/>   <br/><br/><br/>
    <br/><br/><br/>   <br/><br/><br/>    <br/><br/><br/>   <br/><br/><br/>
    <br/><br/><br/>   <br/><br/><br/>    <br/><br/><br/>

    <div align="center" class="action">
        <a href="<g:createLink controller="addSubjects" action="index" id="${plan.id}"/>">Отмена</a>
        <g:submitButton name="save" value="Сохранить"/>
    </div>
</g:form>