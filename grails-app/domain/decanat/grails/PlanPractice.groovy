package decanat.grails

class PlanPractice {
    String name;
    Plan plan;
    int semestr;
    int weeks;

    static belongsTo = [plan: Plan]

    static PlanPractice createNew(PlanPractice planPractice){
        PlanPractice newPlanPractice = new PlanPractice()
        newPlanPractice.name = planPractice.name
        newPlanPractice.semestr = planPractice.semestr
        newPlanPractice.weeks = planPractice.weeks

        newPlanPractice
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
        name(blank: false)
        plan(nullable: false)

        semestr(min: 0)
        weeks(min: 0)
    }
}
