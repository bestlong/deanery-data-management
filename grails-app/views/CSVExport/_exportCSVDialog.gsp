
<script type="text/javascript ">
    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialogcsv").hide()
    });
</script>
<div id="dialogcsv" title="Настройки экспорта">
    <p>${askMessagecsv}</p>

    <div class="action">
          <a href="#"  style="margin: 10px" id="chengpl" onclick='$("#dialogcsv").dialog("close")'> Экспортировать </a>
        <a href="#" style="margin: 10px" onclick='$("#dialogcsv").dialog("close")'>Отмена</a>
    </div>

</div>