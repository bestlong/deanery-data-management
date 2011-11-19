package decanat.grails

class PracticeController {

    def practiceService

    def index = {
        def plan = Plan.get(params.id)
        def res = PlanPractice.findAllByPlan(plan)
        [res: res, plan: plan]
    }

    def add = {
        def plan = Plan.get(params.id)
        [res: PlanPractice.list(), plan: plan]
    }
    def save = {
        def plan
        try {
            plan = Plan.findById(params.planId)
            def practice
            if (!params.id) {
                practice = new PlanPractice()
            }
            else {
                practice = PlanPractice.findById(params.id)
            }
            practice.plan = plan
            practice.properties = params
            if (practice.save(flush: true)) {
                flash.message = message(code: "msg.practice.save")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: index, id: plan.id)
    }


    def delete = {
        try {
            if (params.id) {
                PlanPractice practice = PlanPractice.get(params.id);
                if (practice) {
                    practice.delete(flush: true);
                    flash.message = message(code: "msg.planPractice.remove.success")
                }
            }
            else {
                flash.error = message(code: "msg.planPractice.remove.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.planPractice.remove.error")
        }
        redirect(action: index, id: params.planId)
    }

    def edit = {
        def plan = Plan.get(params.planId)
        def practice = PlanPractice.get(params.id)
        [plan: plan, practice: practice]
    }
}
