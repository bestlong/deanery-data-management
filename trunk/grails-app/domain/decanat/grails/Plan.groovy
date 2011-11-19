package decanat.grails

class Plan {

    SortedSet semestr
    Speciality speciality;
    Chair chair;
    String direction;
    String form;
    String level;
    String termin
    String qualification;
    Integer semestrCount;
    Date lastUpdated;

    def beforeInsert = {
        lastUpdated = new Date()
    }
    def beforeUpdate = {
        lastUpdated = new Date()

    }

    static hasMany = [practise: PlanPractice, subjects: PlanSubject, semestr: Semestr]
    static hasOne = [stateExam: PlanStateExam]

    static constraints = {
        speciality(nullable: false)
        chair(nullable: false)
        direction(blank: false)
        form(blank: false)
        level(blank: false)
        practise(nullable: true)
        subjects(nullable: true)
        stateExam(nullable: true)
        semestr(nullable: true)
        semestrCount(range: 1..20)
        lastUpdated(nullable: true)
        termin(nullable: false)
        qualification(nullable: false)


    }
}
