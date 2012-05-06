<%@ page import="stu.cn.ua.enums.PlanForm; decanat.grails.Speciality;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/planInit/init.js"/>
    <title>Выбор специальности</title>
    <g:javascript>
        function calculateEndYear() {
            var startYear = $('#startYear').val();
            if ($.isNumeric(startYear)) {
                var endYear = parseInt(startYear, 10) + parseInt(1, 10);
                $('#endYear').val(endYear);
            }
        }
        function calculateStartYear() {
            var endYear = $('#endYear').val();
            if ($.isNumeric(endYear)) {
                var startYear = parseInt(endYear, 10) - parseInt(1, 10);
                $('#startYear').val(startYear);
            }
        }
    </g:javascript>
</head>

<body>
<div>
    <content tag="search">
        <g:render template="/template/plan/menu" model="['active': 1, 'plan': plan]"/>
        <g:render template="/template/speciality/searchTemplate"
                  model="['controller': 'planInit', searchConfig: searchSpecialityConfig]"/>
        <g:render template="/template/chair/searchTemplate" model="[searchConfig: searchConfig]"/>
    </content>

    <div id="searchPart" style="display: none;">
        <h4 class="subtitle">для выбора специальности нажмите на её имя:</h4>
        <g:render template="/template/speciality/selectSpeciality" model="${[res: res]}"/>
        <div align="center" class="action">
            <a onclick="showMain()">Отмена</a>
        </div>
    </div>

    <div id="searchPartChair" style="display: none;">
        <h4 class="subtitle">для выбора кафедры нажмите на её имя:</h4>
        <g:render template="/template/chair/selectChair" model="${[res: chairs]}"/>
        <div align="center" class="action">
            <a onclick="showMain()">Отмена</a>
        </div>
    </div>

    <div id="selectPart">
        <h4 class="subtitle">Инициализация плана:</h4>
        <g:form action="next" controller="planInit" name="selectSpecialityForm">
            <table cellpadding="5" class="editTable" style="border-width: 1px">
                <g:if test="${!plan.isStudyPlan()}">
                    <tr>
                        <td class="caption">Года*</td>
                        <td>
                            <g:textField name="startYear" style="width: 40px" class="integer"
                                         onkeyup="calculateEndYear();" value="${plan?.startYear}"/>
                            ${" -  "}
                            <g:textField name="endYear" style="width: 40px" class="integer"
                                         onkeyup="calculateStartYear();" value="${plan?.endYear}"/>
                        </td>
                    </tr>
                </g:if>
                <tr>
                    <td class="caption">Специальность<span style="color: red">*</span></td>
                    <td>
                        <div>
                            <a id="selectSpec" href="#" onclick="showSpecialitySelect(true)"
                               class="action">Выбрать специальность</a>
                            <g:hiddenField name="specId" value="${plan?.speciality?.id}"/>
                            <g:hiddenField name="id" value="${plan?.id}"/>
                        </div>

                        <div style="margin-top: 5px">
                            <span style="font-size: 14px; color: #0000ff; text-decoration: underline"
                                  id="specialityName">${plan?.speciality?.name}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Кафедра<span style="color: red">*</span></td>
                    <td>
                        <div>
                            <a id="selectChair" href="#" onclick="showChairSelect(true)"
                               class="action">Выбрать кафедру</a>
                            <g:hiddenField name="chairId" value="${plan?.chair?.id}"/>
                        </div>

                        <div>
                            <span style="font-size: 14px; color: #0000ff; text-decoration: underline"
                                  id="chairName">${plan?.chair?.name}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Форма обучения<span style="color: red">*</span></td>
                    <td>
                        <select name="form" id="form" style="width: 150px">
                            <option value="0">Выберите форму</option>
                            <option value="1" ${plan?.form == PlanForm.DAILY.name ? 'selected' : ''}>Дневная</option>
                            <option value="2" ${plan?.form == PlanForm.EXTRAMURAL.name ? 'selected' : ''}>Заочная</option>
                            <option value="3" ${plan?.form == PlanForm.EVENING.name ? 'selected' : ''}>Вечерняя</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Направление<span style="color: red">*</span></td>
                    <td>
                        <g:textField name="direction" value="${plan?.direction}"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Уровень<span style="color: red">*</span></td>
                    <td>
                        <g:textField name="level" value="${plan?.level}"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Количество семестров<span style="color: red">*</span></td>
                    <td>
                        <g:select from="${[2, 3, 4, 8]}" name="semestrCount" value="${plan?.semestrCount}"
                                  style="width: 50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Срок обучения<span style="color: red">*</span></td>
                    <td>
                        <g:textField name="termin" value="${plan?.termin}"/><br/>
                        <span style="color: gray; font-style:oblique;">пример: 3 роки 10 місяців</span>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Квалификация<span style="color: red">*</span></td>
                    <td>
                        <g:textField name="qualification" value="${plan?.qualification}"/><br/>
                        <span style="color: gray; font-style:oblique;">пример: бакалавр комп'ютерної інженерії</span>
                    </td>
                </tr>
            </table>

            <div align="center" class="action">
                <g:link action="index" controller="index">Отмена</g:link>
                <g:submitButton name="save" value="Следующий шаг"/>
            </div>
        </g:form>
    </div>
</div>

</body>
</html>
