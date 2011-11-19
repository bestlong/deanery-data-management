<div id="header">
    <g:isLoggedIn>

        <ul id="menu">
            <li>
                <a style="text-decoration: ${active == 1 ? 'underline' : 'none'}"
                   href="<g:createLink action="index" controller="index"/> ">главная</a>
            </li>
            <g:ifAllGranted role="ROLE_ADMIN">
                <li>
                    <a style="text-decoration: ${active == 2 ? 'underline' : 'none'}"
                       href="<g:createLink action="index" controller="user"/>">пользователи</a>
                </li>
            </g:ifAllGranted>
            <li>
                <a style="text-decoration: ${active == 3 ? 'underline' : 'none'}"
                   href="<g:createLink action="index" controller="profile"/> ">мой профиль</a></li>
            <li>
                <a href="<g:createLink action="index" controller="logout"/>">выход</a>
            </li>
        </ul>

        <div align="right" class="hello">Здравствуйте, ${g.userName()}</div>
    </g:isLoggedIn>
</div>
