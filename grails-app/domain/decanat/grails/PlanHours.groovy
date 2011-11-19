package decanat.grails

class PlanHours implements Comparable{

    static belongsTo = [planSubject: PlanSubject]

    int practices;
    int semestr;
    PlanSubject planSubject;
    int lectures;
    int labs;
    int seminars;

    static constraints = {
        semestr(nullable: false)
        planSubject(nullable: false)
        practices(min: 0, blank: true)
        lectures(min: 0, blank: true)
        labs(min: 0, blank: true)
        seminars(min: 0, blank: true)
    }

    int compareTo(Object o) {
        semestr.compareTo(o.semestr)
    }
}
