package decanat.grails

import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole

class UserController {

    def userService;
    def springSecurityService;

    def index = {
        [res: User.list(), active: 2]
    }

    def add = {
    }

    def save = {
        try {
            def user = new User(params);
            if (user.validate()) {
                def role = Role.findById(params.user.role)
                if (!role) {
                    flash.error = message(code: "msg.user.add.role.not.found")
                }
                else {
//                    user.password = springSecurityService.encodePassword(user.password)
                    user.enabled = true
                    user.save();
                    UserRole.create(user, role, true)
                    flash.message = message(code: "msg.user.add", args: [user.username])
                }
            }
            else {
                flash.error = message(code: "msg.user.add.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.user.add.error")
        }
        redirect(action: index, params: params)

    }

    def update = {
        if (params.id) {
            User user = User.findById(params.id)
            user.properties = params
//            user.password = springSecurityService.encodePassword(user.password)
            if (user?.save()) {
                flash.message = message(code: "msg.user.edit", args: [user.username])
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        } else {
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: "index", params: params)
    }


    def delete = {
        if (params.id) {
            User user = User.findById(params.id);
            if (user) {
                Collection<UserRole> userRoles = UserRole.findAllByUser(user)
                userRoles*.delete()
                user.delete()
                flash.message = message(code: "msg.user.remove", args: [user.username])
            }
        }
        else {
            flash.error = message(code: "msg.remove.error")
        }
        redirect(action: index, params: params)
    }

    def search = {
        def res = userService.findUsers(params.login, params.realName, params.email, params.role as int);
        render(template: "/template/user/userList", model: [res: res]);
    }

    def edit = {
        User user = User.findById(params.id);
        [user: user]
    }


}

