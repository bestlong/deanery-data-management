<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.07.11
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>

<div id="updateSpecialityDiv">
    <g:render template="/template/multipleDeleteItem"/>
    <div class="subtitle" align="center">Список специальностей</div>
    <table id="specialityList" class="display">
        <thead>
        <tr>
            <th><a onclick="selectUnselectAll()">Выделить все</a></th>
            <th>Код</th>
            <th>Код специальности</th>
            <th>Имя</th>
            <th>Короткое имя</th>
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <th>Факультет</th>
            </sec:ifAnyGranted>
            <th>Ред.</th>
        </tr>
        </thead>

        <tbody>

        </tbody>
    </table>

</div>