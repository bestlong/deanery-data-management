package decanat.grails

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class AuthorityService {

    def springSecurityService

    def isProrektor() {
        return SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")
    }

    def isDean() {
        return SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")
    }

    def isUser() {
        return SpringSecurityUtils.ifAnyGranted("ROLE_USER")
    }

    /**
     * if role == ROLE_PROREKTOR return role from params or 0
     * else if role == ROLE_DEAN return user.faculty
     * @return
     */
    def getCurrentFaculty(params) {
        Integer facultyId = null
        def faculty = null
        if (isProrektor()) {
            if (null != params.faculty) {
                facultyId = params.faculty as Integer
                if (null != facultyId && 0 != facultyId) {
                    faculty = Faculty.get(facultyId)
                }
            }
        } else {
            if (isDean()) {
                def user = getCurrentUser()
                faculty = user.faculty
            }
        }
        faculty
    }

    def getCurrentUser() {
        User.get(springSecurityService.principal.id)
    }
}
