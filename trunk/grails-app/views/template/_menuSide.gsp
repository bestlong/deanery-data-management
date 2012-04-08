<style type="text/css">
.selectedMenu {
    background: 0 100% no-repeat #e6e6fa;
    margin-bottom: 10px;
    clear: both;
    -moz-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
}

</style>

<div class="menuBox">
    <h3>
        <g:render template="/template/notification/busyIndicator"/>
        Меню
    </h3>
    <ul class="menuBottom">
        <tooltip:tip code="tooltip.menu.gotoSpeciality">
            <li class="${selectedMenu == 1 ? 'selectedMenu' : ''}">
                <g:link controller="speciality" action="index">
                    Специальности
                </g:link>
            </li>
        </tooltip:tip>
        <tooltip:tip code="tooltip.menu.gotoSubject">
            <li class="${selectedMenu == 2 ? 'selectedMenu' : ''}">
                <g:link controller="subject" action="index">Предметы</g:link>
            </li>
        </tooltip:tip>
        <tooltip:tip code="tooltip.menu.gotoChair">
            <li class="${selectedMenu == 3 ? 'selectedMenu' : ''}">
                <g:link controller="chair" action="index">Кафедры</g:link>
            </li>
        </tooltip:tip>
        <sec:ifAnyGranted roles="ROLE_DEAN">
            <tooltip:tip code="tooltip.menu.gotoUniversity">
                <li class="${selectedMenu == 4 ? 'selectedMenu' : ''}">
                    <g:link controller="university" action="index">Информация о деканате</g:link>
                </li>
            </tooltip:tip>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_PROREKTOR">
            <tooltip:tip code="tooltip.menu.gotoDeanery">
                <li class="${selectedMenu == 5 ? 'selectedMenu' : ''}">
                    <g:link controller="deanery" action="index">Деканаты</g:link>
                </li>
            </tooltip:tip>
        </sec:ifAnyGranted>
    </ul>
</div>



