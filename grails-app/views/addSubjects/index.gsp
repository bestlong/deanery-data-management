<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Добавление предметов</title>
<meta name="layout" content="main"/>
<script type="text/javascript">

    var oTable;

    function initTable() {
        oTable = $('#subjects').dataTable({
            "bJQueryUI":true,
            "sPaginationType":"full_numbers",
            "iDisplayLength":15,
            "bLengthChange":false,
            "oLanguage":{
                "sInfo":"Всего: _TOTAL_. Показано с _START_ по _END_",
                "sInfoEmpty":"Нет данных для отображения",
                "sSearch":"Поиск",
                "sLengthMenu":"Отображать по _MENU_",
                "sInfoFiltered":"(найдено из _MAX_)",
                "sZeroRecords":"По Вашему запросу ничего не найдено.",
                "oPaginate":{
                    "sFirst":"К началу",
                    "sPrevious":"Назад",
                    "sLast":"В конец",
                    "sNext":"Далее"
                }
            },
            "fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
                /*
                 * Calculate the total market share for all browsers in this table (ie inc. outside
                 * the pagination)
                 */
                var iTotalMarket = 0;
                var iCreditTotal = 0;
                var iLectureTotal = 0;
                var iSeminarTotal = 0;
                var iPractiseTotal = 0;
                var iLabTotal = 0;
                var iSamostTotal = 0;
                var iTotalTotal = 0;
                for ( var i=0 ; i<aaData.length ; i++ )
                {
                    iCreditTotal += aaData[i][2]*1;
                    iLectureTotal += aaData[i][3]*1;
                    iSeminarTotal += aaData[i][4]*1;
                    iPractiseTotal += aaData[i][5]*1;
                    iLabTotal += aaData[i][6]*1;
                    iSamostTotal += aaData[i][7]*1;
                    iTotalTotal += aaData[i][8]*1;
                    iTotalMarket += aaData[i][4]*1;
                }

                /* Calculate the market share for browsers on this page */
                var iPageMarket = 0;
                var iCreditPage = 0;
                var iLecturePage = 0;
                var iSeminarPage = 0;
                var iPractisePage = 0;
                var iLabPage = 0;
                var iSamostPage = 0;
                var iTotalPage = 0;
                for ( var i=iStart ; i<iEnd ; i++ )
                {
                    iCreditPage += aaData[ aiDisplay[i] ][2]*1;
                    iLecturePage += aaData[ aiDisplay[i] ][3]*1;
                    iSeminarPage += aaData[ aiDisplay[i] ][4]*1;
                    iPractisePage += aaData[ aiDisplay[i] ][5]*1;
                    iLabPage += aaData[ aiDisplay[i] ][6]*1;
                    iSamostPage += aaData[ aiDisplay[i] ][7]*1;
                    iTotalPage += aaData[ aiDisplay[i] ][8]*1;
                    iPageMarket += aaData[ aiDisplay[i] ][4]*1;
                }

                /* Modify the footer row to match what we want */
                var nCells = nRow.getElementsByTagName('th');

                nCells[1].innerHTML = 'Всего';
                nCells[2].innerHTML = parseInt(iCreditPage * 100)/100 +
                        ' ('+ parseInt(iCreditTotal * 100)/100 +' всего)';
                nCells[3].innerHTML = parseInt(iLecturePage * 100)/100 +
                        ' ('+ parseInt(iLectureTotal * 100)/100 +' всего)';
                nCells[4].innerHTML = parseInt(iSeminarPage * 100)/100 +
                        ' ('+ parseInt(iSeminarTotal * 100)/100 +' всего)';

                nCells[5].innerHTML = parseInt(iPractisePage * 100)/100 +
                        ' ('+ parseInt(iPractiseTotal * 100)/100 +' всего)';

                nCells[6].innerHTML = parseInt(iLabPage * 100)/100 +
                        ' ('+ parseInt(iLabTotal * 100)/100 +' всего)';

                nCells[7].innerHTML = parseInt(iSamostPage * 100)/100 +
                        ' ('+ parseInt(iSamostTotal * 100)/100 +' всего)';

                nCells[8].innerHTML = parseInt(iTotalPage * 100)/100 +
                        ' ('+ parseInt(iTotalTotal * 100)/100 +' всего)';
//
//                nCells[1].innerHTML = parseInt(iPageMarket * 100)/100 +
//                        '% ('+ parseInt(iTotalMarket * 100)/100 +'% total)';
            },
            bAutoWidth:false,
            "aoColumnDefs":[
                { "bSortable":false, "aTargets":[ 0 ] }
            ],
            aoColumns:[
                {bSortable:false},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                { bSortable:false }
            ]
        });
    }

    function initDocument(){
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide();

        /*
         * Insert a 'details' column to the table
         */
        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="<g:createLinkTo dir="/images" file="details_open.png"/>">';
        nCloneTd.className = "center";

        $('#subjects thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });

        $('#subjects tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });
    }


    $(document).ready(function () {
        initDocument();
        $('#subjects tbody td img').live('click', function () {
            var nTr = this.parentNode.parentNode;
            if (this.src.match('details_close')) {
                /* This row is already open - close it */
                this.src = "${request.contextPath}/images/details_open.png";
                oTable.fnClose(nTr);
            }
            else {
                /* Open this row */
                this.src = "${request.contextPath}/images/details_close.png";
                var id = nTr.id;
                var count = $("#count" + id).val()
                oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr, id, count), 'detailsStyle');

                $.post('${request.contextPath}/addSubjects/hours',
                        {id:id},
                        function (data) {
                            for (i = 0; i < count.length; i++) {
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "lecture").html(data[i].lectureCount)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "practise").html(data[i].practiceCount)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "seminar").html(data[i].seminarCount)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "lab").html(data[i].labCount)
                            }
                        });

                $.post('${request.contextPath}/addSubjects/control',
                        {id:id},
                        function (data) {
                            for (i = 0; i < count.length; i++) {
                                if (data[i].exam == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "exam").attr("checked", "checked");
                                if (data[i].zach == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "zach").attr("checked", "checked");
                                if (data[i].cWork == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "cWork").attr("checked", "checked");
                                if (data[i].cProj == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "cProj").attr("checked", "checked");
                                if (data[i].rgr == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "rgr").attr("checked", "checked");
                                if (data[i].contrWork == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "contrWork").attr("checked", "checked");
                            }
                        });
            }
        });
        initTable();
    });

    function deleteDialog(iid, planId) {
        $("#delBtn").attr("href", '${request.contextPath + '/addSubjects/delete/'}' + iid + '?planId=' + planId);
        $("#dialog").dialog();
    }

    function fnFormatDetails(oTable, nTr, subjId, count) {
        var aData = oTable.fnGetData(nTr);
        var sOut = '<table cellspacing="0" border="0" align="center">';
        var id;
        sOut += '<tr><td></td>';
        for (i = 0; i < count.length; i++) {
            sOut += '<td>' + count[i] + ' семестр</td>';
        }
        sOut += '</tr>';
        sOut += '<tr>';
        sOut += '<td colspan=' + count.length + 1 + ' align="center">Количество за 2 недели</td>';


        sOut += '<tr><td>Лекций</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'lecture';
            sOut += '<td id =' + id + '>0</td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Практик</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'practise';
            sOut += '<td id =' + id + '>0</td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Семинаров</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'seminar';
            sOut += '<td id =' + id + '>0</td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Лабораторных</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'lab';
            sOut += '<td id =' + id + '>0</td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td colspan=' + count.length + 1 + ' align="center">Вид контроля</td>';

        sOut += '<tr><td>Экзамен</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'exam';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Зачёт</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'zach';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Курсовая работа</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'cWork';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Курсовой проект</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'cProj';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>РГР</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'rgr';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';

        sOut += '<tr><td>Контрольная работа</td>';
        for (i = 0; i < count.length; i++) {
            id = ''
            id = 'subject' + subjId + 'semestr' + count[i] + 'contrWork';
            sOut += '<td><input type="checkbox" disabled="disabled" id =' + id + '></td>';
        }
        sOut += '</tr>';
        sOut += '</table>';

        return sOut;
    }
</script>
<style>
td.detailsStyle {
    background-color: #D0E9F5;
    border: 2px solid #A19B9E;
}
</style>
</head>

<body>
<h4 class="subtitle">Добавление предметов:</h4>
<content tag="search">
    <g:render template="/template/plan/menu" model="['active': 2, 'plan': plan]"/>
    <g:render template="/template/planSubject/filter" model="['plan': plan]"/>
</content>

<div align="left">
    <tooltip:tip code="tooltip.subject.add">
        <a href="<g:createLink action="add" controller="addSubjects" id="${plan.id}"/>">
            <input type="image" src="<g:createLinkTo dir="/images/ctrl" file="new.png"/>">
        </a>
    </tooltip:tip>
</div>

<div id="planSubjectList">
    <g:render template="/template/planSubject/planSubjectList" model="${[res: res]}"/>
</div>

<div align="center" class="action">
    <g:link controller="planInit" action="index" id="${plan.id}">Отмена</g:link>

    <g:link controller="practice" action="index" id="${plan.id}">Следующий шаг</g:link>

</div>
<content tag="deleteConfirmation">
    <g:render template="/template/deleteConfirmation"
              model="['askMessage': 'Вы точно хотите удалить этот предмет из плана?']"/>
</content>

</body>
</html>
