/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 08.07.11
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
class ConfirmPasswordConstraint {

    def userService

    static name ="confirm"
    static defaultMessageCode = "default.not.confirm.message"

    def validate = { val ->
        userService.c_pass = val
        return userService.pass == val
    }
}
