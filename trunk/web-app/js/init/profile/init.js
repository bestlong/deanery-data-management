$(function() {
    $("#messages").delay(6000).fadeOut(5 * 400);
    $("#errors").delay(6000).fadeOut(5 * 400);
    $("input:submit, a, button", ".action").button();
    $("#dialog").hide()
});

function deleteDialog(iid) {
    $("#editPass").attr("href", '/decanat-grails/user/edit/' + iid);
    $("#dialog").dialog();
}

$(function() {
    var myForm = $('#profile');
    myForm.validate({
                errorClass: 'error_field',
                validClass: 'valid',
                onsubmit: true,
                success: function(label) {
                    $('[id=' + label.attr('for') + ']').qtip('destroy');
                },
                errorPlacement: function(error, element) {
                    if ($(error).text())
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
                },
                rules: {
                    passwd: {
                        required: true,
                        minlength: 4
                    },
                    username: {
                        required: true,
                        unique: {
                            url: '/decanat-grails/JQueryRemoteValidator/validate',
                            type: 'post',
                            data: {
                                validatableClass: 'decanat.grails.User',
                                property: 'username',
                                id: $('input:hidden#id').length ? $('input:hidden#id').val() : '0'
                            }
                        }
                    },
                    email: {
                        email: true,
                        required: true
                    },
                    userRealName: {
                        required: true
                    }
                },
                messages: {
                    passwd: {
                        required: 'Пароль не может быть пустым',
                        minlength: function() {
                            return 'Слишком короткий пароль';
                        }
                    },
                    username: {
                        required: 'Логин не может быть пустым',
                        unique: function() {
                            return 'Пользователь с таким логином уже существует';
                        }
                    },
                    email: {
                        email: function() {
                            return 'Поле E-mail заполнено неправильно';
                        },
                        required: 'Email не может быть пустым'
                    },
                    userRealName: {
                        required: 'ФИО не может быть пустым'
                    }
                }
            });
});

$('#editPasswd').validate({
            onkeyup: false,
            errorClass: 'validation_error',
            validClass: 'valid',
            onsubmit: true,
            rules: {
                oldPasswd: {
                    required: true,
                    remote: {
                        url: '/decanat-grails/profile/validate',
                        type: 'post',
                        data: {
                            oldPasswd: function() {
                                return $("#oldPasswd").val();
                            }
                        }
                    }
                },
                newPasswd: {
                    required: true,
                    minlength: 4
                },
                confirmPasswd: {
                    equalTo: "#newPasswd"
                }
            },
            messages: {
                oldPasswd: {
                    required: "Введите старый пароль",
                    remote: 'Старый пароль введён неправильно'
                },
                confirmPasswd: {
                    equalTo: 'Пароль и подтвержение должны совпадать'
                },
                newPasswd: {
                    required: "Введите новый пароль",
                    minlength: "Слишком короткий пароль"
                }
            }
        });