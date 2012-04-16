package decanat.grails

import stu.cn.ua.CommonUtils

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class SpecialityService {

    def sessionParamsService
    def springSecurityService

    static transactional = true
    static scope = "session"

    List<Speciality> findSpecialities(String code, String specialityCode, String name, String shortName) {
        def c = Speciality.createCriteria()
        def specialities = c.listDistinct {
            ilike("code", "%" + code + "%")
            ilike("name", "%" + name + "%")
            ilike("specialityCode", "%" + specialityCode + "%");
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
        }
        return specialities;
    }

    def findSpeciality(params, propertiesToRender) {
        User user = User.get(springSecurityService.principal.id)
        int maxCount = params.iDisplayLength as Integer ?: 25
        int offsetPos = params.iDisplayStart as Integer ?: 0
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as Integer ?: 0]
        def criteria = Speciality.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName
        def name = params?.name
        def code = params?.code
        def specialityCode= params?.specialityCode

        Deanery dean = null
        if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
            Integer deaneryId = params.deanery as Integer ?:0
            if (0 != deaneryId) {
                dean = Deanery.get(params.deanery as Integer)
            }
        }
        if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
            dean = user.deanery
        }

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
                if (code && !code.equals("")) {
                    ilike("code", "%" + code + "%");
                }
                if (specialityCode && !specialityCode.equals("")) {
                    ilike("specialityCode", "%" + specialityCode + "%");
                }
            }
            if (null != dean) {
                eq("deanery", dean)
            }
        }
        list
    }


}
