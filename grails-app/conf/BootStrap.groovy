import decanat.grails.domain.User
import decanat.grails.domain.Role
import decanat.grails.domain.UserRole
import decanat.grails.Deanery

class BootStrap {

    def springSecurityService
    User user

    def init = { servletContext ->
        def prorektorRole = Role.findByAuthority("ROLE_PROREKTOR") ?: new Role(authority: "ROLE_PROREKTOR", description: "Проректор").save()
        def adminRole = Role.findByAuthority("ROLE_DEAN") ?: new Role(authority: "ROLE_DEAN", description: "Декан").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "Секретарь").save()
        Deanery deanery;
        if (0 == Deanery.getCount()){
            deanery = new Deanery(name: "Факультет Электронных Информационных технологий", shortName: "ФЭИТ",prorektor: "prorektor", dean: "admin").save()
        } else {
            deanery = Deanery.findAll().get(0)
        }
        def users = User.list()
        if (!users) {
            user = new User(enabled: true, username: "admin", password: "pass", deanery: deanery).save()
            UserRole.create(user, adminRole, true)
            user = new User(enabled: true, username: "secretary", password: "pass", deanery: deanery).save()
            UserRole.create(user, userRole, true)
            user = new User(enabled: true, username: "prorektor", password: "pass").save()
            UserRole.create(user, prorektorRole, true)
        }
    }

    def destroy = {
        user?.delete()
    }
}
