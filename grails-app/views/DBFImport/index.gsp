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
    <legend>Импорт из DBF файлов</legend>
    <g:form action="upload" method="post" enctype="multipart/form-data">
        <label for="dbf">DBF файл</label>
        <input type="file" name="dbf" id="dbf"/>

        <div style="margin: 1.0em;">
            Имя файла должно находится в списке: DIS.dbf, KAFEDRA.dbf, SPEC.dbf
            <br/>
            Регистр не учитывается
        </div>
        <input type="submit" class="buttons" value="Загрузить"/>
    </g:form>
</fieldset>
<g:if test="${count}">
    <div style="background-color: #adff2f;">
        В результате импорта успешно сохранено ${count} записей
    </div>
</g:if>
<g:if test="${validationErrors}">
    <table border="1" width="840px">
        <tr>
            <th align="center" colspan="3">
                Ошибки импорта
            </th>
        </tr>
        <tr>
            <td>
               Значение записи
            </td>
            <td>
                Поле с ошибкой
            </td>
            <td>
                Значение поля
            </td>
        </tr>
        <g:each in="${validationErrors}" var="it">
            <tr style="background-color: #ff4400;">
                <td>
                    ${it.message}
                </td>
                <td>
                    ${it.field}
                </td>
                <td>
                    ${it.rejectedValue}
                </td>
            </tr>
        </g:each>
    </table>
</g:if>
</body>
</html>