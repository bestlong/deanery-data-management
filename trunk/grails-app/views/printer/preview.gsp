<%--
  Created by IntelliJ IDEA.
  decanat.grails.User: Admin
  Date: 27.06.11
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <g:javascript library="jquery" plugin="jquery"/>
    <meta name="layout" content="none"/>
    <style type="text/css">
    body table tr td {
        font-size: 6px;
    }
    </style>

    <script type="text/javascript">
        $(document).ready(function () {
//            window.print();
        });
    </script>
</head>

<body>

<g:render template="/template/print/header" model="[plan: plan, university: university, datePrint: datePrint]"/>
<g:render template="/template/print/subjectHeader"/>

<g:each in="${subjects}" var="subject" status="status">
    <g:render template="/template/print/subject" model="['subject': subject]"/>
</g:each>

<g:render template="/template/print/sum"/>

<g:render template="/template/print/footer" model="[plan: plan, university: university, datePrint: datePrint]"/>

</body>
</html>


