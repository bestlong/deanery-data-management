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
            <th><a onclick="selectUnselectAll()">Все</a></th>
            <th>Код</th>
            <th>Кафедра</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <th>Деканат</th>
            </sec:ifAnyGranted>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>
        </tbody>
     </table>
</div>
