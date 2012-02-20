package decanat.grails

import stu.cn.ua.enums.PlanClass

class IndexController {

    def planService

    def chairPlans = {
        def chair = Chair.get(params.id)
        def plans = Plan.findAllByChair(chair, [sort: 'lastUpdated', order: 'desc'])
        plans.each {
            def useless = it.speciality.kod
        }
        chain(action: 'index', model: [res: plans, msg: "Список учебных планов кафедры '${chair.name}'"])
    }


    def index = {
        def planList
        def msg = chainModel?.msg ?: ""
        def totalPlans = planService.findByDiscriminatorCount(PlanClass.STUDY)
        if (chainModel?.res == null)
            if (params.offset == null) {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, 4, 0)
            }
            else {
                planList = planService.findByDiscriminatorForPaginating(PlanClass.STUDY, params.max, params.offset)
            }
        else{
            planList = chainModel.res
            totalPlans = planList.size()
        }
        [res: planList, totalPlans: totalPlans, univer: University.list(), active: 1, msg: msg]
    }

    def delete = {
        try {
            if (params.id) {
                Plan plan = Plan.get(params.id);
                if (plan) {
                    plan.delete(flush: true);
                    flash.message = message(code: "msg.plan.remove.success")
                }
            }
            else {
                flash.error = message(code: "msg.plan.remove.error")
            }
        }
        catch (Exception e) {
            flash.error = message(code: "msg.plan.remove.error")
            log.error(e.getMessage(), e)
        }
        redirect(action: index)
    }
}
