<%--
    author: evgeniy
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<sec:ifAllGranted roles="ROLE_ADMIN">
    <div align="left">
        <tooltip:tip code="tooltip.plan.add">
            <a href="<g:createLink action="index" controller="planCreation"/>">
                <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
            </a>
        </tooltip:tip>
    </div>
</sec:ifAllGranted>


<sec:ifAllGranted roles="ROLE_USER">
    <g:set var="align" value="center"/>
</sec:ifAllGranted>
<div align="${align}">
    <g:render template="/template/plans"/>
</div>

<div class="paginateButtons">
    <g:paginate total="${totalPlans}" max="4" maxsteps="3" prev="Предыдущая" next="Следующая"/>   <br/>
</div>

</body>
</html>