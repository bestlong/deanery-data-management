import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole
import decanat.grails.Faculty

class BootStrap {

    def springSecurityService
    User user

    def init = { servletContext ->
        def prorektorRole = Role.findByAuthority("ROLE_PROREKTOR") ?: new Role(authority: "ROLE_PROREKTOR", description: "Проректор").save()
        def adminRole = Role.findByAuthority("ROLE_DEAN") ?: new Role(authority: "ROLE_DEAN", description: "Декан").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "Секретарь").save()
        Faculty faculty;
        if (0 == Faculty.getCount()){
            faculty = new Faculty(name: "Факультет Электронных Информационных технологий", shortName: "ФЭИТ",prorektor: "prorektor", dean: "admin").save()
        } else {
            faculty = Faculty.findAll().get(0)
        }
        def users = User.list()
        if (!users) {
            user = new User(enabled: true, username: "admin", password: "pass", faculty: faculty).save()
            UserRole.create(user, adminRole, true)
            user = new User(enabled: true, username: "secretary", password: "pass", faculty: faculty).save()
            UserRole.create(user, userRole, true)
            user = new User(enabled: true, username: "prorektor", password: "pass").save()
            UserRole.create(user, prorektorRole, true)
        }
    }

    def destroy = {
        user?.delete()
    }
}
