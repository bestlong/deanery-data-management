<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 06.07.11
  Time: 0:32
--%>
<%@ page import="decanat.grails.Deanery; decanat.grails.Chair" %>
<div class="search" id="searchChair">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateChairDiv"
                  url="[controller: searchConfig?.controller ?: 'chair', action: searchConfig?.action ?: 'search']"
                  onComplete='${searchConfig?.successFunction ?: 'initTable()'}'>
        <div style="float: left; padding: 0 0 0 20px">
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <b class="searchTitles">Деканат</b><br/>
                <g:select from="${Deanery.list()}" optionValue="name"
                          noSelection="['0': '-Все деканаты-']" name="deanery"
                          optionKey="id" style="width: 150px"/><br/>
            </sec:ifAnyGranted>
            <b class="searchTitles">Код</b><br/>
            <g:textField name="code"/><br/>
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