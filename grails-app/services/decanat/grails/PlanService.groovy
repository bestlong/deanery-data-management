package decanat.grails

import stu.cn.ua.enums.PlanClass

class PlanService {

    static transactional = true

    def findByDiscriminatorForPaginating(PlanClass planClass, int max, int offset) {
        def c = Plan.createCriteria()
        c.list{
            maxResults (max)
            firstResult(offset)
            order("lastUpdated", "desc")
            eq("class", planClass.name())

        }
    }

    def findByDiscriminatorCount(PlanClass planClass) {
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", planClass.name())
        }
        list.size()
    }

    def findWorkPlansByStudyPlan(Plan plan){
        def c = Plan.createCriteria()
        def list = c.list(){
            eq("class", PlanClass.WORK.name())
            eq("plan", plan)
        }
        list
    }
}
