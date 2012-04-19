package decanat.grails

import decanat.grails.domain.User

class ProfileController {

    def springSecurityService

    def index = {
        User user = User.get(springSecurityService.principal.id)
        [user: user, active: 3]
    }
//
//    def update = {
//        try {
//            if (params.id) {
//                User user = User.findById(params.id)
//                user.properties = params;
//                User userCurrent = User.get(springSecurityService.principal.id)
//                userCurrent.properties = params;
//                if (user?.save()) {
//                    flash.message = message(code: "msg.profile.edit")
//                } else {
//                    flash.error = message(code: "msg.edit.error")
//                }
//            } else {
//                flash.error = message(code: "msg.edit.error")
//            }
//        }
//        catch (Exception e) {
//            flash.error = message(code: "msg.edit.error")
//            log.error(e.getMessage(), e)
//        }
//        redirect(action: "index", params: params)
//    }

    def edit = {
        flash.message = message(code: "tooltip.plan.expand.work.plans")
        try {
            User user = User.get(springSecurityService.principal.id)
            user.password = params.newPasswd
            if (user?.save()) {
                flash.message = message(code: "msg.password.edit")
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
        def user = User.get(springSecurityService.principal.id)
        render user.password == springSecurityService.encodePassword(passwd) ? "true" : "false"
    }

}
