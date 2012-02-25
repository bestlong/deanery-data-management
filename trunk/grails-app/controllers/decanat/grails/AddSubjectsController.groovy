package decanat.grails

import grails.converters.JSON

class AddSubjectsController {

    def planSubjectService
    def subjectService

    def index = {
        def plan = Plan.get(params.id)
        def subjects = PlanSubject.findAllByPlan(plan)
        def newSubjects = new ArrayList()

        subjects.each {
            def res = ""
            def list = PlanHours.findAllByPlanSubject(it)
            list.each {
                res += it.semestr
            }
            def newSubject = [
                    id: it.id,
                    planId: it.plan.id,
                    name: it.subject.name,
                    chair: it.subject.chair.name,
                    count: res,
                    creditCount: it.creditCount,
                    lectureCount: it.lectureCount,
                    seminarCount: it.seminarCount,
                    practiceCount: it.practiceCount,
                    labCount: it.labCount,
                    samCount: it.samCount,
                    total: it.lectureCount + it.seminarCount + it.practiceCount + it.labCount + it.samCount
            ]
            newSubjects.add newSubject
        }
        [res: newSubjects, plan: plan]
    }

    def filter = {
        def plan = Plan.get(params.id)
        def semestrs = []
        (1..plan.semestrCount).each {int i ->
            if (params."filterSemestr${i}" as Boolean){
                semestrs.add(i)
            }
        }
        def result = planSubjectService.findPlanSubjectsBySemestrList(semestrs)
        def newSubjects = []
        result.each {
            def res = ""
            def list = PlanHours.findAllByPlanSubject(it)
            list.each {
                res += it.semestr
            }
            def newSubject = [
                    id: it.id,
                    planId: it.plan.id,
                    name: it.subject.name,
                    chair: it.subject.chair.name,
                    count: res,
                    creditCount: it.creditCount,
                    lectureCount: it.lectureCount,
                    seminarCount: it.seminarCount,
                    practiceCount: it.practiceCount,
                    labCount: it.labCount,
                    samCount: it.samCount,
                    total: it.lectureCount + it.seminarCount + it.practiceCount + it.labCount + it.samCount
            ]
            newSubjects.add newSubject
        }
        render(template: "/template/planSubject/planSubjectList", model: ['plan': plan, 'res': newSubjects]);
    }

    def add = {
        def plan = Plan.get(params.id)
        [res: Subject.list(), plan: plan]
    }

    def edit = {
        def plan = Plan.get(params.planId)
        def subject = PlanSubject.get(params.id)
        def controls = [:]
        def mapHours = [:]
        def newControls = planSubjectService.getControlType(params.id);

        newControls.each {
            controls.put(it.semestr, it)
        }
        def hours = PlanHours.findAllByPlanSubject(subject)

        hours.each {
            mapHours.put(it.semestr, it)
        }

        [res: Subject.list(), plan: plan, subject: subject, controls: controls, hours: mapHours]
    }

    def hours = {
        def subj = PlanSubject.get(params.id)
        def list = PlanHours.findAllByPlanSubject(subj)
        render list as JSON
    }

    def control = {
        def newControls = planSubjectService.getControlType(params.id);
        render newControls as JSON
    }

    def search = {
        def res = subjectService.findSubjects(params.chair.asType(Integer.class), params.name, params.shortName);
        render(template: "/template/subject/selectSubject", model: [res: res]);
    }

