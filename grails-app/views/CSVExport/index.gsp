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

<g:form controller="CSVExport" action="export">

    <fieldset>

        <table  >
            <tr>
                <td>
                    <g:radio name="myGroup" value="directori"/>
                        </td>
                <td>
                    <a style=" font-size: 14px;  color: #000000; ">
                        Экспортировать справочники в CSV .</a>
                </td>
            </tr>

            <tr>
                <td>
                </td>
                <td>
                    <a style=" font-size: 14px;  color: #000000;">
                        В справочники входят кафедры, специальности и предметы.   </a>

                </td>
            </tr>

            <tr>
                <td>
                    <g:radio name="myGroup" value="all" checked="true"/>
                </td>
                <td>
                    <a style="font-size: 14px; color: #000000;">    Экспортировать всё в CSV    </a>

                </td>
            </tr>

            <tr>
                <td>
                </td>
                <td>
                    <a style=" font-size: 14px;  color: #000000;">
                        В экспорт всего входит экспорт справочников, информаций о всех планах и деканатах.   </a>
                </td>
            </tr>

        </table>


        <table  >
            <tr>
                <td>

                </td>
                <td>

                    <input type="submit" value="         Экспортировать       ">

                </td>
            </tr>
        </table>
    </fieldset>


</g:form>

</body>
</html>