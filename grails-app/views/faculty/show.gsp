<%@ page import="decanat.grails.Faculty" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'faculty.label', default: 'Faculty')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-faculty" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-faculty" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list faculty">

        <g:if test="${facultyInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="faculty.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${facultyInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${facultyInstance?.shortName}">
            <li class="fieldcontain">
                <span id="shortName-label" class="property-label"><g:message code="faculty.shortName.label"
                                                                             default="Short Name"/></span>

                <span class="property-value" aria-labelledby="shortName-label"><g:fieldValue bean="${facultyInstance}"
                                                                                             field="shortName"/></span>

            </li>
        </g:if>

        <g:if test="${facultyInstance?.prorektor}">
            <li class="fieldcontain">
                <span id="prorektor-label" class="property-label"><g:message code="faculty.prorektor.label"
                                                                             default="Prorektor"/></span>

                <span class="property-value" aria-labelledby="prorektor-label"><g:fieldValue bean="${facultyInstance}"
                                                                                             field="prorektor"/></span>

            </li>
        </g:if>

        <g:if test="${facultyInstance?.dean}">
            <li class="fieldcontain">
                <span id="dean-label" class="property-label"><g:message code="faculty.dean.label"
                                                                        default="Dean"/></span>

                <span class="property-value" aria-labelledby="dean-label"><g:fieldValue bean="${facultyInstance}"
                                                                                        field="dean"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${facultyInstance?.id}"/>
            <g:link class="edit" action="edit" id="${facultyInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
