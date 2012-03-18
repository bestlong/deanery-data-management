package decanat.grails

import stu.cn.ua.enums.PlanForm

class PlanInitService {

    static transactional = true

    def savePlan(params) {
        Plan plan;
        if (!params.id || params.id == "") {
            plan = new Plan()
        }
        else {
            plan = Plan.findById(params.id)
            if (null != plan.chair){
                plan.chair?.referenceCount--
            }
            if (null != plan.speciality){
                plan.speciality?.referenceCount--
            }
        }

        plan.properties = params
        switch (plan.form as int) {
            case PlanForm.DAILY.number:
                plan.form = PlanForm.DAILY.name;
                break;
            case PlanForm.EVENING.number:
                plan.form = PlanForm.EVENING.name;
                break;
            case PlanForm.EXTRAMURAL.number:
                plan.form = PlanForm.EXTRAMURAL.name;
                break;
        }
        plan.speciality = Speciality.findById(params.specId)
        plan.chair = Chair.findById(params.chairId)
        plan.chair?.referenceCount++
        plan.speciality?.referenceCount++
        plan.save(flush: true)

        return plan;
    }
}
