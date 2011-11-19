package decanat.grails

class DecanatTagLib {
    def authenticateService

    def userName = {
        out << (authenticateService.userDomain() ? authenticateService.userDomain().username : 'Guest')
    }

    def hourBySubjAndSem = { subject, num ->
        def subj = PlanSubject.get(subject as int)
        return PlanHours.findByPlanSubjectAndSemestr(subj, num as int) ? 1 : 0
    }
}
