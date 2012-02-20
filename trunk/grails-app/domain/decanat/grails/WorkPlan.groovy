package decanat.grails

import stu.cn.ua.enums.PlanClass

class WorkPlan extends Plan{
    
    String name
    Plan plan

    WorkPlan() {
    }

    WorkPlan(String name) {
        this.name = name
    }

    WorkPlan(String name, Plan plan) {
        this.name = name
        copyPlan(plan)
        this.plan = plan
    }

    static mapping = {
        discriminator value: PlanClass.WORK, column: "class"
    }
    static constraints = {
        name(nullable: true)
        plan(nullable: false)
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
