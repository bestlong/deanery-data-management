<table style="margin-left: 5%; margin-right: 5%" width="90%;">
    <tr>
        <td width="29%" align="right">
            Усього
        </td>

        <td width="2%" align="right">
            ${totalCreditCount}
        </td>
        <td width="10%" align="right">
            <table width="90%" style="margin-right: 5px;">
                <tr align="left">
                    <td width="27%">
                        ${totalTotal}
                    </td>
                    <td width="25%" align="right">
                        ${totalSeminarCount}
                    </td>
                    <td width="30%" align="right">
                        ${totalLabCount}
                    </td>
                    <td width="18%">
                    </td>
                </tr>

            </table>
        </td>
        <td width="11%" align="right">
        </td>

        <g:each in="${totalSemestrs}" var="it">
            <td width="6%">
                <table width="100%">
                    <tr align="center">
                        <td width="20%">${it.total}</td>
                        <td width="20%">${it.lectureCount}</td>
                        <td width="20%"></td>
                        <td width="20%">${it.practiseCount}</td>
                        <td width="20%"></td>
                    </tr>
                </table>
            </td>
        </g:each>
    </tr>
</table>

<table style="margin-left: 5%; margin-right: 5%" width="90%;">
    <tr>
        <td width="31" align="right"></td>
        <td width="11%" align="right">
            <table width="90%" style="margin-right: 5px;">
                <tr >
                    <td width="20%">
                    </td>
                    <td width="32%" align="left">
                        ${totalLectureCount}
                    </td>
                    <td width="30%" align="left">
                        ${totalPractiseCount}
                    </td>
                    <td width="18%" align="left">
                        ${totalSamCount}
                    </td>
                </tr>

            </table>
        </td>
        <td width="10%">
        </td>

        <g:each in="${totalSemestrs}" var="it">
            <td width="6%">
            <table width="100%">
                <tr align="center">
                    <td width="20%"></td>
                    <td width="20%"></td>
                    <td width="20%">${it.seminarCount}</td>
                    <td width="20%"></td>
                    <td width="20%">${it.labCount}</td>
                </tr>
            </table>
            </td>
        </g:each>
    </tr>
</table>