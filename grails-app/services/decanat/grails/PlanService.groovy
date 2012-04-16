package decanat.grails

import stu.cn.ua.enums.PlanClass
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import decanat.grails.domain.User

class PlanService {

    def springSecurityService

    static transactional = true

    def findByDiscriminatorForPaginating(PlanClass planClass, int max, int offset) {
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
    }

    def findByDiscriminatorCount(PlanClass planClass) {
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", planClass.name())
            order("lastUpdated", "desc")
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
        list.size()
    }

    def findWorkPlansByStudyPlan(Plan plan){
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.WORK.name())
            eq("plan", plan)
            order("lastUpdated", "desc")
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
        list
    }

    def findStudyPlansByChair(Chair chair){
        def  deanery = getDeanery()
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.STUDY.name())
            eq("chair", chair)
            order("lastUpdated", "desc")
            if (null != deanery) {
                speciality{
                    eq("deanery", deanery)
                }
            }
        }
        list
    }

    private def getDeanery(){
        User user = User.get(springSecurityService.principal.id)
        Deanery dean = null
        if (SpringSecurityUtils.ifAnyGranted("ROLE_DEAN")) {
            dean = user.deanery
        }
        dean
    }
}
