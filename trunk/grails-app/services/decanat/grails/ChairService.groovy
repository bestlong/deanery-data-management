package decanat.grails

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class ChairService {

    def springSecurityService

    static transactional = true

    List<Chair> findChairs(String code, String name, String shortName) {
        User user = User.get(springSecurityService.principal.id)
        def c = Chair.createCriteria()
        def res = c.listDistinct {
            ilike("codeChair", code)
            ilike("name", name)
            ilike("shortName", shortName)
            if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
                eq("deanery", user.deanery)
            }
        }
        res
    }

    def findChairsForCurrentUser() {
        def res
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            res = Chair.list()
        } else {
            User user = User.get(springSecurityService.principal.id)
            res = Chair.findAllByDeanery(user.deanery)
        }
        res
    }
}