    def save = {
        try {
            def subject = Subject.get(params.subjId)
            def plan = Plan.get(params.planId)
            params.id = params.planId
            if (subject && plan) {
                def planSubject = new PlanSubject()
                planSubject.plan = plan
                planSubject.subject = subject
                planSubject.creditCount = params.creditCount == "" || params.creditCount == null ? 0 : params.creditCount as double
                planSubject.lectureCount = params.lectureCount == "" || params.lectureCount == null ? 0 : params.lectureCount as int
                planSubject.seminarCount = params.seminarCount == "" || params.seminarCount == null ? 0 : params.seminarCount as int
                planSubject.practiceCount = params.practiceCount == "" || params.practiceCount == null ? 0 : params.practiceCount as int
                planSubject.labCount = params.labCount == "" || params.labCount == null ? 0 : params.labCount as int
                planSubject.samCount = params.samCount == "" || params.samCount == null ? 0 : params.samCount as int
                if (planSubject.validate())
                    planSubject.save(flush: true)
                else {
                    planSubject.errors.each {
                        log.error it
                    }
                    throw new Exception("planSubject is not valid")
                }

                List semestrs = new ArrayList<Integer>(plan.semestrCount);

                for (int i: 1..plan.semestrCount) {
                    if (params."c${i}")
                        semestrs.add(i)
                }

                semestrs.each {

                    def planControlType = planSubjectService.fillControlType(params, null, planSubject, it)
                    if (planControlType.validate())
                        planControlType.save(flush: true)
                    else {
                        planControlType.errors.each {
                            log.error it
                        }
                        throw new Exception("validation doesn't work, subject: ${subject}, plan: ${plan}")
                    }
                    def planHour = planSubjectService.fillPlanHour(params, null, planSubject, it)
                    if (planHour.validate())
                        planHour.save(flush: true)
                    else {
                        planHour.errors.each {
                            log.error it
                        }
                        throw new Exception("planHour is not valid")
                    }
                }
                flash.message = message(code:"msg.planSubject.add.success")
            }
            else {
                log.error "validation doesn't work, subject: ${subject}, plan: ${plan}"
                throw new Exception("validation doesn't work, subject: ${subject}, plan: ${plan}")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code:"msg.planSubject.add.error")
        }
        redirect(action: index, params: params)
    }

    def update = {
        try {
            def subject = Subject.get(params.subjId)
            def plan = Plan.get(params.planId)
            params.id = params.planId
            if (subject && plan) {
                def planSubject = PlanSubject.get(params.subjectId)
                planSubjectService.clearSubject(planSubject)
                planSubject.plan = plan
                planSubject.subject = subject
                planSubject.creditCount = params.creditCount == "" || params.creditCount == null ? 0 : params.creditCount as double
                planSubject.lectureCount = params.lectureCount == "" || params.lectureCount == null ? 0 : params.lectureCount as int
                planSubject.seminarCount = params.seminarCount == "" || params.seminarCount == null ? 0 : params.seminarCount as int
                planSubject.practiceCount = params.practiceCount == "" || params.practiceCount == null ? 0 : params.practiceCount as int
                planSubject.labCount = params.labCount == "" || params.labCount == null ? 0 : params.labCount as int
                planSubject.samCount = params.samCount == "" || params.samCount == null ? 0 : params.samCount as int
                if (planSubject.validate())
                    planSubject.save(flush: true)
                else {
                    planSubject.errors.each {
                        log.error it
                    }
                    throw new Exception("planSubject is not valid")
                }

                List semestrs = new ArrayList<Integer>(plan.semestrCount);

                for (int i: 1..plan.semestrCount) {
                    if (params."c${i}")
                        semestrs.add(i)
                }

                semestrs.each {

                    def planControlType = planSubjectService.fillControlType(params, null, planSubject, it)
                    if (planControlType.validate())
                        planControlType.save(flush: true)
                    else {
                        planControlType.errors.each {
                            log.error it
                        }
                        throw new Exception("validation doesn't work, subject: ${subject}, plan: ${plan}")
                    }
                    def planHour = planSubjectService.fillPlanHour(params, null, planSubject, it)
                    if (planHour.validate())
                        planHour.save(flush: true)
                    else {
                        planHour.errors.each {
                            log.error it
                        }
                        throw new Exception("planHour is not valid")
                    }
                }
                flash.message = message(code:"msg.planSubject.edit.success")
            }
            else {
                log.error "validation doesn't work, subject: ${subject}, plan: ${plan}"
                throw new Exception("validation doesn't work, subject: ${subject}, plan: ${plan}")
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code:"msg.planSubject.edit.error")
        }
        redirect(action: index, params: params)
    }

    def delete = {
        try {
            if (params.id) {
                PlanSubject subj = PlanSubject.get(params.id);
                if (subj) {
                    subj.delete(flush: true);
                    flash.message = message(code:"msg.planSubject.remove.success")
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage(), e)
            flash.error = message(code:"msg.planSubject.remove.error")
        }

        redirect(action: index, id: params.planId)
    }
}
