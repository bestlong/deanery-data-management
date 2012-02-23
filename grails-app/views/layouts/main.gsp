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
    <link href="${resource(dir: 'css', file: 'dataTables/demo_table.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'css', file: 'dataTables/demo_table_jui.css')}" rel="stylesheet" type="text/css"/>

    <tooltip:resources stylesheet="customTooltip"/>
    <g:javascript>
        $(function() {
            $('#ajax_spinner').ajaxStart(
            function() {
                $(this).show();
            }).ajaxComplete(function() {
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
        <g:ifAllGranted role="ROLE_ADMIN">
            <div id="colOne">
                <g:render template="/template/menuSide"/>
                <g:pageProperty name="page.search"/>
            </div>
        </g:ifAllGranted>
    </div>
    <g:ifAllGranted role="ROLE_USER">
        <g:set var="style" value="width: 100%"/>
    </g:ifAllGranted>

    <div id="colTwo" style="${style}; width: 840px">
        <div id="menuBuffer"></div>
        <g:pageProperty name="page.deleteConfirmation"/>
        <g:pageProperty name="page.editPassword"/>
        <div>
            <g:render template="/template/notification/general"/>
            <g:layoutBody/>
        </div>

        <div id="buffer"></div>
    </div>
</div>
</body>
</html>

