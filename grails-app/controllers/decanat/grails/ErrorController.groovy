package decanat.grails

class ErrorController {

    def not_found = {
        flash.error = "Указанная вами страница не существует";
        render(view: '/error/index')
    }

    def unexpected_error = {
        flash.error = "Произошла непредвиденная ошибка";
        render(view: '/error/index')
    }

    def index = { }
}
