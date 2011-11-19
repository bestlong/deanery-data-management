package decanat.grails

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
