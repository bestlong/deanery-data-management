<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<g:ifAllGranted role="ROLE_ADMIN">
    <div align="left">
        <tooltip:tip code="tooltip.plan.add">
            <a href="<g:createLink action="index" controller="selectSpeciality"/>">
                <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
            </a>
        </tooltip:tip>
    </div>
</g:ifAllGranted>


<g:ifAllGranted role="ROLE_USER">
    <g:set var="align" value="center"/>
</g:ifAllGranted>
<div align="${align}">
    <g:render template="/template/plans"/>
</div>

<div class="paginateButtons">
    <g:paginate total="${totalPlans}" max="4" maxsteps="3" prev="Предыдущая" next="Следующая"/>   <br/>
</div>

</body>
</html>