package decanat.grails

class WorkPlan extends Plan{
    
    String name
    Plan plan

    WorkPlan() {
    }

    WorkPlan(String name, Plan plan) {
        this.name = name
        this.plan = copyPlan(plan)
    }

    static mapping = {
        discriminator value: "work"
    }
    static constraints = {
        name(blank: false)
        plan(nullable: false)
    }
    
    private Plan copyPlan(Plan p){
        WorkPlan newPlan = new WorkPlan()
        newPlan.speciality = p.speciality
        newPlan.chair = p.chair
        newPlan.direction = p.direction
        newPlan.level = p.level
        newPlan.form = p.form
        newPlan.termin = p.termin
        newPlan.qualification = p.qualification
        newPlan.semestrCount = p.semestrCount
        semestr.each {
            newPlan.addToSemestr(Semestr.createNew(it))
        }
        subjects.each {
            newPlan.addToSubjects(PlanSubject.createNew(it))
        }
        practise.each {
            newPlan.addToPractise(PlanPractice.createNew(it))
        }
        newPlan.stateExam = PlanStateExam.createNew(newPlan.stateExam)

        newPlan
    }
}
