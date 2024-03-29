<!DOCTYPE html>
<html>
<head>
    <title>
        <g:layoutTitle default="Информационно-справочная система Деканат - Главная"/>
    </title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'default.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'notification.css')}"/>

    <g:javascript library="jquery" plugin="jquery"/>
    <jqval:resources/>
    <jqvalui:resources/>
    <jqui:resources/>
    <link href="<g:createLinkTo dir='/css/jquery-ui/themes/green' file='jquery-ui-1.8.14.custom.css'/>"
          rel="stylesheet"/>

    <script type="text/javascript" src="<g:createLinkTo dir="/js/jQuery" file="jquery.dataTables.js"/>"></script>
    <script type="text/javascript" src="<g:createLinkTo dir="/js/jQuery" file="jquery.numeric.js"/>"></script>
    <link href="${resource(dir: 'css', file: 'dataTables/demo_table.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'css', file: 'dataTables/demo_table_jui.css')}" rel="stylesheet" type="text/css"/>

    <tooltip:resources stylesheet="customTooltip"/>
    <g:javascript>
        $(function () {
            $('#ajax_spinner').ajaxStart(
                    function () {
                        $(this).show();
                    }).ajaxComplete(function () {
                        $(this).hide();
                    });
        });
    </g:javascript>
    <g:layoutHead/>
</head>

<body>

<g:render template="/template/header"/>
<div id="content">
    <div id="menuSide" xmlns="">
        <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_PROREKTOR">
            <div id="colOne">
                <g:render template="/template/menuSide"/>
                <g:pageProperty name="page.search"/>
            </div>
        </sec:ifAnyGranted>
    </div>
    <sec:ifAllGranted roles="ROLE_USER">
        <g:set var="style" value="width: 100%"/>
    </sec:ifAllGranted>
    <sec:ifNotGranted roles="ROLE_USER">
        <g:set var="style" value="width: 840px"/>
    </sec:ifNotGranted>

    <div id="colTwo" style="${style}">
        <div id="menuBuffer"></div>
        <g:pageProperty name="page.deleteConfirmation"/>
        <g:pageProperty name="page.editPassword"/>
        <g:pageProperty name="page.findPlan"/>
        <div>
            <g:render template="/template/notification/general"/>
            <sec:ifAnyGranted roles="ROLE_DEAN, ROLE_SECRETARY">
                <div align=center>
                    <h3>
                        <g:deanFacultyFullName/>
                    </h3>
                </div>
            </sec:ifAnyGranted>
            <g:layoutBody/>
        </div>

        <div id="buffer"></div>
    </div>
</div>
</body>
</html>

