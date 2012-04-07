<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10.03.12
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
    <script type="text/javascript ">
        $(function () {
            $("input:submit, button, input:file").button();
        });
    </script>
</head>

<body>
<fieldset>
    <fieldset>
        <g:form controller="CSVExport" action="exportDirectoriToCSV" method="get" name="ExportToCSV">
            <input type="submit" value="Экспортировать справочники в CSV">
            <p></p>
            &nbsp
           Экспортировать справочники в CSV . В справочники входят кафедры, специальности и предметы.
            <p></p>
        </g:form>
    </fieldset>
    <p></p>
    <g:form controller="CSVExport" action="exportAllToCSV" method="get" name="ExportToCSV">
        <input type="submit" value="        Экспортировать всё в CSV           ">
    </g:form>



</fieldset>
</body>
</html>