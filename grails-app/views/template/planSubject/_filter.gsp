<div>
    <g:formRemote name="fromSearch" update="updateChairDiv"
                  url="[controller: 'addSubjects', action: 'index']">
        <table width="90%" border="1"
               style="margin-left: 20px; margin-right: 20px; margin-top: 20px; border: 1px dotted #5E99BD; ">
            <tr>
                <td colspan="8" align="center">
                    <h4>Семестр №</h4>
                </td>
            </tr>
            <tr align="center">
                <g:each in="${1..plan.semestrCount}" var="index">
                    <td>
                        ${index}
                    </td>
                </g:each>
            </tr>
            <tr align="center">
                <g:each in="${1..plan.semestrCount}" var="index">
                    <td>
                        <g:checkBox name="filterSemestr${index}"/>
                    </td>
                </g:each>
            </tr>
            <tr align="center">
                <td colspan="8" class="action">
                    <g:submitButton name="submit" value="Применить фильтр"/>
                </td>
            </tr>
        </table>
    </g:formRemote>
</div>