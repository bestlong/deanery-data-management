<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 06.07.11
  Time: 0:32
--%>

<div class="search" id="searchChair">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateChairDiv"
                  url="[controller:searchConfig.controller, action:searchConfig.action]"
                  onSuccess="${searchConfig?.successFunction ?: 'initTable();'}">
        <div style="float: left; padding: 0 0 0 20px">
            <b class="searchTitles">Название</b><br/>
            <g:textField name="name"/><br/>
            <b class="searchTitles">Аббревиатура</b><br/>
            <g:textField name="shortName"/><br/><br/>

            <div align="center" class="action">
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div>
        </div>
    </g:formRemote>
</div>