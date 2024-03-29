<%--
  Created by IntelliJ IDEA.
  User: Mustang
  Date: 26.03.12
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>

<div class="search" id="searchFaculty">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateFacultyDiv"
                  url="[controller:searchConfig.controller, action:searchConfig.action]"
                  onSuccess="${searchConfig?.successFunction ?: 'initTable();'}">
        <div style="float: left; padding: 0 0 0 20px">
            <b class="searchTitles">Название</b><br/>
            <g:textField name="name"/><br/>
            <b class="searchTitles">Аббревиатура</b><br/>
            <g:textField name="shortName"/><br/><br/>
            <b class="searchTitles">Проректор</b><br/>
            <g:textField name="prorektor"/><br/><br/>
            <b class="searchTitles">Декан</b><br/>
            <g:textField name="dean"/><br/><br/>
            <div align="center" class="action">
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div>
        </div>
    </g:formRemote>
</div>