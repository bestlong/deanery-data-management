<%--
  Created by IntelliJ IDEA.
  User: drStout
  Date: 11.07.11
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>


<div id="updateChairDiv">
    <g:render template="/template/multipleDeleteItem"/>
    <div class="subtitle" align="center">Список кафедр</div>
    <table id="tableCont" class="display">
        <thead>
        <tr>
            <th><a onclick="selectUnselectAll()">Все</a></th>
            <th>Код</th>
            <th>Название</th>
            <th>Аббревиатура</th>
            <th>Заведующий</th>
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <th>Факультет</th>
            </sec:ifAnyGranted>
            <th>Планы</th>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>

        </tbody>
    </table>
</div>

