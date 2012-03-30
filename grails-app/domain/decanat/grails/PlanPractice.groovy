package decanat.grails

import stu.cn.ua.CommonUtils

class PlanPractice {
    String name;
    Plan plan;
    int semestr;
    int weekCount;

    static belongsTo = [plan: Plan]

    static PlanPractice createNew(PlanPractice planPractice){
        PlanPractice newPlanPractice = new PlanPractice()
        newPlanPractice.name = planPractice.name
        newPlanPractice.semestr = planPractice.semestr
        newPlanPractice.weekCount = planPractice.weekCount

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
        weekCount(min: 0)
    }

    public String toCSV(){

        String srt = new String();

        def  nodes=["id" , "name", "planId" , "semestr" ,  "weekCount"];

        for(String obj: nodes){
            def nod=this."${obj}";
            srt = srt + CommonUtils.wordToCSV(nod);
        }
        srt=srt+"\n"
        return srt;


    }
}
