<head>
   <meta name='layout' content=''/>
    <title>Login</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'auth.css')}"/>
</head>
<body>
<div id='login'>
    <div class='inner'>
      <div class='fheader'>Авторизация</div>
        <form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
            <p>
                <label for='j_username'>Логин</label>
                <input type='text' class='text_' name='j_username' id='j_username' value='${request.remoteUser}'/>
            </p>
            <p>
                <label for='j_password'>Пароль</label>
                <input type='password' class='text_' name='j_password' id='j_password'/>
            </p>

            <p>
                <label for='remember_me'>Запомнить меня</label>
                <tooltip:tip code="tooltip.auth.remember">
                    <input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me'
                           <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                </tooltip:tip>
            </p>

            <p>
                <tooltip:tip code="tooltip.auth.submit">
                    <input type='submit' value='Войти'/>
                </tooltip:tip>
            </p>
        </form>
        <g:if test='${flash.message}'>
            <g:render template="/template/notification/error" model="${[msg:flash.message]}"/>
        </g:if>
    </div>
</div>
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>