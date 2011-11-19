<%@ page import="decanat.grails.Chair" %>
<div class="search" id ="searchSubject">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updatePlanSubjectDiv"
                  url="[controller:controller, action:'search']"
                  onSuccess="initTable();">
        <div style="float: left; padding: 0 0 0 20px">
            <b class="searchTitles">Кафедра</b><br/>
            <g:select from="${Chair.list()}" optionValue="name"
                      noSelection="['0':'-Все кафедры-']" name="chair"
                      optionKey="id" style="width: 150px"/><br/>
            <b class="searchTitles">Имя</b><br/>
            <g:textField name="name"/><br/>
            <b class="searchTitles">Короткое имя</b><br/>
            <g:textField name="shortName"/><br/>
            <br/>
            <div align="center" class="action">
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div>
        </div>
    </g:formRemote>

</div>