<%@ page import="decanat.grails.Faculty" %>



<div class="fieldcontain ${hasErrors(bean: facultyInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="faculty.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${facultyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facultyInstance, field: 'shortName', 'error')} ">
    <label for="shortName">
        <g:message code="faculty.shortName.label" default="Short Name"/>

    </label>
    <g:textField name="shortName" value="${facultyInstance?.shortName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facultyInstance, field: 'prorektor', 'error')} required">
    <label for="prorektor">
        <g:message code="faculty.prorektor.label" default="Prorektor"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="prorektor" required="" value="${facultyInstance?.prorektor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facultyInstance, field: 'dean', 'error')} required">
    <label for="dean">
        <g:message code="faculty.dean.label" default="Dean"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="dean" required="" value="${facultyInstance?.dean}"/>
</div>

