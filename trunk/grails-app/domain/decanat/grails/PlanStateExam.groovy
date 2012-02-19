package decanat.grails

class PlanStateExam {


    Plan plan;
    String date;
    String forma;
    int semestr;

    static belongsTo = [plan: Plan]

    static PlanStateExam createNew(PlanStateExam planStateExam, Plan p){
        PlanStateExam newPlanStateExam = new PlanStateExam()
        newPlanStateExam.date = planStateExam.date
        newPlanStateExam.forma = planStateExam.forma
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
        forma(blank: false)
        semestr(blank: false)
    }
}
