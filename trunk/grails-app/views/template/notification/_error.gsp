<%--
  Created by IntelliJ IDEA.
  User: drStout
  Date: 04.07.11
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>

<div class='message_error'>
    <table>
        <tr>
            <td>
                <img src="<g:createLinkTo dir="/images/notifications" file="error.png"/>" align="left" width="80%">
            </td>
            <td>
                <div class="message_error_info">
                    <g:if test="${code}">
                        <g:message code="${code}" args="${args}"/>
                    </g:if>
                    <g:if test="${msg}">
                        ${msg}
                    </g:if>
                </div>
            </td>
        </tr>
    </table>
</div>