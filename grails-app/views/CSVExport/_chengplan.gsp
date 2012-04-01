<script type="text/javascript ">
    $(function () {
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialogcvs").hide()
    });
</script>
<div id="dialogcvs" title="Настройки импорта">
    <p>${askMessagecvs}</p>

    <div class="action">
          <a href="#"  style="margin: 10px" id="chengpl" onclick='$("#dialogcvs").dialog("close")'> Импортировать </a>
        <a href="#" style="margin: 10px" onclick='$("#dialogcvs").dialog("close")'>Отмена</a>
    </div>

</div>