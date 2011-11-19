package decanat.grails

class ErrorController {

    def not_found = {
        flash.error = "Указанная вами страница не существует";
        redirect(action: 'index')
    }

    def unexpected_error = {
        flash.error = "Произошла непредвиденная ошибка";
        redirect(action: 'index')
    }

    def index = { }
}
