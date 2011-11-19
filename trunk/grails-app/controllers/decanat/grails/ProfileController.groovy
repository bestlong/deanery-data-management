package decanat.grails

class ProfileController {

    def authenticateService

    def index = {
        User user = authenticateService.userDomain();
        [user: user, active: 3]
    }

    def update = {
        try {
            if (params.id) {
                User user = User.findById(params.id)
                user.properties = params;
                User userCurrent = authenticateService.userDomain();
                userCurrent.properties = params;
                if (user?.save()) {
                    flash.message = message(code: "msg.profile.edit")
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.edit.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: "index", params: params)
    }

    def edit = {
        try {
            if (params.id) {
                User user = User.findById(params.id)
                def encodedPass = authenticateService.encodePassword(params.newPasswd)  //TODO fix fucking bug
                user.passwd = encodedPass;
                authenticateService.userDomain().passwd = encodedPass;
                if (user?.save()) {
                    flash.message = message(code: "msg.password.edit")
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: "index", params: params)
    }

    def validate = {
        String passwd = params.oldPasswd
        def user = authenticateService.userDomain()
        render user.passwd == authenticateService.encodePassword(passwd) ? "true" : "false"
    }

}
