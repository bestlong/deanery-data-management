<div id="updateChairDiv">
    <table id="chairList" class="display">
        <thead>
        <tr>
            <th>Код</th>
            <th>Название</th>
            <th>Аббревиатура</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${res}" var="chair">
            <tr>
                <td>
                    ${chair.codeChair}
                </td>
                <td>
                    <a href="#" onclick="chooseChair('${chair?.id}', '${chair?.name}')" class="delBtn">
                        ${chair.name}
                    </a>
                </td>
                <td>
                    ${chair.shortName}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>