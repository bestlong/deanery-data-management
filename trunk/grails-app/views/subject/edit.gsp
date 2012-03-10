<%--
  Created by IntelliJ IDEA.
  User: corwin
  Date: 13.07.11
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="decanat.grails.Chair" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Редактирование предметов</title>
    <meta name="layout" content="main"/>
</head>

<body>
<script type="text/javascript ">
    $(function () {
        $("input:submit, a, button", ".action").button();
        var myForm = $('form:first');
        myForm.validate({
            onkeyup:false,
            errorClass:'error_field',
            validClass:'valid',
            onsubmit:true,
            success:function (label) {
                $('[id=' + label.attr('for') + ']').qtip('destroy');
            },
            errorPlacement:function (error, element) {
                if ($(error).text())
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
            },
            rules:{
                name:{
                    required:true
                },
                chair:{
                    required:true
                },
                shortName:{
                }
            },
            messages:{
                name:{
                    required:'Имя предмета не может быть пустым'
                },
                chair:{
                    required:'Поле [chair] класса [class decanat.grails.Subject] не может иметь значение null'
                },
                shortName:{
                }
            }
        });
    });
</script>

<div>
    <h4 class="subtitle">Редактировать предмет:</h4>
    <g:form controller="subject" action="update" width="300">
        <table class="editTable" align="center">
            <tr>
                <td class="caption">Код*</td>
                <td>
                    <g:textField name="code" value="${subject?.code}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Кафедра*</td>
                <td>
                    <g:hiddenField name="id" value="${subject.id}"/>
                    <g:select from="${Chair.list()}" optionValue="name" optionKey="id" name="subject.chair"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Имя*</td>
                <td>
                    <g:textField name="name" value="${subject?.name}"/>
                </td>
            </tr>
            <tr>
                <td class="caption">Короткое имя*</td>
                <td>
                    <g:textField name="shortName" value="${subject?.shortName}"/>
                </td>
            </tr>
        </table>
        <br/>

        <div align="center" class="action">
            <g:link controller="subject" action="index">Отмена</g:link>
            <g:submitButton name="save" value="Сохранить"/>
        </div>
    </g:form>
</div>

</body>
</html>