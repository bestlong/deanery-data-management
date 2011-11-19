    <g:javascript>
        function initTable() {
            $('#subjects').dataTable({
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
                aoColumns   : [
                    { sWidth: "30%" },
                    { sWidth: "30%" },
                    { sWidth: "25%" }
                ]
            });
        }


        $(document).ready(function () {
            $('#searchSubject').hide();
            var message = 'только цифры'
            initTable();
            $("#messages").delay(6000).fadeOut(5 * 400);
            $("#errors").delay(6000).fadeOut(5 * 400);
            $("input:submit, a, button", ".action").button();
            $("#dialog").hide();
            $("#selectSubj").button();
            var myForm = $('#formSave');
            myForm.validate({
                onkeyup: false,
                errorClass: 'error_field',
                validClass: 'valid',
                onsubmit: true,
                success: function(label) {
                    $('[id=' + label.attr('for') + ']').qtip('destroy');
                },
                errorPlacement: function(error, element) {
                    if ($(error).text() != ''){
                        if (element.attr('id') == 'subjId')
                            element = $('#selectSubj');
                        $(element).filter(':not(.valid)').qtip({
                            overwrite: true,
                            content: error,
                            position: { my: 'left center', at: 'right center' },
                            show: {
                                event: false,
                                ready: true
                            },
                            hide: false,
                            style: {
                                widget: false,
                                classes: 'ui-tooltip-blue ui-tooltip-jtools ui-tooltip-rounded',
                                tip: true
                            }
                        });
                        }
                },
                rules: {
                    <g:each in="${1..plan.semestrCount}" var="index">
                        lectureCount${index}: {
                            min: 0
                        },
                        seminarCount${index}: {
                            min: 0
                        },
                        practiceCount${index}: {
                            min: 0
                        },
                        labCount${index}: {
                            min: 0
                        },
                    </g:each>
                    subjId: {
                        required:true
                    },
                    creditCount: {
                        min: 0
                    },
                    lectureCount: {
                        min: 0
                    },
                    seminarCount: {
                        min: 0
                    },
                    practiceCount: {
                        min: 0
                    },
                    labCount: {
                        min: 0
                    },
                    samCount: {
                        min: 0
                    }
                },
                messages: {
                    <g:each in="${1..plan.semestrCount}" var="index">
                        lectureCount${index}: {
                            min: message
                        },
                        seminarCount${index}: {
                            min: message
                        },
                        practiceCount${index}: {
                            min: message
                        },
                        labCount${index}: {
                            min: message
                        },
                    </g:each>
                    subjId: {
                        required:'Выберите предмет'
                    },
                    creditCount: {
                        min: message
                    },
                    lectureCount: {
                        min: message
                    },
                    seminarCount: {
                        min: message
                    },
                    practiceCount: {
                        min: message
                    },
                    labCount: {
                        min: message
                    },
                    samCount: {
                        min: message
                    }
                }
            });

        });

        function showOrHide(show) {
            $('#selectSubj').qtip('destroy');
            $('#creditCount').qtip('destroy');
            $('#seminarCount').qtip('destroy');
            $('#practiceCount').qtip('destroy');
            $('#labCount').qtip('destroy');
            $('#samCount').qtip('destroy');
            $("#searchPart").toggle(show);
            $("#selectPart").toggle(!show);
            $("#searchSubject").toggle(show);
            $("#menuBox").toggle(!show);
        }

        function chooseSubj(id, name) {
            $("#subjName").html(name);
            $("#subjId").val(id);
            showOrHide(false);
        }
    </g:javascript>