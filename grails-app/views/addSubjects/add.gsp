<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Добавление новых предметов</title>
    <meta name="layout" content="main"/>

    <script type="text/javascript">
        function switchBox(id, val) {
            document.getElementById(id).setAttribute('class', val ? 'visible' : 'invisible');
        }

        function showSubjectInGeneral() {
            for(var i = 1; i<=${plan?.semestrCount}; i++){
                document.getElementById('d' + i.toString()).setAttribute('class','nonDisplay');
            }
            document.getElementById('subjectGeneral').setAttribute('class','visible');
        }

        function switchDiv(id) {
            for (i = 1; i <=${plan?.semestrCount}; i++) {
                document.getElementById('d' + i.toString()).setAttribute('class', 'nonDisplay');
            }
            document.getElementById('subjectGeneral').setAttribute('class','nonDisplay');
            document.getElementById(id).setAttribute('class', 'display');
        }


    </script>
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


    <div id="selectPart">
        <h4 class="subtitle">Добавление предметов:</h4>
        <div id="tabs">
            <ul>
                <li>
                     <a href="#" onclick="javascript: showSubjectInGeneral()">
                         <span>Предмет</span>
                     </a>
                </li>
                <g:each in="${1..plan?.semestrCount}" var ="cp">
                    <li id="l${cp}">
                        <a href="#" onclick="javascript: switchDiv('d${cp}')">
                            <span>${cp} семестр</span>
                        </a>
                    </li>
                </g:each>
            </ul>
            <br/>
        </div>

    </div>

        <div id="subjectGeneral">
            <br/>
            <table id="addSubjTable">
                <tr>
                    <g:set var="idx" value="${1}"/>
                    <g:while test="${idx <= plan?.semestrCount}">
                        <td>
                            <g:if test="${hours?.containsKey(idx) && hours?.get(idx)}">
                                <h3>${idx} <g:checkBox name="c${idx}" value="true" onclick="javascript: switchBox('l${idx}', this.checked );"/></h3>
                            </g:if>
                            <g:else>
                                <h3>${idx} <g:checkBox name="c${idx}" onclick="javascript: switchBox('l${idx}', this.checked );"/></h3>
                            </g:else>
                        </td>
                        <g:set var="idx" value="${idx + 1}"/>
                    </g:while>
                </tr>
            </table>

            <g:render template="/template/planSubject/addSubjectInGeneral"/>
        </div>
        <div id="addSubjectInSemestr">
            <g:render template="/template/subject/addSubjects" model="[ 'semestr' : plan?.semestrCount]"/>
        </div>





</div>

</body>
</html>