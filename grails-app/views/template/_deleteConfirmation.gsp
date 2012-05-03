<div id="dialog" title="Подтверждение удаления">
    <p>${askMessage}</p>

    <div class="action">
        <a href="#" id="delBtn" style="margin: 10px">Удалить</a>

        <a href="#" style="margin: 10px" onclick='$("#dialog").dialog("close")'>Не удалять</a>
    </div>
</div>

<div id="dialogImportDBF" title="Импорт предметов в план" style="display: none;">
    <g:form action="dbfImport" controller="index" method="post" enctype="multipart/form-data">
        <g:hiddenField name="importPlanId"/>
        <table width="100%" class="action">
            <tr>
                <td colspan="2" align="center">
                    <label for="dbf">DBF файл</label>
                    <input type="file" name="dbf" id="dbf"/>
                </td>
            </tr>
            <tr></tr><tr></tr><tr></tr><tr></tr>
            <tr valign="bottom">
                <td align="center" valign="bottom" colspan="2">
                    <input type="submit" class="buttons" value="Загрузить"/>
                    <a href="#" style="margin: 10px" onclick='$("#dialogImportDBF").dialog("close")'>Отмена</a>
                </td>
            </tr>
        </table>
    </g:form>
</div>