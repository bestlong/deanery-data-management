<g:javascript>
    function selectUnselectAll() {
        var totalSize = $("input[type=checkbox]").length;
        var size = $("input[type=checkbox]:checked").length;

        if (size != totalSize) {
            $('input[name*=multipleDelete]').attr('checked', true);
            $('[name=subjectTr]').css('background-color', '#e3a345');
        }
        else {
            $('input[name*=multipleDelete]').attr('checked', false);
            $('[name=subjectTr]').removeAttr('style');
        }
        hideDeleteMultiple();
    }
    function changeBackground(id) {
        if ($('#multipleDelete' + id).attr('checked')) {
            $('#tr' + id).css('background-color', '#e3a345');
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