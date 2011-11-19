<%--
  Created by IntelliJ IDEA.
  User: corwin
  Date: 10.07.11
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <meta name="layout" content="main"/>
      <jqvalui:renderValidationScript for="decanat.grails.User"/>
  </head>
  <body>
   <div align="left" style="width: 17%">
  <g:form action="saveNewPasswd" name="changePasswdForm">
  <b><label>Старый пароль</label></b><br>
  <b><input name="oldPasswd" type="password"/></b><br>
  <b><label>Новый пароль</label> </b><br>
  <b><input name="newPasswd" type="password"/> </b><br>
  <b><label>Повторно введите новый пароль</label> </b><br>
  <b><input name="confirmPasswd" type="password"/> </b><br>
  <b><input name="UserID" type="text" style="display: none; "/></b><br>
   <div align="center">
   <table>
     <tr> <td><b><input type="submit" value="сменить"></b><br> </td>
      <td><b><input type="button" value="отмена"></b><br></td>
     </tr>
   </table>
   </div>
  </div>
  </g:form>



  </body>
</html>