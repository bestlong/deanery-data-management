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
     * else if role == ROLE_DEAN return user.deanery
     * @return
     */
    def getCurrentDeanery(params) {
        Integer deaneryId = null
        def deanery = null
        if (isProrektor()) {
            if (null != params.deanery) {
                deaneryId = params.deanery as Integer
                if (null != deaneryId && 0 != deaneryId) {
                    deanery = Deanery.get(deaneryId)
                }
            }
        } else {
            if (isDean()) {
                def user = getCurrentUser()
                deanery = user.deanery
            }
        }
        deanery
    }

    def getCurrentUser() {
        User.get(springSecurityService.principal.id)
    }
}
