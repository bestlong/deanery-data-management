<%@ page import="stu.cn.ua.enums.PlanForm; decanat.grails.Speciality;" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:javascript src="/init/selectSpeciality/init.js"/>
    <title>Выбор специальности</title>
</head>

<body>
<div id="latest-post">
    <content tag="search">
        <g:render template="/template/plan/menu" model="[ 'active' : 1, 'plan': plan ]"/>
        <g:render template="/template/speciality/searchTemplate" model="['controller': 'selectSpeciality', searchConfig: searchSpecialityConfig]"/>
        <g:render template="/template/chair/searchTemplate" model="[searchConfig: searchConfig]"/>

    </content>

    <div id="searchPart" style="display: none;">
        <h4 class="subtitle">для выбора специальности нажмите на её имя:</h4>
        <g:render template="/template/speciality/selectSpeciality" model="${[res: res]}"/>
        <div align="center" class="action">
            <a href="" onclick="showMain()">Отмена</a>
        </div>
    </div>

    <div id="searchPartChair" style="display: none;">
        <h4 class="subtitle">для выбора кафедры нажмите на её имя:</h4>
        <g:render template="/template/chair/selectChair" model="${[res: chairs]}"/>
        <div align="center" class="action">
            <a href="" onclick="showMain()">Отмена</a>
        </div>
    </div>

    <div id="selectPart">
        <h4 class="subtitle">Инициализация плана:</h4>

        <g:form action="next" controller="selectSpeciality" name="selectSpecialityForm">
            <table cellpadding="5" class="editTable">
                <tr>
                    <td class="caption">Специальность*</td>
                    <td>
                        <span style="font-size: 14px;" id="specialityName">${plan?.speciality?.name}</span>
                        <a id="selectSpec" href="#" onclick="showSpecialitySelect(true)" class="action">Выбрать специальность</a>
                        <g:hiddenField name="specId" value="${plan?.speciality?.id}"/>
                        <g:hiddenField name="id" value="${plan?.id}"/>
                    </td>
                </tr>

                <tr>
                    <td class="caption">Кафедра*</td>
                    <td>
                        <span style="font-size: 14px;" id="chairName">${plan?.chair?.name}</span>
                        <a id="selectChair" href="#" onclick="showChairSelect(true)" class="action">Выбрать кафедру</a>
                        <g:hiddenField name="chairId" value="${plan?.chair?.id}"/>

                    </td>
                </tr>

                <tr>
                    <td class="caption">Форма обучения*</td>
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
                    <td class="caption">Направление*</td>
                    <td>
                        <g:textField name="direction" value="${plan?.direction}"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Уровень*</td>
                    <td>
                        <g:textField name="level" value="${plan?.level}"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Количество семестров*</td>
                    <td>
                        <g:select from="${1..11}" name="semestrCount" value="${plan?.semestrCount}"
                                  style="width: 50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Срок обучения*</td>
                    <td>
                        <g:textField name="termin" value="${plan?.termin}"/><br/>
                        <span style="color: gray; font-style:oblique;">пример: 3 роки 10 місяців</span>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Квалификация*</td>
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
