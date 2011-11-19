import decanat.grails.Role
import decanat.grails.User
//import VersionControl.VersionControl

class BootStrap {

    def authenticateService
    User user
    //VersionControl contr

    def init = { servletContext ->
//        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "").save()
        def adminRole = Role.findByAuthority("ROLE_ADMIN") ?: new Role(authority: "ROLE_ADMIN", description: "Администратор").save()
        def userRole = Role.findByAuthority("ROLE_USER") ?: new Role(authority: "ROLE_USER", description: "Секретарь").save()

        def users = User.list()
        if (!users) {
            user = new User(
                    authorities: [adminRole],
                    email: "tester@mail.com",
                    enabled: true,
                    userRealName: "Real User",
                    username: "admin",
                    passwd: authenticateService.encodePassword("pass"),
                    description: ""
            ).save()
            adminRole.addToPeople(user).save()
//            userRole.addToPeople(user).save()
        }

        //contr.index()
        //VersionControl.index()

    }

    def destroy = {
        user?.delete()
    }
}
