package decanat.grails

import stu.cn.ua.dbf.dto.ValidationResult
import stu.cn.ua.dbf.dto.ErrorInfo
import stu.cn.ua.dbf.dto.SubjectDTO

class Subject {

    String name
    Chair chair
    String shortName
    String code

    private static final fieldMap = [name: 'NAMEDIS', shortName: 'SHORTDIS', code: 'CODDIS']

    static constraints = {
        name(blank: false)
        chair(nullable: true)
        shortName(nullable: true)
        code(nullable: true)
    }

    public static Subject saveSubject(SubjectDTO subjectDTO){
        def subject = Subject.findByCode(subjectDTO.coddis)
        if (!subject || "".equals(subjectDTO.coddis)){
            subject = new Subject(name:  subjectDTO.namedis, shortName: subjectDTO.shortdis, code: subjectDTO.coddis)
        } else{
            subject.name = subjectDTO.namedis
            subject.shortName = subjectDTO.shortdis
        }
        subject.save()
    }

    public static ValidationResult validate(SubjectDTO subjectDTO){
        def subject = Subject.findByCode(subjectDTO.coddis)
        if (!subject || "".equals(subjectDTO.coddis)){
            subject = new Subject(name:  subjectDTO.namedis, shortName: subjectDTO.shortdis, code: subjectDTO.coddis)
        } else{
            subject.name = subjectDTO.namedis
            subject.shortName = subjectDTO.shortdis
        }
        if (!subject.validate()){
            List<ErrorInfo> validationErrors = new ArrayList<ErrorInfo>()
            subject.errors.allErrors.each {
                validationErrors.add(new ErrorInfo(subjectDTO.toString(), fieldMap.get(it.field), it.rejectedValue))
            }
            return new ValidationResult(false, validationErrors)
        } else {
            return new ValidationResult(true)
        }
    }    
}
