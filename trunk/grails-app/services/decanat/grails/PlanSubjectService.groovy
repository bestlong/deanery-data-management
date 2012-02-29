package decanat.grails

class PlanSubjectService {

    static transactional = true

    def findPlanSubjectsBySemestrList(def semestr, def plan){
        def c = PlanSubject.createCriteria()
        c.listDistinct{
            order("lastUpdated", "desc")
            eq("plan", plan)
            planHours {
                'in'("semestr", semestr);
            }
        }
    }

    def getControlType(id) {
        def subj = PlanSubject.get(id)
        def list = PlanControlType.findAllByPlanSubject(subj)
        def newControls = new ArrayList();

        list.each {
            def bit = Integer.toBinaryString(it.mask)
            def newControl = [
                    id: it.id,
                    subjId: it.planSubject.id,
                    exam: bit.size() > 0 ? new Integer("" + bit.charAt(bit.size() - 1)) : 0,
                    zach: bit.size() > 1 ? new Integer("" + bit.charAt(bit.size() - 2)) : 0,
                    cProj: bit.size() > 2 ? new Integer("" + bit.charAt(bit.size() - 3)) : 0,
                    cWork: bit.size() > 3 ? new Integer("" + bit.charAt(bit.size() - 4)) : 0,
                    rgr: bit.size() > 4 ? new Integer("" + bit.charAt(bit.size() - 5)) : 0,
                    contrWork: bit.size() > 5 ? new Integer("" + bit.charAt(bit.size() - 6)) : 0,
                    semestr: it.semestr
            ]
            newControls.add(newControl)
        }

        return newControls

    }

    def fillControlType(params, planControlType, subject, semestr) {
        def exam = params."exam${semestr}" == null ? 0 : 1
        def zach = params."zach${semestr}" == null ? 0 : 2
        def cProj = params."cProj${semestr}" == null ? 0 : 4
        def cWork = params."cWork${semestr}" == null ? 0 : 8
        def rgr = params."rgr${semestr}" == null ? 0 : 16
        def contrWork = params."contrWork${semestr}" == null ? 0 : 32

        def mask = exam + zach + cProj + cWork + rgr + contrWork
        if (planControlType) {
            planControlType.mask = mask
            planControlType.planSubject = subject
            planControlType.semestr = semestr
        }
        else
            planControlType = new PlanControlType(mask: mask, planSubject: subject, semestr: semestr)
        return planControlType
    }

    def fillPlanHour(params, hour, planSubject, semestr) {
        def planHour
        if (hour)
            planHour = hour
        else
            planHour = new PlanHours()
        planHour.lectures = params."lectureCount${semestr}" == null || params."lectureCount${semestr}".equals("") ? 0 : params."lectureCount${semestr}" as int
        planHour.seminars = params."seminarCount${semestr}" == null || params."seminarCount${semestr}".equals("") ? 0 : params."seminarCount${semestr}" as int
        planHour.practices = params."practiceCount${semestr}" == null || params."practiceCount${semestr}".equals("") ? 0 : params."practiceCount${semestr}" as int
        planHour.labs = params."labCount${semestr}" == null || params."labCount${semestr}".equals("") ? 0 : params."labCount${semestr}" as int
        planHour.semestr = semestr
        planHour.planSubject = planSubject
        return planHour
    }

    def clearSubject(planSubject) {
        def iter = planSubject.planHours.iterator()
        while (iter.hasNext()) {
            def item = iter.next()
            iter.remove()
            item.delete()
        }
        iter = planSubject.planControlTypes.iterator()
        while (iter.hasNext()) {
            def item = iter.next()
            iter.remove()
            item.delete()
        }
    }
}
