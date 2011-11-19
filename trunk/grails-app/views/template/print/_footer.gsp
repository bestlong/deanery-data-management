<table width="60%" align="center">
    <tr>
        <td align="left">2. Практики</td>
        <td align="right">2. Державна атестація</td>
    </tr>
</table>
<table border="2" style="margin-left: 5%; margin-right: 5%" width="90%;">
    <tr>
        <td width="3%" align="center">N</td>
        <td width="20%" align="center">Назва практики</td>
        <td width="6%" align="center">Семестр</td>
        <td width="6%" align="center">Тижнів</td>
        <td width="30%" align="center"></td>
        <td width="3%" align="center">N</td>
        <td width="6%" align="center">Семестр</td>
        <td width="10%" align="center">Строки проведення</td>
        <td width="16%" align="center">Форма державної атестації</td>
    </tr>
    <tr>
        <td>
            <table align="center">
                <g:each in="${plan.practise}" var="pract" status="i">
                    <tr>
                        <td>${i + 1}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td>
            <table align="center">
                <g:each in="${plan.practise}" var="pract">
                    <tr>
                        <td>${pract.name}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td>
            <table align="center">
                <g:each in="${practices}" var="pract">
                    <tr>
                        <td>${pract.semestr}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td>
            <table align="center">
                <g:each in="${practices}" var="pract">
                    <tr>
                        <td>${pract.weeks}</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td></td>
        <td valign="top" align="center">1</td>
        <td valign="top" align="center">${gos.semestr}</td>
        <td valign="top" align="center">${gos.date}</td>
        <td valign="top" align="center">${gos.forma}</td>
    </tr>
</table>

<table width="80%" align="center" style="margin-top: 10px">
    <tr>
        <td align="left" width="15"><g:formatDate date="${datePrint}" format="dd.MM.yyyy"/></td>
        <td align="left" width="10">Декан</td>
        <td align="left" width="50">${university.dean}</td>
        <td align="right" width="15">зав. кафедрою</td>
        <td align="right" width="10">${plan.chair.head}</td>
    </tr>
</table>
