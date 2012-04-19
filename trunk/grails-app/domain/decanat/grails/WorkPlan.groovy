package decanat.grails

import stu.cn.ua.enums.PlanClass
import stu.cn.ua.CommonUtils

class WorkPlan extends Plan{
    
    String name
    Plan plan

    WorkPlan() {
    }

    WorkPlan(String name) {
        this.name = name
    }


    static WorkPlan createFromWorkPlan(String name, WorkPlan workPlan){
        WorkPlan wPlan = new WorkPlan()
        wPlan.name = name
        wPlan.copyPlan(workPlan)
        wPlan.plan = workPlan.plan
        wPlan
    }

    static WorkPlan createFromStudyPlan(String name, Plan plan){
        WorkPlan wPlan = new WorkPlan()
        wPlan.name = name
        wPlan.copyPlan(plan)
        wPlan.plan = plan
        wPlan
    }

    static belongsTo = [plan: Plan]

    static mapping = {
        discriminator value: PlanClass.WORK
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
        startYear = p.startYear
        endYear = p.endYear
        p.semesterList.each {
            addToSemesterList(Semestr.createNew(it))
        }
        p.subjects.each {
            addToSubjects(PlanSubject.createNew(it))
        }
        p.practiseList.each {
            addToPractiseList(PlanPractice.createNew(it))
        }
        if (null != p.stateExam){
            stateExam = PlanStateExam.createNew(p.stateExam, this)
        }
    }
    public String toCSV(){
        String srt = new String();
        def  nodes=["id" , "chairId", "direction" , "startYear" ,  "endYear" ,  "form" ,  "level" ,  "qualification" ,  "semestrCount", "specialityId",  "termin","name","planId"];
        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;
    }
}
