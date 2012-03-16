<%--
  @author evgeniy
  @date 10.07.11
--%>

<div id="updatePlanSubjectDiv">

    <g:render template="/template/multipleDeleteItem"/>
    <div class="subtitle" align="center">Список предметов</div>
    <table id="subjects" class="display">
        <thead>
        <tr>
            <th><a onclick="selectUnselectAll()">Выделить все</a></th>
            <th>Код</th>
            <th>Кафедра</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="subject">
            <tr id="tr${subject.id}" name="subjectTr">
                <td align="center">
                    <g:if test="${subject.referenceCount == 0}">
                        <tooltip:tip code="tooltip.del">
                            <g:hiddenField name="id" value="${subject.id}"/>
                            <g:checkBox name="multipleDelete${subject.id}" onclick="changeBackground(${subject.id})"/>
                        </tooltip:tip>
                    </g:if>
                </td>
                <td>
                    ${subject.code}
                </td>
                <td>
                    ${subject.chair?.name}
                </td>
                <td>
                    ${subject.name}
                </td>
                <td>
                    ${subject.shortName}
                </td>
                <td>
                    <table>
                        <tr>
                            <td align="left" style="margin: 5px">
                                <tooltip:tip code="tooltip.edit">
                                    <a href="<g:createLink action="edit" controller="subject" id="${subject.id}"/>">
                                        <input type="image"
                                               src="<g:createLinkTo dir="/images/ctrl" file="edit.jpg"/>"/>
                                    </a>
                                </tooltip:tip>
                            </td>
                            <td align="right" style="margin: 5px">
                                <g:if test="${subject.referenceCount == 0}">
                                    <tooltip:tip code="tooltip.del">
                                        <a href="#" onclick="deleteDialog(${subject?.id})" class="delBtn">
                                            <input type="image"
                                                   src="<g:createLinkTo dir="/images/ctrl" file="del.jpg"/>"/>
                                        </a>
                                    </tooltip:tip>
                                </g:if>
                                <g:else>
                                    <tooltip:tip code="tooltip.unable.remove">
                                        <input type="image"
                                               src="<g:createLinkTo dir="/images/ctrl"
                                                                    file="delete_disabled.gif"/>"/>
                                    </tooltip:tip>
                                </g:else>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
