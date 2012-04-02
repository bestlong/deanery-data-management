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
  <div>
<g:form controller="CSVExport" action="exportAllToCSV" method="get" name="ExportToCSV">

    <input type="submit" value="Экспортировать всё в CSV">

</g:form>
  </div>
 <div>
<g:form controller="CSVExport" action="exportDirectoriToCSV" method="get" name="ExportToCSV">

    <input type="submit" value="Экспортировать справочники в CSV">

</g:form>
 </div>

</body>
</html>