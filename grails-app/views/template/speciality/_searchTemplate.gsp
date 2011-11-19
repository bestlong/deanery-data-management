<div class="search" id ="searchSpeciality">
    <h3>Поиск</h3>
    <g:formRemote name="fromSearch" update="updateSpecialityDiv"
                  url="[controller:controller, action:'search']"
                  onSuccess="${searchConfig?.successFunction ?: 'initTable();'}">
        <div style="float: left; padding: 0 0 0 20px">
            <b class="searchTitles">Код</b><br/>
            <g:textField name="code"/><br/>
            <b class="searchTitles">Имя</b><br/>
            <g:textField name="name"/><br/>
            <b class="searchTitles">Короткое имя</b><br/>
            <g:textField name="shortName"/><br/>

            <br/>
            <div align="center" class="action">
                <g:submitButton name="find" value="Найти" id="searchButton"/>
            </div>
        </div>
    </g:formRemote>

</div>