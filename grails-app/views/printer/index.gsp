<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title> Предварительный просмотр</title>
    <meta name="layout" content="main"/>
    <calendar:resources lang="en" theme="tiger"/>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#dialog").dialog(
                    {
                        height: 150,
                        width:400,
                        zIndex: 0,
                        resizable: false,
                        draggable: false,
                        close: function(event, ui) {
                            window.location.replace('${request.contextPath + '/index/index/'}');
                        }
                    });
            $("input:submit, a, button", ".action").button();
        });
    </script>

    <style type="text/css">
    body .calendar {
        z-index: 1000;
    }
    </style>
</head>

<body>
<div id="dialog" title="Необходимая для печати инфрмация">
    <g:form action="preview" controller="printer">
        <div class="action">
            <table>
                <tr>
                    <td align="left">
                        <g:hiddenField name="id" value="${planId}"/>
                        Дата печати
                    </td>
                    <td align="right">
                        <calendar:datePicker name="date" defaultValue="${new Date()}" />
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <g:submitButton name="submit" value="Предв. просмотр"/>
                    </td>
                    <td align="left">
                        <g:submitButton name="submit" value="Экспорт в Excel"/>
                    </td>
                    <td align="right">
                        <a href="<g:createLink action="index" controller="index"/>" style="margin: 10px">Отмена</a>
                    </td>
                </tr>
            </table>
        </div>
    </g:form>
</div>

</body>
</html>


