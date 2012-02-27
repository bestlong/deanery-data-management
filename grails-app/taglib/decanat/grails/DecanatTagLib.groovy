package decanat.grails

class DecanatTagLib {
    def springSecurityService

    def userName = {
        out << (springSecurityService.getPrincipal() ? springSecurityService.getPrincipal().username : 'Guest')
    }

    def hourBySubjAndSem (def subjId, def semester) {
        def subj = PlanSubject.get(subjId as int)
        return PlanHours.findByPlanSubjectAndSemestr(subj, semester as int) ? 1 : 0
    }
}
