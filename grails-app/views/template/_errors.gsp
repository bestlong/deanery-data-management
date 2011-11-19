<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.06.11
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>

<g:hasErrors bean="${errorBean}">
    <div class="errors">
        <g:renderErrors bean="${errorBean}" as="list" />
    </div>
</g:hasErrors>