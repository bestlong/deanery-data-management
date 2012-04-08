package decanat.grails

import ua.cn.decanat.plan.exception.RoleNotFoundException
import decanat.grails.domain.User
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.Role

class UserService {

    static transactional = true

    String pass
    String c_pass

    def springSecurityService

    def findRolesForSearch() {
        def res = []
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            res = Role.list()
        } else {
            res.add(Role.findByAuthority("ROLE_USER"))
            res.add(Role.findByAuthority("ROLE_DEAN"))
        }
        res
    }

    List<User> findUsers(String username, String userRealName, String email, int role) {
        if (role == null) {
            throw new RoleNotFoundException(role: role);
        }
        def c = User.createCriteria()
        User user = User.get(springSecurityService.principal.id)       
        def users = c.listDistinct {
            ilike("username", "%" + username + "%")
            userRoles {
                if (role != 0) {
                    def roleDomain = Role.get(role)
                    eq("role", roleDomain)
                }
            }
            if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
                eq("deanery", user.deanery)
            }
        }
        return users;
    }

    def findUsersForCurrentUser() {
        def res
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            res = User.list()
        } else {
            User user = User.get(springSecurityService.principal.id)
            res = User.findAllByDeanery(user.deanery)
        }
        res
    }
}
