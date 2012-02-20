package decanat.grails

import stu.cn.ua.enums.PlanWayCreation
import stu.cn.ua.enums.PlanClass

class PlanCreationController {

    def index = {}

    def next = {
//        try {
            PlanClass planType = params.planType
            if (PlanClass.STUDY.equals(planType)){
                redirect(action: "index", controller: "selectSpeciality")
            } else {
                PlanWayCreation planWayCreation = params.planWayCreation
                if (PlanWayCreation.STANDARD_CONSTRUCTOR.equals(planWayCreation)){
                    WorkPlan plan = new WorkPlan(name: params.planName)
                    plan.save(flush:  true)
                    redirect(action: "index", controller: "selectSpeciality", id: plan.id)
                } else {
                    if (PlanWayCreation.FROM_WORK_PLAN.equals(planWayCreation)){

                    } else {
                        if (PlanWayCreation.FROM_STUDY_PLAN.equals(planWayCreation)){
                            Plan plan = Plan.findById(params.baseStudyPlan as long)
                            WorkPlan workPlan = new WorkPlan("new work plan", plan)
                            workPlan.save()
                            flash.message = message(code: "msg.plan.work.successfully.added")
                            redirect(action: "index", controller: "index", params: params)
                        }
                        else {
                            flash.error = message(code: "error.plan.unknown.way.creation")
                        }
                    }
                }
            }

//        } catch (Exception e){
//
//        }
    }
}
