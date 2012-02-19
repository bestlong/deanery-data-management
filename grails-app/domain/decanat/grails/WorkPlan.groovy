package decanat.grails

class WorkPlan extends Plan{
    
    String name

    WorkPlan() {
    }

    WorkPlan(String name) {
        this.name = name
    }

    WorkPlan(String name, Plan plan) {
        this.name = name
        copyPlan(plan)
    }

    static mapping = {
        discriminator value: "work"
    }
    static constraints = {
        name(nullable: true)
    }
    
    private void copyPlan(Plan p){
        speciality = p.speciality
        chair = p.chair
        direction = p.direction
        level = p.level
        form = p.form
        termin = p.termin
        qualification = p.qualification
        semestrCount = p.semestrCount
        p.semestr.each {
            addToSemestr(Semestr.createNew(it))
        }
        p.subjects.each {
            addToSubjects(PlanSubject.createNew(it))
        }
        p.practise.each {
            addToPractise(PlanPractice.createNew(it))
        }
        if (null != p.stateExam){
            stateExam = PlanStateExam.createNew(p.stateExam, this)
        }
    }
}
