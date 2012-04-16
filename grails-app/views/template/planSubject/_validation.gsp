<g:javascript>
    $(function () {
        $("#tabs").tabs();
        $("#formSave :input[type=text]").numeric({ decimal:false, negative:false }, null);
        $('#searchSubject').hide();
        initTable();
        $("#messages").delay(6000).fadeOut(5 * 400);
        $("#errors").delay(6000).fadeOut(5 * 400);
        $("input:submit, a, button", ".action").button();
        $("#dialog").hide();
        $("#selectSubj").button();
    });

    function initTable() {
        var currId = '';
        $('#subjects').dataTable({
        "bJQueryUI":true,
        "bFilter":false,
        sPaginationType:"full_numbers",
        iDisplayLength:25,
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
        bProcessing:true,
        bServerSide:true,
        sAjaxSource:"${request.contextPath}/addSubjects/table",
            bAutoWidth:false,
            aLengthMenu:[
                [10, 25, 50],
                [10, 25, 50]
            ],
            aoColumns:[
                {
                    "fnRender":function (o, val) {
                        currId = o.aData[0];
                        var res = '';
                        res = res + '<a onclick="chooseSubj('+ o.aData[3]+', &#39;'+ o.aData[0]+'&#39;)" class="delBtn">'+ o.aData[0]+'</a>';
                        return res;
                    }},
                {bSortable:false},
                {},
                {"fnRender":function (o, val) {
                        return '';
                }}
            ]
        });
        var myForm = $('#formSave');
        myForm.validate({
            onkeyup:false,
            errorClass:'error_field',
            validClass:'valid',
            onsubmit:true,
            success:function (label) {
                $('[id=' + label.attr('for') + ']').qtip('destroy');
            },
            errorPlacement:function (error, element) {
                if ($(error).text() != '') {
                    if (element.attr('id') == 'subjId')
                        element = $('#selectSubj');
                    $(element).filter(':not(.valid)').qtip({
                        overwrite:true,
                        content:error,
                        position:{ my:'left center', at:'right center' },
                        show:{
                            event:false,
                            ready:true
                        },
                        hide:false,
                        style:{
                            widget:false,
                            classes:'ui-tooltip-blue ui-tooltip-jtools ui-tooltip-rounded',
                            tip:true
                        }
                    });
                }
            },
            rules:{
                subjId:{
                    required:true
                }
            },
            messages:{

                subjId:{
                    required:'Выберите предмет'
                }
            }
        });
    }

    function showOrHide(show) {
        $('#selectSubj').qtip('destroy');
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