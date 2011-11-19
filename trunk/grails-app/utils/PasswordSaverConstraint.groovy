
/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 08.07.11
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
class PasswordSaverConstraint {

    def userService

    static name ="saver"
    static defaultMessageCode = "default.not.social.message"

    def validate = { val ->
        userService.pass = val
        return val == userService.c_pass
    }
}
