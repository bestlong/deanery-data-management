import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole

class BootStrap {

    def springSecurityService
    User user

    def init = { servletContext ->
        def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority: "ROLE_ADMIN", description: "Администратор").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "Секретарь").save()

        def users = User.list()
        if (!users) {
            user = new User(enabled: true, username: "admin", password: "pass").save();
            UserRole.create(user, adminRole, true)
        }
    }

    def destroy = {
        user?.delete()
    }
}
