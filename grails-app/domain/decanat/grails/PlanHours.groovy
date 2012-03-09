package decanat.grails

class PlanHours implements Comparable{

    static belongsTo = [planSubject: PlanSubject]

    int practiceCount;
    int semestr;
    PlanSubject planSubject;
    int lectureCount;
    int labCount;
    int seminarCount;

    static PlanHours createNew(PlanHours hours){
        PlanHours planHours = new PlanHours()
        planHours.practiceCount = hours.practiceCount
        planHours.semestr = hours.semestr
        planHours.lectureCount = hours.lectureCount
        planHours.labCount = hours.labCount
        planHours.seminarCount = hours.seminarCount

        planHours
    }

    static constraints = {
        semestr(nullable: false)
        planSubject(nullable: false)
        practiceCount(min: 0, blank: true)
        lectureCount(min: 0, blank: true)
        labCount(min: 0, blank: true)
        seminarCount(min: 0, blank: true)
    }

    int compareTo(Object o) {
        semestr.compareTo(o.semestr)
    }
}
