package decanat.grails

import stu.cn.ua.CommonUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class ChairService {
    def sessionParamsService
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





    def findChairs(params, propertiesToRender) {

        int maxCount = params.iDisplayLength as Integer ?: 25
        int offsetPos = params.iDisplayStart as Integer ?: 0
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as Integer ?: 0]
        def criteria = Chair.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName
        def name = params?.name
        def codeChair = params?.code


       def list = criteria.list(max: maxCount, offset: offsetPos) {
            if (!orderField.equals("") && !sort.equals("")) {
                order(orderField, sort)
            }
            if (params) {
                if (shortName && !shortName.equals("")) {
                    ilike("shortName", "%" + shortName + "%");
                }
                if (name && !name.equals("")) {
                    ilike("name", "%" + name + "%");
                }
                if (codeChair && !codeChair.equals("")) {
                    ilike("codeChair", "%" + codeChair + "%");
                }
            }
        }
        list
    }

}
