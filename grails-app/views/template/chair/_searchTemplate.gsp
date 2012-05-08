<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 06.07.11
  Time: 0:32
--%>
<%@ page import="decanat.grails.Faculty; decanat.grails.Chair" %>
<div class="search" id="searchChair">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateChairDiv"
                  url="[controller: searchConfig?.controller ?: 'chair', action: searchConfig?.action ?: 'search']"
                  onComplete='${searchConfig?.successFunction ?: 'initTable()'}'>
        <div style="float: left; padding: 0 0 0 20px">
            <sec:ifAnyGranted roles="ROLE_PROREKTOR">
                <b class="searchTitles">Деканат</b><br/>
                <g:if test="${faculty?.id!=0}">
                    <g:select from=" " disabled="true"
                              noSelection='["${faculty?.id}": "${faculty?.name}"]' name="faculty_disabled"
                              style="width: 150px"/><br/>
                </g:if>
                <g:if test="${faculty?.id==0}">
                    <g:select from="${Faculty.list()}" optionValue="name"
                              noSelection="['0': '-Все деканаты-']" name="faculty"
                              optionKey="id" style="width: 150px"/><br/>
                </g:if>
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