package stu.cn.ua.dbf.reader

import stu.cn.ua.dbf.dto.SubjectDTO
import decanat.grails.Subject

/**
 * @author evgeniy
 * date: 10.03.12
 */
class SubjectDTOReader extends DBFAbstractReader<SubjectDTO> {
    private List<SubjectDTO> resultList = new ArrayList<SubjectDTO>();

    @Override
    protected void add(SubjectDTO d) {
        resultList.add(d)
    }

    @Override
    protected SubjectDTO createDomain() {
        return new SubjectDTO()
    }

    @Override
    List<SubjectDTO> validate() {
        def errors = []
        resultList.each {
            def result = Subject.validate(it, currentUser)
            if (!result.success) {
                errors.add(result.messages)
            }
        }
        errors
    }

    @Override
    int save() {
        resultList.each {
            Subject.saveSubject(it, currentUser)
        }
        resultList.size()
    }
}
