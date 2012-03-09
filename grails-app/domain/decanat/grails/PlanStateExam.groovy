package decanat.grails

class PlanStateExam {


    Plan plan;
    String date;
    String form;
    int semestr;

    static belongsTo = [plan: Plan]

    static PlanStateExam createNew(PlanStateExam planStateExam, Plan p){
        PlanStateExam newPlanStateExam = new PlanStateExam()
        newPlanStateExam.date = planStateExam.date
        newPlanStateExam.form = planStateExam.form
        newPlanStateExam.semestr = planStateExam.semestr
        newPlanStateExam.plan = p

        newPlanStateExam
    }

    def beforeInsert = {
        plan.lastUpdated = new Date()
    }
    def beforeUpdate = {
        plan.lastUpdated = new Date()

    }
    def beforeDelete = {
        plan.lastUpdated = new Date()

    }
    static constraints = {

        plan(nullable: false)
        date(blank: false)
        form(blank: false)
        semestr(blank: false)
    }
}
