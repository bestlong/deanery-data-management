<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление новых предметов</title>
    <meta name="layout" content="main"/>

     %{--<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>--}%
     %{--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>--}%
     %{--<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>--}%



    <style>
    .visible {
        visibility: visible;
    }

    .invisible {
        visibility: hidden;
    }

    .display {
        display: inline-table;
    }

    .nonDisplay {
        display: none;
    }
    </style>

    <g:render template="/template/planSubject/validation"/>
</head>

<body>
<div>
    <content tag="search">
        <g:render template="/template/subject/searchTemplate" model="['controller':'addSubjects']"/>
        <g:render template="/template/plan/menu" model="[ 'active' : 2, 'plan': plan ]"/>
    </content>

    <div id="searchPart" style="display: none;">
        <h4 class="subtitle">для выбора предмета нажмите на его имя:</h4>
        <g:render template="/template/subject/selectSubject" model="${[res: res]}"/>
        <div align="center" class="action">
            <a href="" onclick="showOrHide(false)">Отмена</a>
        </div>
    </div>

    <h4 class="subtitle">Добавление предметов:</h4>
      <div id="tabs">
          <ul>
              <li>
                  <a href="#subjectGeneral">
                      <span>Предмет</span>
                  </a>
              </li>
              <g:each in="${1..plan?.semestrCount}" var ="cp">
                  <li>
                      <a href="#semestrNumber-${cp}">
                          <span>${cp} семестр</span>
                      </a>
                  </li>
              </g:each>
          </ul>

        <div id="subjectGeneral">

            <g:render template="/template/planSubject/addSubjectInGeneral"/>
        </div>
            <g:render template="/template/subject/addSubjects" model="[ 'semestr' : plan?.semestrCount]"/>
    </div>
</div>



<script>
    $(document).ready(function() {
        $("#tabs").tabs();
    });
</script>

</body>
</html>