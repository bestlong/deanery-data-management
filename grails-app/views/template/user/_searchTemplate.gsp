<%@ page import="decanat.grails.domain.Role" %>
<%@ page import="decanat.grails.UserController" %>
<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 06.07.11
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>

<div class="search">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateUserDiv"
                  url="[controller:'user', action:'search']"
                  onSuccess="initTable();">
        <div style="float: left; padding: 0 0 0 20px">
            <b class="searchTitles">ФИО</b><br/>
            <g:textField name="realName"/><br/>
            <b class="searchTitles">Логин</b><br/>
            <g:textField name="login"/><br/>
            <b class="searchTitles">Email</b><br/>
            <g:textField name="email"/><br/>
            <b class="searchTitles">Роль</b><br/>
            <g:select from="${decanat.grails.domain.Role.list()}" optionValue="description"
                      noSelection="['0':'-Все роли-']" name="role"
                      optionKey="id" style="width: 150px"/><br/>

            <br/>
            <div align="center" class="action">
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div>
        </div>
    </g:formRemote>

</div>