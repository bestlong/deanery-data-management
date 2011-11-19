package decanat.grails

class UserController {

    def userService;
    def authenticateService;

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
                    user.addToAuthorities(role)
                    user.passwd = authenticateService.encodePassword(user.passwd)
                    user.enabled = true
                    user.save();
                    flash.message = message(code: "msg.user.add", args: [user.userRealName])
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
            if (user?.save()) {
                flash.message = message(code: "msg.user.edit", args: [user.userRealName])
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
                user.authorities.toList().get(0).removeFromPeople(user);
                flash.message = message(code: "msg.user.remove", args: [user.userRealName])
                user.delete();
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

