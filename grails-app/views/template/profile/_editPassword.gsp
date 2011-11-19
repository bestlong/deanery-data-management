<script type="text/javascript ">

    $(function() {
           $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide()
    });


</script>
<div id="dialog" title="Изменение пароля">
<g:form controller="profile" action="edit" width="200" name="editPasswd">
        <table id="editPassTable" align="center">
            <tr>
                <td>Старый пароль*</td>
                <td >
                    <g:hiddenField name="id" value="${user?.id}"/>
                    <g:passwordField name="oldPasswd" />
                </td>
            </tr>
            <tr>
                <td>Новый пароль*</td>
                <td >
                    <g:passwordField name="newPasswd"/>
                </td>
            </tr>
            <tr>
                <td>Подтверждение пароля*</td>
                <td>
                    <g:passwordField name="confirmPasswd"/>
                </td>
            </tr>
        </table>
        <br/>

    <div class="action" align="center">
        <input type="submit" value="Изменить">

        <a href="#" style="margin: 10px" onclick='$("#dialog").dialog("close")'>Отмена</a>
    </div>
    </g:form>
</div>