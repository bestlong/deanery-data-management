<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Информация о деканате</title>
    <meta name="layout" content="main"/>
    <jqvalui:renderValidationScript for="decanat.grails.Faculty"/>
</head>

<body>
<script type="text/javascript ">
    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide()
    });
</script>

<div>
    <h4 class="subtitle">Изменить информацию:</h4>
    <g:form controller="university" action="update" width="300">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">Проректор<span style="color: red">*</span></td>
                <td>
                    <g:hiddenField name="id" value="${faculty.id}"/>
                    <g:textField name="prorektor" value="${faculty?.prorektor}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Декан<span style="color: red">*</span></td>
                <td>
                    <g:textField name="dean" value="${faculty?.dean}"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="index" action="index">Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>
</body>
</html>