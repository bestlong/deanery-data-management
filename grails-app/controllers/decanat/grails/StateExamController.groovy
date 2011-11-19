package decanat.grails

class StateExamController {
    def planStateExamService
    def index = {
        def plan = Plan.findById(params.id)
        def exam = PlanStateExam.findByPlan(plan)
        if (!exam) {
            exam = new PlanStateExam();
        }
        [stateExam: exam, plan: plan]
    }


    def save = {
        try {
            def exam = planStateExamService.createNewStateExam(params)
            if (exam) {
                flash.message = message(code: "msg.stateExam.save")
            } else {
                flash.error = message(code: "msg.edit.error")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code: "msg.edit.error")
        }
        redirect(action: index, controller: "semestr", id: params.planId)
    }
}
