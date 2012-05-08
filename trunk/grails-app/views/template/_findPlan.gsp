<%@ page import="stu.cn.ua.enums.PlanForm" %>
<%@ page import="decanat.grails.Faculty; decanat.grails.Chair" %>

<div id="findDialog" title="Поиск учебных планов">

    <g:javascript>
        function calculateEndYear(){
            var startYear = $('#startYear').val();
            if ($.isNumeric(startYear)){
                var endYear = parseInt(startYear, 10) + parseInt(1, 10);
                $('#endYear').val(endYear);
            }
        }
        function calculateStartYear(){
            var endYear = $('#endYear').val();
            if ($.isNumeric(endYear)){
                var startYear = parseInt(endYear, 10) - parseInt(1, 10);
                $('#startYear').val(startYear);
            }
        }

    </g:javascript>

    <g:form controller="Index" action="findPlan" method="get">

    <table cellpadding="5" class="editTable" style="border-width: 1">
        <tr>
            <td class="rowLabel">Года</td>
            <td>
                <g:textField name="startYear" style="width: 40px" class="integer" onchange="calculateEndYear();" onkeyup="calculateEndYear();" value="${param?.startYear}"/>
                ${" -  "}
                <g:textField name="endYear" style="width: 40px" class="integer" onchange="calculateStartYear();" onkeyup="calculateStartYear();" value="${param?.endYear}"/>
            </td>
        </tr>
        <tr>
            <td  class="rowLabel">Специальность</td>
            <td>
                <g:textField name="speciality" type="text"  value="${param?.speciality?.name}" style="width: 200px"/>
            </td>
        </tr>
        <sec:ifAnyGranted roles="ROLE_PROREKTOR">
            <tr>
                <td>
                  Деканат
                     </td>
             <td>
                 <g:if test="${faculty?.id!=0}">
                     <g:select from=" " noSelection='["${faculty?.id}":"${faculty?.name}"]' id="faculty_disabled" disabled="true" name="faculty_disabled"
                               style="width: 150px;"/>
                 </g:if>

                 <g:if test="${faculty?.id==0}">
                     <g:select from="${Faculty.list()}" optionValue="name"   id="faculty"
                               noSelection='["${faculty?.id}":"${faculty?.name}"]' name="faculty"
                               optionKey="id" style="width: 150px"/>
                 </g:if>
             </td>
            </tr>
        </sec:ifAnyGranted>
        <tr>
            <td class="rowLabel">Кафедра</td>
            <td>
                <g:textField name="chair" type="text" value="${param?.chair?.name}" style="width: 200px"/>
            </td>
        </tr>
        <tr>
            <td class="rowLabel">Форма обучения</td>
            <td>
                <select name="form" id="form" style="width: 150px">
                <option id="form0" value="0">Выберите форму</option>
                <option id="form${PlanForm.DAILY.name}" value="${PlanForm.DAILY.name}" >Дневная</option>
                <option id="form${PlanForm.EXTRAMURAL.name}" value="${PlanForm.EXTRAMURAL.name}" >Заочная</option>
                <option id="form${PlanForm.EVENING.name}" value="${PlanForm.EVENING.name}" >Вечерняя</option>
            </select>

        </td>
    </tr>
        <tr>
            <td class="rowLabel">Направление</td>
            <td>
                <g:textField name="direction" value="${param?.direction}" style="width: 200px"/>
            </td>
        </tr>
        <tr>
            <td class="rowLabel">Уровень</td>
            <td>
                <g:textField name="level"  value="${param?.level}" style="width: 200px"/>
            </td>
        </tr>
        <tr>
            <td class="rowLabel">Количество семестров</td>
            <td>
                <select name="semestrCount" id="semestrCount" style="width: 50px" >
                    <option id="semestrCount0" value="0">-</option>
                    <option id="semestrCount2" value="2" >2</option>
                    <option id="semestrCount3" value="3" >3</option>
                    <option id="semestrCount4" value="4" >4</option>
                    <option id="semestrCount8" value="8" >8</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="rowLabel">Срок обучения</td>
            <td>
                <g:textField name="termin"  value="${param?.termin}"  style="width: 300px"/><br/>
                <span style="color: gray; font-style:oblique;">пример: 3 роки 10 місяців</span>
            </td>
        </tr>
        <tr>
            <td class="rowLabel">Квалификация</td>
            <td>
                <g:textField name="qualification"  value="${param?.qualification}"   style="width: 300px"/><br/>
                <span style="color: gray; font-style:oblique;">пример: бакалавр комп'ютерної інженерії</span>
            </td>
        </tr>
    </table>

         <div class="action" align="center">
            <input type="submit" value="Найти">
            <a href="#" style="margin: 10px" onclick='$("#findDialog").dialog("close")'>Отмена</a>
        </div>
  </g:form>

</div>


