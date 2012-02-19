package decanat.grails

import stu.cn.ua.enums.PlanType
import stu.cn.ua.enums.PlanWayCreation

class PlanCreationController {

    def index = {}

    def next = {
//        try {
            PlanType planType = params.planType
            if (PlanType.STUDY.equals(planType)){
                
            } else {
                PlanWayCreation planWayCreation = params.planWayCreation
                if (PlanWayCreation.STANDARD_CONSTRUCTOR.equals(planWayCreation)){
                    
                } else {
                    if (PlanWayCreation.FROM_WORK_PLAN.equals(planWayCreation)){

                    } else {
                        if (PlanWayCreation.FROM_STUDY_PLAN.equals(planWayCreation)){
                            Plan plan = Plan.findById(params.baseStudyPlan as long)
                            WorkPlan workPlan = new WorkPlan("new work plan", plan)
                            workPlan.save()
                            redirect(action: index, controller: index, params: params)
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
