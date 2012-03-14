package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum

class PlanSubject {

    static hasMany = [planControlTypes: PlanControlType, planHours: PlanHours]
    static belongsTo = [plan: Plan]

    Plan plan
    Subject subject
    Double creditCount
    int lectureCount
    int seminarCount
    int practiceCount
    int labCount
    int samCount
    Date lastUpdated

    static PlanSubject createNew(PlanSubject subj){
        PlanSubject newSubject = new PlanSubject()
        newSubject.subject = subj.subject
        newSubject.creditCount = subj.creditCount
        newSubject.lectureCount = subj.lectureCount
        newSubject.seminarCount = subj.seminarCount
        newSubject.practiceCount = subj.practiceCount
        newSubject.labCount = subj.labCount
        newSubject.samCount = subj.samCount
        subj.planHours.each {
            newSubject.addToPlanHours(PlanHours.createNew(it))
        }
        subj.planControlTypes.each {
            newSubject.addToPlanControlTypes(PlanControlType.createPlanControlType(it))
        }

        newSubject
    }

    def beforeInsert = {
        lastUpdated = new Date()
        plan.lastUpdated = new Date()
    }
    def beforeUpdate = {
        lastUpdated = new Date()
        plan.lastUpdated = new Date()

    }
    def beforeDelete = {
        lastUpdated = new Date()
        plan.lastUpdated = new Date()
    }

    def afterInsert(){
        if (null != subject){
            subject.referenceCount++
        }
    }

    def afterDelete(){
        if (null != subject){
            subject.referenceCount--
        }
    }

    def getTotal() {
        return lectureCount + seminarCount + practiceCount + labCount + samCount
    }

    public String getControlType(ControlTypeEnum cType) {
        def result = new StringBuilder()
        planControlTypes.each {
            if (it.isControlTypeExists(cType)) {
                result.append(it.semestr)
            }
        }
        if ("".equals(result.toString())) {
            result.append("___")
        }
        result.toString()
    }

    public int getControlTypeCount(ControlTypeEnum cType) {
        def sum = 0
        planControlTypes.each {
            if (it.isControlTypeExists(cType)) {
                sum++
            }
        }
        sum
    }

    public int getControlTypeCount(ControlTypeEnum cType, int semestr) {
        def sum = 0
        def planControl = PlanControlType.findBySemestrAndPlanSubject(semestr, this)
        if (null == planControl){
            return 0
        }
        if (planControl.isControlTypeExists(cType)) {
            sum++
        }
        sum
    }

    public int getHourCount(int semestr, WorkTypeEnum workType) {
        PlanHours hour = PlanHours.findBySemestrAndPlanSubject(semestr, this)
        if (null == hour) {
            return 0;
        }
        switch (workType) {
            case WorkTypeEnum.LECTURE:
                return hour.lectureCount
            case WorkTypeEnum.SEMINAR:
                return hour.seminarCount
            case WorkTypeEnum.PRACTISE:
                return hour.practiceCount
            case WorkTypeEnum.LAB:
                return hour.labCount
        }
        throw IllegalArgumentException("unknown workType: ${workType}")
    }

    public int getHourCount(int semestr) {
        PlanHours hour = PlanHours.findBySemestrAndPlanSubject(semestr, this)
        if (null == hour) {
            return 0;
        }
        return hour.lectureCount + hour.seminarCount + hour.practiceCount + hour.labCount
    }

    static mapping = {
        planControlTypes sort: 'semestr'
        planHours sort: 'semestr'
    }

    static constraints = {
        plan(nullable: false)
        subject(nullable: false)
        creditCount(min: Double.valueOf(0))
        lectureCount(min: 0)
        seminarCount(min: 0)
        practiceCount(min: 0)
        labCount(min: 0)
        samCount(min: 0)
        lastUpdated(nullable: true)
    }
}
