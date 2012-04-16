package decanat.grails

import stu.cn.ua.CommonUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class SubjectService {

    static scope = "session"

    static transactional = true

    def sessionParamsService
    def springSecurityService

    def findSubjects(params, propertiesToRender) {
        User user = User.get(springSecurityService.principal.id)
        int maxCount = params.iDisplayLength as Integer ?: 25
        int offsetPos = params.iDisplayStart as Integer ?: 0
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as Integer ?: 0]
        def criteria = Subject.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName ?: ""
        def name = params?.name ?: ""
        long chairId = 0
        try {
            chairId = params?.chair as long
        } catch (Exception e) {}

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
                if (chairId != 0) {
                    chair {
                        eq("id", chairId)
                    }
                }
                ilike("shortName", "%" + shortName + "%");
                ilike("name", "%" + name + "%");
            }
            if (null != dean) {
                eq("deanery", dean)
            }
        }
        list
    }

    def findSubjectsCount(params, propertiesToRender) {
        User user = User.get(springSecurityService.principal.id)
        def sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        def orderField = propertiesToRender[params.iSortCol_0 as int]
        def criteria = Subject.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params?.shortName
        def name = params?.name
        long chairId = 0
        try {
            chairId = params?.chair as long
        } catch (Exception e) {}

        def count = criteria.list {
            if (!orderField.equals("") && !sort.equals("")) {
                order(orderField, sort)
            }
            if (params) {
                println params?.chair && params?.chair != 0
                println params?.chair
                if (chairId != 0) {
                    chair {
                        if (chairId == -1) {
                            eq("id", null)
                        }
                        else {
                            eq("id", chairId as long)
                        }
                    }
                }
                if (name && !name.equals("")) {
                    ilike("shortName", "%" + name + "%");
                }
                if (shortName && !shortName.equals("")) {
                    ilike("name", "%" + shortName + "%");
                }
                projections {
                    countDistinct("id")
                }
            }
            if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
                eq("deanery", user.deanery)
            }
            if (SpringSecurityUtils.ifAnyGranted("ROLE_PROREKTOR")) {
                if (0 != params.deanery) {
                    Deanery deanery = Deanery.get(params.deanery as int)
                    eq("deanery", deanery)
                }
            }
        }
        count
    }

    def updateSubject(Subject subject, def params) {
        subject.properties = params
        if (null != subject.chair) {
            subject.chair?.referenceCount--
        }
        subject.chair = Chair.findById(params.subject.chair);
        if (null != subject.chair) {
            subject.chair?.referenceCount++
        }
        subject.name = CommonUtils.prepareString(subject.name)
        return subject.save()
    }

    List<Subject> findSubjects(Integer chairId, String name, String shortName) {
        def c = Subject.createCriteria()
        def subjects = c.listDistinct {
            ilike("name", "%" + name + "%");
            order("name", "asc")
            if (!shortName.equals(""))
                ilike("shortName", "%" + shortName + "%");
            if (chairId != 0) {
                chair {
                    if (chairId == -1) {
                        eq("id", null)
                    }
                    else {
                        eq("id", chairId.longValue())
                    }
                }
            }
        }
        return subjects;
    }
}
