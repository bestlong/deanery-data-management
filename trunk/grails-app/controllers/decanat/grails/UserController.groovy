package decanat.grails

import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole
import stu.cn.ua.enums.Roles

class UserController {

    def userService;
    def springSecurityService;

    def index = {
        [res: userService.findUsersForCurrentUser(), roles: userService.findRolesForSearch(), active: 2]
    }

    def add = {
        [roles: userService.findRolesForSearch()]
    }

    def save = {
        try {
            def user = new User(params);
            def facultyId = params.facultyId as Integer
            def faculty = null
            if (user.validate()) {
                def role = Role.findById(params.roleId)
                if (!role) {
                    flash.error = message(code: "msg.user.add.role.not.found")
                }
                else {
                    if (null != facultyId && 0 != facultyId) {
                        faculty = Deanery.get(facultyId)
                    }
                    if (role?.description == Roles.PROREKTOR.text) {
                        faculty = null
                    }
                    user.deanery = faculty
                    user.enabled = true
                    user.save();
//                    UserRole.removeAll(user)
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
            def facultyId = params.facultyId as Integer
            def faculty = null
            User user = User.findById(params.id)
            user.properties = params
            def role = Role.findById(params.roleId)
            if (!role) {
                flash.error = message(code: "msg.user.add.role.not.found")
            }
            else {
                if (null != facultyId && 0 != facultyId) {
                    faculty = Deanery.get(facultyId)
                }
                if (role?.description == Roles.PROREKTOR.text) {
                    faculty = null
                }
                user.deanery = faculty
                UserRole.removeAll(user)
                UserRole.create(user, role, true)
                if (user?.save()) {
                    flash.message = message(code: "msg.user.edit", args: [user.username])
                } else {
                    flash.error = message(code: "msg.edit.error")
                }
            }
            //user.password = springSecurityService.encodePassword(user.password)

        } else {
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: "index", params: params)
    }


    def delete = {
        if (params.id) {
            User tekUser = User.get(springSecurityService.principal.id)
            User user = User.findById(params.id);
            if (user.deaneryId!=tekUser.deaneryId){
                flash.error = message(code: "msg.DeaneryId.error")
            }else{
                if (user) {
                    Collection<UserRole> userRoles = UserRole.findAllByUser(user)
                    userRoles*.delete()
                    user.delete()
                    flash.message = message(code: "msg.user.remove", args: [user.username])
                }
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
        User tekUser = User.get(springSecurityService.principal.id)
        if (user.deaneryId==tekUser.deaneryId){
            [user: user, roles: userService.findRolesForSearch(), role: user.getAuthorities().toArray()[0]]
        }else{
            flash.error = message(code: "msg.DeaneryId.error")
            redirect(action: index, params: params)
        }
    }

    def validate = {
        String passwd = params.oldPasswd
        def user = User.get(springSecurityService.principal.id)
        render user.password == springSecurityService.encodePassword(passwd) ? "true" : "false"
    }

    def changePass = { /*TODO Rule for changed user password page _editPasword,
    in page "Edit" for UserController create new dialog window for entered new password, and in init.js for user created rule for validation
    for editPasswd form named*/
        if (params.userId) {
            try {
                User user = User.findById(params.userId);
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
        }
        redirect(action: "index", params: params)
    }
}

