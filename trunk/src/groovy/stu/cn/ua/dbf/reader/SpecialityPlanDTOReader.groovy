package stu.cn.ua.dbf.reader

import decanat.grails.Speciality
import stu.cn.ua.dbf.dto.SpecialityPlanDTO
import decanat.grails.Plan

/**
 * @author evgeniy
 * date: 11.03.12
 */
class SpecialityPlanDTOReader extends DBFAbstractReader<SpecialityPlanDTO> {
    private List<SpecialityPlanDTO> resultList = new ArrayList<SpecialityPlanDTO>();

    @Override
    protected void add(SpecialityPlanDTO d) {
        resultList.add(d)
    }

    @Override
    protected SpecialityPlanDTO createDomain() {
        return new SpecialityPlanDTO()
    }

    @Override
    List<SpecialityPlanDTO> validate() {
        def errors = []
        resultList.each {
            def result = Speciality.validate(it, currentUser)
            if (!result.success) {
                errors.add(result.messages)
            }
            else {
                result = Plan.validate(it)
                if (!result.success) {
                    errors.add(result.messages)
                }
            }
        }
        errors
    }

    @Override
    int save() {
        resultList.each {
            def spec = Speciality.saveSpeciality(it, currentUser)
            Plan.savePlan(it, spec)
        }
        resultList.size()
    }
}
