<div style="width: 800px;" id="messages" onclick='$(this).fadeOut(400);' onmouseover='$(this).fadeIn(400);'>
    <g:if test="${flash.message}">
        <g:render template="/template/notification/info" model="${[code: flash.message, args: flash.args]}"/>

    </g:if>
</div>

<div style="width: 840px;" id="errors" onclick='$(this).fadeOut(400);' onmouseover='$(this).fadeIn(400);'>
    <g:if test="${flash.error}">
        <g:render template="/template/notification/error" model="${[code: flash.error, args: flash.args]}"/>
    </g:if>
</div>