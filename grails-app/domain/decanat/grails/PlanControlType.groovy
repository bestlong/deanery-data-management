package decanat.grails

class PlanControlType {

    static belongsTo = [planSubject: PlanSubject]

    int mask;
    int semestr;
    PlanSubject planSubject;

    static constraints = {
        mask(nullable: false)
        semestr(nullable: false)
        planSubject(nullable: false)
    }
}
