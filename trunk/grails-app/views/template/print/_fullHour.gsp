<td width="6%">
    <table width="100%">
        <tr align="left">
            <td width="20%" align="center">
                ${hour.lectures + hour.seminars + hour.practices + hour.labs == 0 ? "__" :
                    hour.lectures + hour.seminars + hour.practices + hour.labs}
            </td>
            <td width="20%" align="center">
                ${hour.lectures == 0 ? "__" : hour.lectures}
            </td>
            <td width="20%" align="center">
                ${hour.seminars == 0 ? "__" : hour.seminars}
            </td>
            <td width="20%" align="center">
                ${hour.practices == 0 ? "__" : hour.practices}
            </td>
            <td width="20%" align="center">
                ${hour.labs == 0 ? "__" : hour.labs}
            </td>
        </tr>
    </table>
</td>