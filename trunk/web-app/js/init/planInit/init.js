function initTableSpeciality() {
    $('#specialityList').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "iDisplayLength": 15,
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
            }
    );
}

function initTableChair() {
    $('#chairList').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "iDisplayLength": 15,
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
                    { sWidth: "50%" },
                    { sWidth: "50%" }
                ]
            }
    );
}

$(document).ready(function () {
    initTableSpeciality();
    initTableChair();
    $("#messages").delay(6000).fadeOut(5 * 400);
    $("#errors").delay(6000).fadeOut(5 * 400);
    $("input:submit, a, button", ".action").button();
    $("#selectSpec").button();
    $("#selectChair").button();
    $("#dialog").hide();
    $('#searchSpeciality').hide();
    $('#searchChair').hide();
    $(".integer").numeric({ decimal: false, negative: false }, null);
});

function showChairSelect() {
    destroyQtips();
    $("#searchPart").hide();
    $("#selectPart").hide();
    $("#searchSpeciality").hide();
    $("#menuBox").hide();
    $('#searchChair').show();
    $("#searchPartChair").show();
}

function showSpecialitySelect(show) {
    destroyQtips();
    $('#selectSpec').qtip('destroy');
    $("#searchPart").show();
    $("#selectPart").hide();
    $("#searchSpeciality").show();
    $("#menuBox").hide();
    $('#searchChair').hide();
    $("#searchPartChair").hide();
}

function destroyQtips(){
    $('#selectChair').qtip('destroy');
    $('#selectSpec').qtip('destroy');

    $('#form').qtip('destroy');
    $('#direction').qtip('destroy');
    $('#level').qtip('destroy');
    $('#semestrCount').qtip('destroy');
    $('#termin').qtip('destroy');
    $('#qualification').qtip('destroy');
}

function showMain() {
    $('#selectSpec').qtip('destroy');
    $("#searchPart").hide();
    $("#selectPart").show();
    $("#searchSpeciality").hide();
    $("#menuBox").show();
    $('#searchChair').hide();
    $("#searchPartChair").hide();
}

function chooseChair(id, name) {
    $("#chairName").html(name);
    $("#chairId").val(id);
    showMain(false);
}
function chooseSpeciality(id, name) {
    $("#specialityName").html(name);
    $("#specId").val(id);
    showMain(false);
}

$(function() {
    var myForm = $('#selectSpecialityForm');
    myForm.validate({
                onkeyup: false,
                errorClass: 'error_field',
                validClass: 'valid',
                onsubmit: true,
                success: function(label) {
                    $('[id=' + label.attr('for') + ']').qtip('destroy');
                },
                errorPlacement: function(error, element) {
                    if ($(error).text()) {
                        if (element.attr('id') == 'specId')
                            element = $('#selectSpec');
                        if (element.attr('id') == 'chairId')
                            element = $('#selectChair');
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
                    level: {
                        required: true
                    },
                    form: {
                        range: [1, 3]
                    },
                    semestrCount: {
                        range: [1, 11]
                    },
                    specId: {
                        required: true
                    },
                    direction: {
                        required: true
                    },
                    termin: {
                        required: true
                    },
                    qualification: {
                        required: true
                    },
                    chairId : {
                        required: true
                    },
                    startYear : {
                        required: true,
                        minlength: 4,
                        maxlength: 4,
                        range: [1990, 2100]
                    },
                    endYear : {
                        required: true,
                        minlength: 4,
                        maxlength: 4,
                        range: [1990, 2100]
                    }
                },
                messages: {
                    level: {
                        required: 'Выберите уровень'
                    },
                    form: {
                        range: 'Выберите форму обучения'
                    },
                    semestrCount: {
                        range: 'Выберите количество семестров'
                    },
                    specId: {
                        required: 'Выберите специальность'
                    },
                    chairId: {
                        required: 'Выберите кафедру'
                    },
                    direction: {
                        required: 'Введите направление'
                    },
                    termin: {
                        required: 'Введите срок обучения'
                    },
                    qualification: {
                        required: " Введите квалификацию"
                    },
                    startYear: {
                        required: "Введите год",
                        minlength: "Должно быть 4 цифры",
                        maxlength: "Должно быть 4 цифры",
                        range: "Значение должно быть в пределать от 1990 до 2100"
                    },
                    endYear: {
                        required: "Введите год",
                        minlength: "Должно быть 4 цифры",
                        maxlength: "Должно быть 4 цифры",
                        range: "Значение должно быть в пределать от 1990 до 2100"
                    }
                }
            });
});