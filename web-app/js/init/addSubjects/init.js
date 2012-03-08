
    var oTable;

    function initTable() {
        oTable = $('#subjects').dataTable({
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers",
                    "iDisplayLength": 20,
                    "bLengthChange": false,
                    "oLanguage": {
                        "sInfo": "Всего: _TOTAL_. Показано с _START_ по _END_",
                        "sInfoEmpty": "Нет данных для отображения",
                        "sSearch": "Поиск",
                        "sLengthMenu":"Отображать по _MENU_",
                        "sInfoFiltered": "(найдено из _MAX_)",
                        "sZeroRecords": "По Вашему запросу ничего не найдено.",
                        "oPaginate":
                        {
                            "sFirst": "К началу",
                            "sPrevious": "Назад",
                            "sLast": "В конец",
                            "sNext": "Далее"
                        }
                    },
                    bAutoWidth: false,
                    "aoColumnDefs": [
                        { "bSortable": false, "aTargets": [ 0 ] }
                    ],
                    aoColumns   : [
                        { sWidth: "5%", bSortable: false },
                        { sWidth: "15%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%" },
                        { sWidth: "10%", bSortable: false }
                    ]
                });
    }


    $(document).ready(function () {
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

                $.post('${request.contextPath}/addSubjects/work',
                        {id:id},
                        function(data) {
                            for (i = 0; i < count.length; i++) {
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "lecture").html(data[i].lectures)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "practise").html(data[i].practices)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "seminar").html(data[i].seminars)
                                $("#subject" + data[i].planSubject.id + "semestr" + count[i] + "lab").html(data[i].labs)
                            }
                        });

                $.post('${request.contextPath}/addSubjects/control',
                        {id:id},
                        function(data) {
//                            alert(data);
                            for (i = 0; i < count.length; i++) {
                                if (data[i].exam == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "exam").attr("checked","checked");
                                if (data[i].zach == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "zach").attr("checked","checked");
                                if (data[i].cWork == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "cWork").attr("checked","checked");
                                if (data[i].cProj == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "cProj").attr("checked","checked");
                                if (data[i].rgr == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "rgr").attr("checked","checked");
                                if (data[i].contrWork == 1)
                                    $("#subject" + data[i].subjId + "semestr" + count[i] + "contrWork").attr("checked","checked");
                            }
                        });
            }
        });

        initTable();
    });

    function deleteDialog(iid, planId) {
        $("#delBtn").attr("href", '${request.contextPath + '/addSubjects/delete/'}' + iid+'?planId='+planId);
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