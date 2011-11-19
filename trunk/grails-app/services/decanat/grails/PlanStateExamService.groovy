package decanat.grails

class PlanStateExamService {

    static transactional = true

    def createNewStateExam(params) {
        def plan = Plan.findById(params.planId)
        def exam = PlanStateExam.findByPlan(plan)
        if (!exam) {
            exam = new PlanStateExam();
        }

        exam.properties = params
        exam.plan = plan;

        return exam.save(flush:true);

    }
}
