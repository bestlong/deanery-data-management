<%@ page import="decanat.grails.Chair" %>
<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 06.07.11
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>

<div id="searchForm">
    <h2>Поиск по критерию:</h2>
    <g:formRemote name="formSearch" update="contentTable"
                  url="[controller:'subject', action:'search']"
                  onComplete="initTable();">
        <div style="float: left;">
            <b>Кафедра</b><br/>
            <b><g:select from="${Chair.list()}" optionValue="name"
                      noSelection="['0':'-Все кафедры-']" name="chair"
                      optionKey="id" style="width: 150px"/><br/> </b>
            <b>Имя</b><br/>
            <b><g:textField name="name"/></b><br/>
            <b>Короткое имя</b><br/>
            <b><g:textField name="shortName"/></b><br/>
            <b><div align="center">
                <br/>
                <input type="button" value="Закрыть"/>
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div></b>
        </div>
        <img src="../images/lypa.gif">
        <br/><br/><br/>
    </g:formRemote>
</div>
