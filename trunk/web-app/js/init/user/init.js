$(function() {
    $("input:submit, a, button", ".action").button();
});

$(function() {
    var myForm = $('form:first');
    myForm.validate({
                onkeyup: false,
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
                    description: {
                        required: true
                    },
                    passwdRetype: {
                        equalTo: "#passwd"
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
                    passwdRetype: {
                        equalTo: "Пароль и подтверждение должны совпадать"
                    },
                    userRealName: {
                        required: 'ФИО не может быть пустым'
                    }
                }
            });
});
