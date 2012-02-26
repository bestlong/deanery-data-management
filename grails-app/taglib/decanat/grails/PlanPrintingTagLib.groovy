package decanat.grails

class PlanPrintingTagLib {

    def hourBySubjAndSem (def subjId, def semester){
        def subj = PlanSubject.get(subjId as int)
        return PlanHours.findByPlanSubjectAndSemestr(subj, semester as int) ? 1 : 0
    }
}
