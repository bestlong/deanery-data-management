package stu.cn.ua.dbf.reader

import stu.cn.ua.dbf.dto.PlanSubjectDTO
import decanat.grails.PlanSubject
import decanat.grails.Plan

/**
 * @author evgeniy
 * date: 03.05.12
 */
class PlanSubjectDTOReader extends DBFAbstractReader<PlanSubjectDTO> {

    private List<PlanSubjectDTO> resultList = new ArrayList<PlanSubjectDTO>()
    private Plan plan

    PlanSubjectDTOReader(Plan plan) {
        this.plan = plan
    }

    @Override
    protected void add(PlanSubjectDTO d) {
        resultList.add(d)
    }

    @Override
    protected PlanSubjectDTO createDomain() {
        return new PlanSubjectDTO()
    }

    @Override
    List<PlanSubjectDTO> validate() {
        def errors = []
        resultList.each {
            def result = PlanSubject.validate(it, plan)
            if (!result.success) {
                errors.add(result.messages)
            }
        }
        errors
    }

    @Override
    int save() {
        resultList.each {
            def item = it
            PlanSubject.withTransaction {
                PlanSubject.savePlanSubject(item, plan)
            }
        }
        resultList.size()
    }
}
