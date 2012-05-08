<%@ page import="decanat.grails.Faculty; decanat.grails.Chair" %>

<div class="search" id ="searchSpeciality">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateSpecialityDiv"
                  url="[controller: searchConfig?.controller ?: 'speciality', action: searchConfig?.action ?: 'search']"
                  onSuccess="${searchConfig?.successFunction ?: 'initTable();'}">
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
            <b class="searchTitles">Код специальности</b><br/>
            <g:textField name="specialityCode"/><br/>
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