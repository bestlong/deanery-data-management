<g:render template="/template/print/total"/>

<table style="margin-left: 5%; margin-right: 5%" width="90%;">
<tr>
<td width="31%" align="right">
    Іспитів
</td>
<td width="10%" align="right"></td>
<td width="11%" align="right">
    <table width="90%" style="margin-right: 5px;">
        <tr align="left">
            <td width="17%">
                ${totalExamCount}
            </td>
            <td width="83%"></td>
        </tr>
    </table>
</td>
<g:each in="${totalSemestrs}" var="it">
    <g:render template="/template/print/totalColumn" bean="${it.examCount}" var="value"/>
</g:each>
</tr>
</table>

<table style="margin-left: 5%; margin-right: 5%" width="90%;">
<tr>
<td width="31%" align="right">
    Заліків
</td>
<td width="10%" align="right"></td>
<td width="11%" align="right">
    <table width="90%" style="margin-right: 5px;">
        <tr align="left">
            <td width="17%"></td>
            <td width="17%">${totalZachCount}</td>
            <td width="65%"></td>
            <td width="16%"></td>
        </tr>
    </table>
</td>
<g:each in="${totalSemestrs}" var="it">
    <g:render template="/template/print/totalColumn" bean="${it.zachCount}" var="value"/>
</g:each>
</tr>
</table>



<table style="margin-left: 5%; margin-right: 5%" width="90%;">
<tr>
    <td width="31%" align="right">
        Курсових проектів
    </td>

    <td width="10%" align="right">
    </td>
    <td width="11%" align="right">
        <table width="90%" style="margin-right: 5px;">
            <tr align="center">
                <td width="34%"></td>
                <td width="17%">${totalCProjCount}</td>
                <td width="48%"></td>
            </tr>
        </table>
    </td>
<g:each in="${totalSemestrs}" var="it">
    <g:render template="/template/print/totalColumn" bean="${it.cProjCount}" var="value"/>
</g:each>
</tr>
</table>

<table style="margin-left: 5%; margin-right: 5%" width="90%;">
<tr>
<td width="31%" align="right">
    Курсових робіт
</td>
<td width="10%" align="right">
</td>
<td width="11%" align="right">
    <table width="90%" style="margin-right: 5px;">
        <tr align="center">
            <td width="51%"></td>
            <td width="16%">${totalCWorkCount}</td>
            <td width="32%"></td>
        </tr>
    </table>
</td>
<g:each in="${totalSemestrs}" var="it">
    <g:render template="/template/print/totalColumn" bean="${it.cWorkCount}" var="value"/>
</g:each>
</tr>
</table>

<table style="margin-left: 5%; margin-right: 5%" width="90%;">
<tr>
<td width="31%" align="right">
    Ргр
</td>
<td width="10%" align="right"></td>
<td width="11%" align="right">
    <table width="90%" style="margin-right: 5px;">
        <tr align="center">
            <td width="67%"></td>
            <td width="16%">${totalRgrCount}</td>
            <td width="16%"></td>
        </tr>
    </table>
</td>

<g:each in="${totalSemestrs}" var="it">
    <g:render template="/template/print/totalColumn" bean="${it.rgrCount}" var="value"/>
</g:each>

</tr>
</table>