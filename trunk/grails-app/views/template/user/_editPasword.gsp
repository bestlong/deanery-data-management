<script type="text/javascript ">
    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide()
    });
</script>
<div id="dialog" title="Изменение пароля">
    <g:form controller="user" action="changePass" method="get" name="editPasswd">
        <g:hiddenField name="userId" value="${user?.id}" />
        <table id="editPassTable" align="center">
            <tr>
                <td>Новый пароль*</td>
                <td>
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