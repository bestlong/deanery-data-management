<div id="header">
    <sec:ifLoggedIn>
        <ul id="menu">
            <li>
                <a style="text-decoration: ${active == 1 ? 'underline' : 'none'}"
                   href="<g:createLink action="index" controller="index"/> ">главная</a>
            </li>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
                <li>
                    <a style="text-decoration: ${active == 2 ? 'underline' : 'none'}"
                       href="<g:createLink action="index" controller="user"/>">пользователи</a>
                </li>
            </sec:ifAnyGranted>
            <li>
                <a style="text-decoration: ${active == 3 ? 'underline' : 'none'}"
                   href="<g:createLink action="index" controller="profile"/> ">мой профиль</a></li>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
                <li>
                    <a style="text-decoration: ${active == 4 ? 'underline' : 'none'}"
                       href="<g:createLink action="index" controller="DBFImport"/>">DBF импорт</a>
                </li>
            </sec:ifAnyGranted>
        </ul>

        <div align="right" class="hello">${g.userName()} &nbsp;
            <a  style="color: white;" href="<g:createLink action="index" controller="logout"/>">выход</a>
        </div>
    </sec:ifLoggedIn>
</div>
