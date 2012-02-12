package decanat.grails

import stu.cn.ua.enums.ControlTypeEnum
import stu.cn.ua.enums.WorkTypeEnum

class PlanSubject {

    SortedSet planHours

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

    def beforeInsert = {
        plan.lastUpdated = new Date()
    }
    def beforeUpdate = {
        plan.lastUpdated = new Date()

    }
    def beforeDelete = {
        plan.lastUpdated = new Date()

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
                return hour.lectures
            case WorkTypeEnum.SEMINAR:
                return hour.seminars
            case WorkTypeEnum.PRACTISE:
                return hour.practices
            case WorkTypeEnum.LAB:
                return hour.labs
        }
        throw IllegalArgumentException("unknown workType: ${workType}")
    }

    public int getHourCount(int semestr) {
        PlanHours hour = PlanHours.findBySemestrAndPlanSubject(semestr, this)
        if (null == hour) {
            return 0;
        }
        return hour.lectures + hour.seminars + hour.practices + hour.labs
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
    }
}
