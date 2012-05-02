package decanat.grails

import stu.cn.ua.CommonUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class SubjectService {

    static scope = "session"

    static transactional = true

    def sessionParamsService
    def springSecurityService
    def authorityService

    def findSubjects(params, propertiesToRender) {
        Integer maxCount = 25
        Integer offsetPos = 0
        String sort = "desc"
        String orderField="id"
        Integer sortCol = 0
        if (null != params.iDisplayLength){
            maxCount = params.iDisplayLength as Integer
        }
        if (null != params.iDisplayStart){
            offsetPos = params.iDisplayStart as Integer
        }
        if (null != params.sSortDir_0){
            sort = params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc'
        }
        if (null != params.iSortCol_0){
            sortCol = params.iSortCol_0 as Integer
        }
        orderField = propertiesToRender[sortCol]
        def criteria = Subject.createCriteria()
        params = sessionParamsService.loadParams()
        def shortName = params.shortName ?: ""
        def name = params.name ?: ""
        Integer chairId = 0
        if (null != params.chair){
            chairId = params.chair as Integer
        }

        Deanery dean = authorityService.getCurrentDeanery(params)
        if (authorityService.isProrektor()){
            def did=authorityService.getCurrentUser().getDeaneryId()
            if (did!=null)
                dean=Deanery.findById(did)
        }
        def list = criteria.list(max: maxCount, offset: offsetPos) {
            order(orderField, sort)
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
