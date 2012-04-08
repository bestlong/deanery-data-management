import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole

class BootStrap {

    def springSecurityService
    User user

    def init = { servletContext ->
        def prorektorRole = Role.findByAuthority("ROLE_PROREKTOR") ?: new Role(authority: "ROLE_PROREKTOR", description: "Проректор").save()
        def adminRole = Role.findByAuthority("ROLE_DEAN") ?: new Role(authority: "ROLE_DEAN", description: "Декан").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "Секретарь").save()

        def users = User.list()
        if (!users) {
            user = new User(enabled: true, username: "admin", password: "pass").save();
            UserRole.create(user, adminRole, true)
            user = new User(enabled: true, username: "secretary", password: "pass").save();
            UserRole.create(user, userRole, true)
            user = new User(enabled: true, username: "prorektor", password: "pass").save();
            UserRole.create(user, prorektorRole, true)
        }
    }

    def destroy = {
        user?.delete()
    }
}
