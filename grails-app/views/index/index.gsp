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
    .rowLabel {font-size: 12;}
    </style>
    <script type="text/javascript">
        $(function () {
            $("#findDialog").hide()
            $("#dialog").hide()
        });

        function deleteDialog(iid) {
            $("#delBtn").attr("href", '${request.contextPath + '/index/delete/'}' + iid);
            $("#dialog").dialog();
        }

        function findDialog() {
            var options = {
                width: 500,

                show: 'scale'
            };
         var f=document.getElementById("form${param?.form}");
            f.selected="selected";
            var s=document.getElementById("semestrCount${param?.semestrCount}");
            s.selected="selected";

            $("#findDialog").dialog(options);
        }

    </script>

</head>

<body>






<sec:ifAnyGranted roles="ROLE_DEAN">

    <div style="float: left;">
    <tooltip:tip code="tooltip.plan.add">
        <a href="<g:createLink action="index" controller="planCreation"/>">
            <img src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
        </a>
    </tooltip:tip>
   </div>

</sec:ifAnyGranted>

<div  align="right" >
<tooltip:tip code="tooltip.plan.find">
    <a href="#" onclick="findDialog()">
        <img src="<g:createLinkTo dir="/images/ctrl" file="search.gif"/>">
    </a>
</tooltip:tip>
</div>


<sec:ifAllGranted roles="ROLE_USER">
<g:set var="align" value="center"/>
</sec:ifAllGranted>
<div align="${align}" class="post"  style="margin-top: 0px; margin-bottom: 10px">
    <table  style="font-size: 16px; width: 100%; ">
        <g:render template="/template/plans"/>
    </table>
</div>

<div class="paginateButtons">
    <g:paginate total="${totalPlans}" max="${sizePerPage ?: 10}" maxsteps="3" action="index" controller="index" prev="Предыдущая" next="Следующая"/>   <br/>
</div>


<content tag="findPlan">
    <g:render template="/template/findPlan" />
</content>

</body>
</html>
