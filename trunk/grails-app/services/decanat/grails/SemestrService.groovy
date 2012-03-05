package decanat.grails

class SemestrService {

    static transactional = true

    def cleanSemesters(int semestr, def plan) {
        Semestr.executeUpdate("delete Semestr s where s.number > :number and s.plan = :plan", [number: semestr, plan: plan])
    }
}
