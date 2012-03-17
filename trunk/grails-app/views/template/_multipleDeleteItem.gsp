<g:javascript>
    function selectUnselectAll() {
        var totalSize = $("input[type=checkbox]").length;
        var size = $("input[type=checkbox]:checked").length;

        if (size != totalSize) {
            $('input[name*=multipleDelete]').attr('checked', true);
            $('[name=itemTr]').css('background-color', '#edf511');
        }
        else {
            $('input[name*=multipleDelete]').attr('checked', false);
            $('[name=itemTr]').removeAttr('style');
        }
        hideDeleteMultiple();
    }
    function changeBackground(id) {
        if ($('#multipleDelete' + id).attr('checked')) {
            $('#tr' + id).css('background-color', '#edf511');
        } else {
            $('#tr' + id).removeAttr('style');
        }
        hideDeleteMultiple();
    }
    function hideDeleteMultiple() {
        var size = $("input[type=checkbox]:checked").length;
        if (size == 0) {
            $('#multipleDelete').hide();
        }
        else {
            $('#multipleDelete').show();
        }
    }
</g:javascript>
<g:render template="/template/multipleDeleteConfirmation"/>
<g:hiddenField name="id" value="-1"/>