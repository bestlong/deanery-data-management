package decanat.grails

import stu.cn.ua.CommonUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class ChairService {
    def sessionParamsService
    def springSecurityService
    def authorityService

    static transactional = true
    static scope = "session"

//    List<Chair> findChairs(String code, String name, String shortName) {
    //        User user = User.get(springSecurityService.principal.id)
    //        def c = Chair.createCriteria()
    //        def res = c.listDistinct {
    //            ilike("codeChair", code)
    //            ilike("name", name)
    //            ilike("shortName", shortName)
    //            if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
    //                eq("deanery", user.deanery)
    //            }
    //        }
    //        res
    //    }

    def findChairsForCurrentUser() {
        def res
        if (authorityService.isProrektor()) {
            res = Chair.list()
        } else {
            User user = User.get(springSecurityService.principal.id)
            res = Chair.findAllByDeanery(user.deanery)
        }
        res
    }

    def findChairs(params, propertiesToRender) {
        Integer maxCount = 25
        Integer offsetPos = 0
        String sort = "desc"
        String orderField = "id"
        Integer sortCol = 0
        if (null != params.iDisplayLength) {
            maxCount = params.iDisplayLength as Integer
        }
        if (null != params.iDisplayStart) {
            offsetPos = params.iDisplayStart as Integer
        }
        if (null != params.sSortDir_0) {
            sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        }
        if (null != params.iSortCol_0) {
            sortCol = params.iSortCol_0 as Integer
        }
        orderField = propertiesToRender[sortCol]

        def criteria = Chair.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName ?: ""
        def name = params?.name ?: ""
        def codeChair = params?.code ?: ""
        Deanery dean = authorityService.getCurrentDeanery(params)

        def list = criteria.list(max: maxCount, offset: offsetPos) {
            order(orderField, sort)
            if (params) {
                if (null != shortName && !shortName.equals("")) {
                    ilike("shortName", "%" + shortName + "%");
                }
                if (null != name && !name.equals("")) {
                    ilike("name", "%" + name + "%");
                }
                if (null != codeChair && !codeChair.equals("")) {
                    ilike("codeChair", "%" + codeChair + "%");
                }
            }
            if (null != dean) {
                eq("deanery", dean)
            }
        }
        list
    }
}
