<%--
    author: evgeniy
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <style type="text/css">
        .paginateButtons {
            font-size: 16px;
            text-align: center;
            margin: 2px;
            padding: 2px;
        }
        .step {
            margin: 2px;
            padding: 2px;
        }
    </style>
</head>

<body>
<sec:ifAnyGranted roles="ROLE_DEAN">
    <div align="left">
        <tooltip:tip code="tooltip.plan.add">
            <a href="<g:createLink action="index" controller="planCreation"/>">
                <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
            </a>
        </tooltip:tip>
    </div>
</sec:ifAnyGranted>


<sec:ifAllGranted roles="ROLE_USER">
    <g:set var="align" value="center"/>
</sec:ifAllGranted>
<div align="${align}">
    <g:render template="/template/plans"/>
</div>

<div class="paginateButtons">
    <g:paginate total="${totalPlans}" max="${sizePerPage ?: 4}" maxsteps="3" action="index" controller="index" prev="Предыдущая" next="Следующая"/>   <br/>
</div>

</body>
</html>