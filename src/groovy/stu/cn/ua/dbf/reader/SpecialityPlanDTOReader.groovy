package stu.cn.ua.dbf.reader

import stu.cn.ua.dbf.dto.SpecilaityPlanDTO
import decanat.grails.Speciality

/**
 * @author evgeniy
 * date: 11.03.12
 */
class SpecialityPlanDTOReader extends DBFAbstractReader<SpecilaityPlanDTO> {
    private List<SpecilaityPlanDTO> resultList = new ArrayList<SpecilaityPlanDTO>();

    @Override
    protected void add(SpecilaityPlanDTO d) {
        resultList.add(d)
    }

    @Override
    protected SpecilaityPlanDTO createDomain() {
        return new SpecilaityPlanDTO()
    }

    @Override
    List<SpecilaityPlanDTO> validate() {
        def errors = []
        resultList.each {
            def result = Speciality.validate(it)
            if (!result.success) {
                errors.add(result.messages)
            }
            else {

            }
        }
        errors
    }

    @Override
    int save() {
        resultList.each {
            Speciality.saveSpeciality(it)
        }
        resultList.size()
    }
}
