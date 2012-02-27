package decanat.grails

import ua.cn.decanat.plan.exception.RoleNotFoundException
import decanat.grails.domain.User

class UserService {

    static transactional = true

    String pass
    String c_pass

    List<User> findUsers(String username, String userRealName, String email, int role) {
        if (role == null)
            throw new RoleNotFoundException(role);

        def c = User.createCriteria()

        def users  = c.listDistinct{
            ilike("userRealName", "%" + userRealName + "%");
            ilike("username", "%" + username + "%");
            ilike("email", "%" + email + "%");
            authorities{
                if (role != 0)
                    eq ("id", role.longValue())
            }
        }

        return users;
    }

}
