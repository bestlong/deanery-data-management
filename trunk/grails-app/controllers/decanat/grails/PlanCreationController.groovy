package decanat.grails

import stu.cn.ua.enums.PlanWayCreation
import stu.cn.ua.enums.PlanClass

class PlanCreationController {

    def index = {}

    def next = {
        try {
            PlanClass planType = params.planType
            if (PlanClass.STUDY.equals(planType)) {
                redirect(action: "index", controller: "planInit")
            } else {
                PlanWayCreation planWayCreation = params.planWayCreation
                if (PlanWayCreation.STANDARD_CONSTRUCTOR.equals(planWayCreation)) {
                    Plan plan = Plan.findById(params.baseStudyPlan as long)
                    WorkPlan wPlan = new WorkPlan(name: params.planName, plan: plan)
                    wPlan.save(flush: true)
                    redirect(action: "index", controller: "planInit", id: wPlan.id)
                } else {
                    if (PlanWayCreation.FROM_WORK_PLAN.equals(planWayCreation)) {
                        WorkPlan plan = WorkPlan.findById(params.baseWorkPlan as long)
                        WorkPlan workPlan = WorkPlan.createFromWorkPlan(params.planName, plan)
                        workPlan.save()
                        flash.message = message(code: "msg.plan.work.successfully.added")
                        redirect(action: "index", controller: "index", params: params)
                    } else {
                        if (PlanWayCreation.FROM_STUDY_PLAN.equals(planWayCreation)) {
                            Plan plan = Plan.findById(params.baseStudyPlan as long)
                            WorkPlan workPlan = WorkPlan.createFromStudyPlan(params.planName, plan)
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
        }
        catch (Exception e) {
            flash.error = message(code: "error.plan.creation")
            log.error(e.getMessage(), e)
        }
    }
}
